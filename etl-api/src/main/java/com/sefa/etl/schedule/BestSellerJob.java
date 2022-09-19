package com.sefa.etl.schedule;

import com.sefa.etl.service.BestSellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BestSellerJob {

    private final BestSellerService bestSellerService;

    /**
     * Scheduled the annotated method to run at 00:00 AM on the first day of every month.
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void bestSellerMonthlyScheduler() {
        log.info("SCHEDULER - bestSellerMonthlyScheduler");
        log.info("[bestSellerMonthlyScheduler] started...");

        bestSellerService.prepareMonthlyBestSellersByCategory();

        log.info("[bestSellerMonthlyScheduler] finished...");
    }
}
