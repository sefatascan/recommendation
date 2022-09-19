package com.sefa.etl.service;

import com.sefa.etl.entity.BestSellerEntity;
import com.sefa.etl.projection.ProductUserCountProjection;
import com.sefa.etl.repository.BestSellerRepository;
import com.sefa.etl.repository.OrderItemRepository;
import com.sefa.etl.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BestSellerService {

    private static final int BEST_SELLER_SIZE = 10;

    private final ProductRepository productRepository;

    private final OrderItemRepository orderItemRepository;

    private final BestSellerRepository bestSellerRepository;

    public void prepareMonthlyBestSellersByCategory() {
        List<String> distinctCategory = productRepository.findDistinctCategory();

        distinctCategory.forEach(this::prepareBestSeller);
    }

    @Async
    public void prepareBestSeller(String categoryId) {
        List<ProductUserCountProjection> byCategory = orderItemRepository.findDistinctUserByCategoryAndGroupByProduct(categoryId,
                                                                                                                      Pageable.ofSize(BEST_SELLER_SIZE));
        mapAndSave(categoryId, byCategory);
    }


    private void mapAndSave(String categoryId, List<ProductUserCountProjection> bestSellerProjection) {
        LocalDate currentDate = LocalDate.now();

        List<BestSellerEntity> bestSellerByCategoryModel = bestSellerProjection.stream()
                                                                                 .map(i -> BestSellerEntity.builder()
                                                                                                           .year(currentDate.getYear())
                                                                                                           .month(currentDate.getMonthValue())
                                                                                                           .category(categoryId)
                                                                                                           .product(i.getProductId())
                                                                                                           .total(i.getUserCount().intValue())
                                                                                                           .build())
                                                                                 .collect(toList());
       bestSellerRepository.saveAll(bestSellerByCategoryModel);
    }
}
