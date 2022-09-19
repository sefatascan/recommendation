package com.sefa.api.controller;

import com.sefa.api.dto.response.ProductResponse;
import com.sefa.api.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/user/{id}")
    public ResponseEntity<ProductResponse> getLastTenProduct(@PathVariable("id") String userId) {
        return ResponseEntity.ok(historyService.getLatestTenProductByUser(userId));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteHistoryByUserAndProduct(@PathVariable("id") String userId, @RequestParam("productIds") List<String> productId) {
        historyService.deleteHistoryByUserIdAndProductId(userId, productId);
        return ResponseEntity.ok().build();
    }
}
