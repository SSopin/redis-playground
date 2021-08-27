package com.redistest.demo.model;

import lombok.Data;

@Data
public class AddToCartRequest {
    private String userId;
    private Product product;
    private Integer quantity;
}
