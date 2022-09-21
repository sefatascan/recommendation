package com.sefa.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sefa.api.util.RecommendationHelper;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductResponse {

    @JsonProperty("user-id")
    private String userId;

    @JsonProperty("products")
    private List<String> products;

    @JsonProperty("type")
    private ResponseType type;

    public static ProductResponse of(String userId, List<String> products, ResponseType type) {
        return ProductResponse.builder().userId(userId).products(RecommendationHelper.getValidRecommendation(products)).type(type).build();
    }

    @RequiredArgsConstructor
    public enum ResponseType {
        PERSONALIZED("personalized"), NON_PERSONALIZED("non-personalized");

        private final String jsonValue;

        @JsonValue
        public String getJsonValue() {
            return jsonValue;
        }
    }
}
