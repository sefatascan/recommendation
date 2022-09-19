package com.sefa.viewproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyModel {

    @JsonProperty("productid")
    private String productId;
}
