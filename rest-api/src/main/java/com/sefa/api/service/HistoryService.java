package com.sefa.api.service;

import com.sefa.api.dto.response.ProductResponse;
import com.sefa.api.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sefa.api.dto.response.ProductResponse.ResponseType.PERSONALIZED;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private static final int PRODUCT_SIZE = 10;

    private final HistoryRepository historyRepository;

    @Transactional
    public void deleteHistoryByUserIdAndProductId(String userId, List<String> productId) {
        historyRepository.deleteByUserIdAndProductIdIn(userId, productId);
    }

    public ProductResponse getLatestTenProductByUser(String userId) {
        List<String> productByUserIdOrderByViewedAt = historyRepository.findProductByUserIdOrderByViewedAt(userId, Pageable.ofSize(PRODUCT_SIZE));
        return ProductResponse.of(userId, productByUserIdOrderByViewedAt, PERSONALIZED);
    }
}
