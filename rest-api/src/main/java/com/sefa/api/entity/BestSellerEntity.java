package com.sefa.api.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "best_seller")
public class BestSellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "category_id")
    private String category;

    @Column(name = "product_id")
    private String product;

    @Column(name = "total")
    private Integer total;
}
