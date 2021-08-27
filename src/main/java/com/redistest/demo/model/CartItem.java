package com.redistest.demo.model;

import lombok.Data;

@Data
public class CartItem {
    private Product product;
    private Integer quantity;
}
