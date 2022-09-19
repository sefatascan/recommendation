package com.sefa.etl.controller;

import com.sefa.etl.service.BestSellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etl")
@RequiredArgsConstructor
public class ManuelTriggerController {

    private final BestSellerService bestSellerService;

    @GetMapping("/trigger")
    public void trigger() {
        bestSellerService.prepareMonthlyBestSellersByCategory();
    }
}
