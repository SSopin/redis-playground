package com.redistest.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

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

    /**
     * Function saves sorted set with key "numbers".
     * @param maxNubmer - largest number in a set.
     */
    public void saveSetOfNumbers(int maxNubmer) {
        for (int i = 1; i <= maxNubmer; i++) {
            jedisMaster.zadd("numbers", Double.valueOf(i), String.valueOf(i));
        }
    }

    public void deleteNumbers(String key) {
        jedisMaster.del(key);
    }
    
    public void saveListOfNumbers(int maxNumber) {
        for (int i = 1; i <= maxNumber; i++) {
            jedisMaster.lpush("numbersList",  String.valueOf(i));
        }
    }

    public List<String> getListOfNumbersAsc() {
        return jedisSlave.sort("numbersList");
    }

    public List<String> getListOfNumbersDesc() {
        SortingParams order = new SortingParams();
        return jedisSlave.sort("numbersList", order.desc());
    }

}
