package com.sefa.api.service;

import com.sefa.api.dto.response.ProductResponse;
import com.sefa.api.repository.BestSellerRepository;
import com.sefa.api.repository.HistoryRepository;
import com.sefa.api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceTest {

    @InjectMocks
    private RecommendationService recommendationService;

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BestSellerRepository bestSellerRepository;

    @Test
    void should_recommendation_is_personalized() {
        final String userId = "user-id";

        when(historyRepository.existsByUserId(userId)).thenReturn(true);
        when(recommendationService.getBestSellerWithFilter(userId)).thenReturn(Collections.emptyList());

        ProductResponse recommendationByUser = recommendationService.getRecommendationByUser(userId);

        assertThat(recommendationByUser).isNotNull();
        assertThat(recommendationByUser.getType()).isEqualTo(ProductResponse.ResponseType.PERSONALIZED);
        assertThat(recommendationByUser.getUserId()).isEqualTo(userId);
        assertThat(recommendationByUser.getProducts().size()).isEqualTo(0);
    }

    @Test
    void should_recommendation_is_not_personalized() {
        final String userId = "user-id";

        when(historyRepository.existsByUserId(userId)).thenReturn(false);
        when(recommendationService.getBestSellerWithoutFilter()).thenReturn(Collections.emptyList());

        ProductResponse recommendationByUser = recommendationService.getRecommendationByUser(userId);

        assertThat(recommendationByUser).isNotNull();
        assertThat(recommendationByUser.getType()).isEqualTo(ProductResponse.ResponseType.NON_PERSONALIZED);
        assertThat(recommendationByUser.getUserId()).isEqualTo(userId);
        assertThat(recommendationByUser.getProducts().size()).isEqualTo(0);
    }

}