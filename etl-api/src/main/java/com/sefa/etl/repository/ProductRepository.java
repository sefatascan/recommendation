package com.sefa.etl.repository;

import com.sefa.etl.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query("select distinct p.categoryId from ProductEntity p")
    List<String> findDistinctCategory();

}
