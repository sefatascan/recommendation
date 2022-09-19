package com.sefa.api.repository;

import com.sefa.api.entity.BestSellerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BestSellerRepository extends JpaRepository<BestSellerEntity, Long> {

    @Query("select b.product from BestSellerEntity b where b.product in :productIds order by b.total desc")
    List<String> findByProductOrderByTotal(List<String> productIds, Pageable pageable);

    @Query("select b.product from BestSellerEntity b order by b.total desc")
    List<String> findOrderByTotal(Pageable pageable);
}