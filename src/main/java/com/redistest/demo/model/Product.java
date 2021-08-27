package com.redistest.demo.model;

import java.util.Map;

import lombok.Data;

@Data
public class Product {
    private String skuId;
    private String name;
    private String description;
    private Double price;
    private String currency;

    public static Product of(Map<String, String> map) {
        Product product = new Product();
        product.skuId = map.get("skuId");
        product.name = map.get("name");
        product.description = map.get("description");
        product.price = Double.valueOf(map.get("price"));
        product.currency = map.get("currency");
        return product;
    }
}
