package com.sefa.api.repository;

import com.sefa.api.entity.HistoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

    @Query("select p.categoryId " +
            "from HistoryEntity h " +
            "join h.product p " +
            "where h.userId = :userId " +
            "group by p.categoryId " +
            "order by count(h.id) desc")
    List<String> findCategoryIdByUserIdOrderByCount(String userId, Pageable pageable);


    @Query("select h.product.id " +
            "from HistoryEntity h " +
            "where h.userId = :userId " +
            "order by h.viewedAt desc")
    List<String> findProductByUserIdOrderByViewedAt(String userId, Pageable pageable);

    boolean existsByUserId(String userId);

    void deleteByUserIdAndProductIdIn(String userId, List<String> productId);
}
