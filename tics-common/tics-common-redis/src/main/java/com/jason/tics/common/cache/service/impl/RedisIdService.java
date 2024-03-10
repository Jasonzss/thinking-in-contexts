package com.jason.tics.common.cache.service.impl;

import com.jason.tics.common.cache.service.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 */
@Slf4j
@Service
public class RedisIdService implements IdService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Long getIncreaseId(String key){
        return getIncreaseId(key, 1);
    }

    public Long getIncreaseId(String key, int step){
        //利用increment即redis原生incrBy命令的原子性特性生成递增的序列号
        Long increment = redisTemplate.opsForValue().increment(key, step);
        if (increment == null) {
            throw new RuntimeException("redis命令执行失败");
        }
        //业务id+递增id，如果需要纯数字，去掉业务id即可
        return increment;
    }

    public List<Long> getMultiIncreaseId(String key, int num){
        Long increment = redisTemplate.opsForValue().increment(key, num);
        if (increment == null) {
            throw new RuntimeException("redis命令执行失败");
        }
        long begin = increment - Long.parseLong(num + "");
        List<Long> rs = new ArrayList<>();
        for (long i = begin + 1; i <= increment; i++) {
            rs.add(i);
        }
        return rs;
    }
}
