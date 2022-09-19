package com.sefa.etl.repository;

import com.sefa.etl.entity.OrderItemEntity;
import com.sefa.etl.projection.ProductUserCountProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    @Query("select p.id as productId, count(distinct o.userId) as userCount " +
            "from OrderItemEntity ot " +
            "join ot.product p " +
            "join ot.order o " +
            "where p.categoryId = :categoryId " +
            "group by p.id " +
            "order by userCount desc")
    List<ProductUserCountProjection> findDistinctUserByCategoryAndGroupByProduct(String categoryId, Pageable pageable);
}
