package com.sefa.etl.repository;

import com.sefa.etl.entity.BestSellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestSellerRepository extends JpaRepository<BestSellerEntity, Long> {

}
