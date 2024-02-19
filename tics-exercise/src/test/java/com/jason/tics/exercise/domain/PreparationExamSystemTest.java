package com.jason.tics.exercise.domain;

import com.jason.tics.exercise.ExerciseApplication;
import com.jason.tics.exercise.domain.exam.PreparationExamSystem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Jason
 */
@SpringBootTest(classes = ExerciseApplication.class)
@Slf4j
public class PreparationExamSystemTest {
    @Autowired
    private PreparationExamSystem system;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test(){
        log.info("开始");
        Boolean name = redisTemplate.expire("name", 20, TimeUnit.SECONDS);
        log.info("删除："+name);
    }
}
