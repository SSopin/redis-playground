package com.redistest.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redislabs.modules.rejson.JReJSON;
import com.redistest.demo.model.Product;

import redis.clients.jedis.Jedis;

@Service
public class JsonCartService {

    @Autowired
    private JReJSON jsonRedisMaster;

    @Autowired
    private JReJSON jsonRedisSlave;

    public void addToCart(Product product) {
        /*
         * This function is to be done, since I have already installed
         * Redis OSS without JSON module and I have no time to re-install it.
        */
    }
}
