package com.saltedfish.app.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public enum RedisUtil {
    INSTANCE;

    private final JedisPool pool;
    private  JedisPoolConfig config;

    RedisUtil() {
//        config = new JedisPoolConfig();
//        config.setMaxWaitMillis(10);
//        config.setMaxTotal(3000000);
//        config.setMaxIdle(10);
//        config.setTestOnBorrow(true);
        pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1",6379,3000000,"usx14322");

    }

    public void sadd(String key, String value) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.sadd(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void srem(String key, String value) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.srem(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
