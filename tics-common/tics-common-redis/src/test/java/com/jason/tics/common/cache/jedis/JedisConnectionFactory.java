package com.jason.tics.common.cache.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author Jason
 * @since 2023/09/05 - 12:23
 */
public class JedisConnectionFactory {

    private static final JedisPool JEDIS_POOL;
    private static final JedisPoolConfig poolConfig = new JedisPoolConfig();

    static {
        // 配置连接池
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWait(Duration.ofMillis(1000));

        // 创建连接池对象，参数：连接池配置、服务端ip、服务端端口、超时时间、密码
        JEDIS_POOL = new JedisPool(poolConfig, "127.0.0.1", 6379, 5000, "123456");
    }

    public static Jedis getJedis(){
        return JEDIS_POOL.getResource();
    }

    public static JedisPoolConfig getJedisPoolConfig(){
        return poolConfig;
    }
}
