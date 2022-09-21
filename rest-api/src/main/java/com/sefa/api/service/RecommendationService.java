package com.sefa.api.service;

import com.sefa.api.dto.response.ProductResponse;
import com.sefa.api.repository.BestSellerRepository;
import com.sefa.api.repository.HistoryRepository;
import com.sefa.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sefa.api.dto.response.ProductResponse.ResponseType.NON_PERSONALIZED;
import static com.sefa.api.dto.response.ProductResponse.ResponseType.PERSONALIZED;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private static final int CATEGORY_SIZE = 3;
    private static final int PRODUCT_SIZE = 10;

    private final HistoryRepository historyRepository;

    private final ProductRepository productRepository;

    private final BestSellerRepository bestSellerRepository;

    public ProductResponse getRecommendationByUser(String userId) {
        boolean exists = historyRepository.existsByUserId(userId);

        if (Boolean.TRUE.equals(exists)) {
            return ProductResponse.of(userId, getBestSellerWithFilter(userId), PERSONALIZED);
        } else {
            return ProductResponse.of(userId, getBestSellerWithoutFilter(), NON_PERSONALIZED);
        }
    }

    public List<String> getBestSellerWithFilter(String userId) {
        List<String> categoryIds = historyRepository.findCategoryIdByUserIdOrderByCount(userId, Pageable.ofSize(CATEGORY_SIZE));

        List<String> productIds = productRepository.findProductIdByCategoryId(categoryIds);

        List<String> bestSellers = bestSellerRepository.findByProductOrderByTotal(productIds, Pageable.ofSize(PRODUCT_SIZE));

        return bestSellers;
    }

    public List<String> getBestSellerWithoutFilter() {
        List<String> bestSellers = bestSellerRepository.findOrderByTotal(Pageable.ofSize(PRODUCT_SIZE));

        return bestSellers;
    }

}
