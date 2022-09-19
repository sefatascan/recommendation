package com.sefa.api.controller;

import com.sefa.api.dto.response.ProductResponse;
import com.sefa.api.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/user/{id}")
    public ResponseEntity<ProductResponse> recommend(@PathVariable("id") String userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationByUser(userId));
    }

}
