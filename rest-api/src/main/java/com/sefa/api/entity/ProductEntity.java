package com.sefa.api.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @Column(name = "product_id", length = 50)
    private String id;

    @Column(name = "category_id", length = 50, nullable = false)
    private String categoryId;
}
