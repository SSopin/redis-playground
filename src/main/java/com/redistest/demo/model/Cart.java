package com.redistest.demo.model;

import java.util.Map;

import lombok.Data;

@Data
public class Cart {
//    private String id;
//    private CartStatus status;
//    private Date date;
    private String userId;
    private Map<String, CartItem> items;

    public static Cart of(/*String id, CartStatus status, Date date,*/
                          String userId, Map<String, CartItem> items) {
        final Cart cart = new Cart();
        /*cart.setId(id);
        cart.setStatus(status);
        cart.setDate(date);*/
        cart.setUserId(userId);
        cart.setItems(items);
        return cart;
    }
}
