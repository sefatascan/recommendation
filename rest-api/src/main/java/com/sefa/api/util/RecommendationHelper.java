package com.sefa.api.util;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class RecommendationHelper {
    public static int MINIMUM_RECOMMENDATION_SIZE = 5;

    public static List<String> getValidRecommendation(List<String> recommendations) {
        if (nonNull(recommendations) && recommendations.size() >= MINIMUM_RECOMMENDATION_SIZE) {
            return recommendations;
        }
        return new ArrayList<>();
    }
}
