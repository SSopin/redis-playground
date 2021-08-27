package com.redistest.demo.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redistest.demo.model.AddToCartRequest;
import com.redistest.demo.model.Cart;
import com.redistest.demo.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * Adds the product into the Cart. If active cart doesn't exist it creates it and then adds the product.
     *
     * @return Whole Cart object.
     */
    @PostMapping("/add-to-cart")
    public Cart addToCart(@RequestBody AddToCartRequest request) {
        cartService.addToCart(request.getUserId(), request.getProduct(), request.getQuantity());
        return cartService.getCart(request.getUserId());
    }
}
