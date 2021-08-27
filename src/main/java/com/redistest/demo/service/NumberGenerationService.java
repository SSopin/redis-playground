package com.redistest.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import redis.clients.jedis.Jedis;

@Service
@AllArgsConstructor
public class NumberGenerationService {

    @Autowired
    private Jedis jedisMaster;

    @Autowired
    private Jedis jedisSlave;

    public List<Object> getNumbersAsc() {
        return Arrays.asList(jedisSlave.zrange("numbers", Long.MIN_VALUE, Long.MAX_VALUE).toArray());
    }

    public List<Object> getNumbersDesc() {
        return Arrays.asList(jedisSlave.zrevrange("numbers", Long.MIN_VALUE, Long.MAX_VALUE).toArray());
    }

    public void saveListOfNumbers(int maxNubmer) {
        for (int i = 1; i <= maxNubmer; i++) {
            jedisMaster.zadd("numbers", Double.valueOf(i), String.valueOf(i));
        }
    }

    public void deleteNumbers() {
        jedisMaster.del("numbers");
    }

}
