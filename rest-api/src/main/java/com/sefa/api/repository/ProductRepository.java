package com.sefa.api.repository;

import com.sefa.api.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query("select p.id from ProductEntity p where p.categoryId in :categoryIds")
    List<String> findProductIdByCategoryId(List<String> categoryIds);
}