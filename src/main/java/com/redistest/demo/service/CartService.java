package com.redistest.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redistest.demo.model.Cart;
import com.redistest.demo.model.CartItem;
import com.redistest.demo.model.Product;

import redis.clients.jedis.Jedis;

@Service
public class CartService {

    private static final String CART_KEY = "cart:%s";
    private static final String PRODUCT_KEY = "product:%s";

    @Autowired
    private Jedis jedisMaster;

    @Autowired
    private Jedis jedisSlave;

    public void addToCart(String userId, Product product, int quantity) {
        if (isProductPresent(product)) {
            addToCart(userId, product.getSkuId(), quantity);
        } else {
            createProduct(product);
            addToCart(userId, product.getSkuId(), quantity);
        }
    }

    private void addToCart(String userId, String skuId, int quantity) {
        if (quantity <= 0) {
            jedisMaster.hdel(String.format(CART_KEY, userId), skuId);
        } else {
            jedisMaster.hset(String.format(CART_KEY, userId), skuId, String.valueOf(quantity));
        }
    }

    public Cart getCart(String userId) {
        Map<String, String> cart = jedisSlave.hgetAll(String.format(CART_KEY, userId));
        Map<String, CartItem> items =  cart.entrySet().stream()
                .map(entry -> assembleItem(entry))
                .collect(Collectors.toMap((item -> item.getProduct().getSkuId()), Function.identity(),
                        (existing, replacement) -> existing) );
        return Cart.of(userId, items);
    }

    private CartItem assembleItem(Map.Entry entry) {
        CartItem item = new CartItem();
        item.setProduct(getProductBySku((String) entry.getKey()));
        item.setQuantity(Integer.valueOf((String) entry.getValue()));
        return item;
    }

    private Boolean isProductPresent(Product product) {
        return jedisSlave.exists(String.format(PRODUCT_KEY, product.getSkuId()));
    }

    private void createProduct(Product product) {
        HashMap<String, String> productDetails = new HashMap<>();
        productDetails.put("skuId", product.getSkuId());
        productDetails.put("name", product.getName());
        productDetails.put("description", product.getDescription());
        productDetails.put("price", String.valueOf(product.getPrice()));
        productDetails.put("currency", product.getCurrency());

        jedisMaster.hset(String.format(PRODUCT_KEY, product.getSkuId()), productDetails);
    }

    private Product getProductBySku(String skuId) {
        return Product.of(jedisSlave.hgetAll(String.format(PRODUCT_KEY, skuId)));
    }
}
