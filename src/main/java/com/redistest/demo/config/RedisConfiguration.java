package com.redistest.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.redislabs.modules.rejson.JReJSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public
class RedisConfiguration {
    @Value("${redis.master.host}")
    private String redisMasterHost;

    @Value("${redis.master.port}")
    private int redisMasterPort;

    @Value("${redis.slave.host}")
    private String redisSlaveHost;

    @Value("${redis.slave.port}")
    private int redisSlavePort;

    @Value("${redis.slave.password}")
    private String redisSlavePassword;

    @Bean
    public Jedis jedisMaster() {
        JedisPool pool = new JedisPool(redisMasterHost, redisMasterPort);
        Jedis jedis =  pool.getResource();
        return jedis;
    }

    @Bean
    public Jedis jedisSlave() {
        JedisPool pool = new JedisPool(redisSlaveHost, redisSlavePort);
        Jedis jedis =  pool.getResource();
        jedis.auth(redisSlavePassword);
        return jedis;
    }

    @Bean
    public JReJSON jsonRedisMaster() {
        JedisPool pool = new JedisPool(redisMasterHost, redisMasterPort);
        return new JReJSON(pool);
    }

    @Bean
    public JReJSON jsonRedisSlave() {
        JedisPool pool = new JedisPool(redisSlaveHost, redisSlavePort, "", redisSlavePassword);
        return new JReJSON(pool);
    }
}