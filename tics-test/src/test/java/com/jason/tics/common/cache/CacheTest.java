package com.jason.tics.common.cache;

import com.jason.tics.test.TestApplication;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author Jason
 */
@SpringBootTest(classes = TestApplication.class)
@Slf4j
public class CacheTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testStringRedisTemplate(){
        // 写入一条String数据
        stringRedisTemplate.opsForValue().set("name", "suck");
        // 获取string数据
        String name = stringRedisTemplate.opsForValue().get("name");
        log.info("name = " + name);
    }

    @Test
    public void testRedisTemplate(){
        // 写入一条数据
        redisTemplate.opsForValue().set("user", new User(1L, "dick"));
        // 获取string数据
        Object name = redisTemplate.opsForValue().get("user");
        log.info("user = " + name);
    }

    @Test
    public void testTransactionRedisTemplate(){
        try{
            List<?> execute = stringRedisTemplate.execute(new SessionCallback<List<?>>() {
                @Override
                public List<?> execute(@NonNull RedisOperations operations) throws DataAccessException {
                    operations.multi();
                    // 写入一条String数据
                    operations.opsForValue().set("name", "ff");
                    // 获取string数据
                    operations.opsForValue().get("name");
                    //抛出异常则不会进行以上操作
                    int i = 1/0;
                    return operations.exec();
                }
            });

            log.info("user = " + execute);
        }catch (ArithmeticException e){
            log.info("抛出异常则不会进行以上操作");
        }

    }

    @Data
    public static class User {
        private Long id;
        private String name;

        public User() {
        }

        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
