package com.sefa.api.service;

import com.sefa.api.dto.response.ProductResponse;
import com.sefa.api.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    @InjectMocks
    private HistoryService historyService;

    @Mock
    private HistoryRepository historyRepository;

    @Test
    void should_not_throw_when_delete_history() {
        final String userId = "user-id";
        final List<String> productIds = Arrays.asList("product-id");

        assertThatNoException().isThrownBy(() -> historyService.deleteHistoryByUserIdAndProductId(userId, productIds));
    }

    @Test
    void should_recommendation_returned() {

        final String userId = "user-id";
        final int productSize = 10;
        final List<String> productByUserIdOrderByViewedAt = Arrays.asList("user-1", "user-2", "user-3", "user-4", "user-5");

        when(historyRepository.findProductByUserIdOrderByViewedAt(userId, Pageable.ofSize(productSize))).thenReturn(productByUserIdOrderByViewedAt);

        ProductResponse latestTenProductByUser = historyService.getLatestTenProductByUser(userId);

        assertThat(latestTenProductByUser).isNotNull();
        assertThat(latestTenProductByUser.getType()).isEqualTo(ProductResponse.ResponseType.PERSONALIZED);
        assertThat(latestTenProductByUser.getUserId()).isEqualTo(userId);
        assertThat(latestTenProductByUser.getProducts().size()).isEqualTo(5);
    }

    @Test
    void should_recommendation_not_returned() {

        final String userId = "user-id";
        final int productSize = 10;
        final List<String> productByUserIdOrderByViewedAt = Arrays.asList("user-1", "user-2", "user-3", "user-4");

        when(historyRepository.findProductByUserIdOrderByViewedAt(userId, Pageable.ofSize(productSize))).thenReturn(productByUserIdOrderByViewedAt);

        ProductResponse latestTenProductByUser = historyService.getLatestTenProductByUser(userId);

        assertThat(latestTenProductByUser).isNotNull();
        assertThat(latestTenProductByUser.getType()).isEqualTo(ProductResponse.ResponseType.PERSONALIZED);
        assertThat(latestTenProductByUser.getUserId()).isEqualTo(userId);
        assertThat(latestTenProductByUser.getProducts().size()).isEqualTo(0);
    }
}