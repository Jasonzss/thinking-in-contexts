package com.jason.tics.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Jason
 * @since 2023/09/05 - 16:41
 */
class RedisStringTest {

    private StringRedisTemplate stringRedisTemplate;

    @BeforeAll
    public void init(){
        stringRedisTemplate = new StringRedisTemplate(
                new JedisConnectionFactory(
                        com.jason.tics.common.jedis.JedisConnectionFactory.getJedisPoolConfig()
                ));
    }

    @Test
    void testString() {
        // 写入一条String数据
        stringRedisTemplate.opsForValue().set("name", "Jason");
        // 获取string数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }
}