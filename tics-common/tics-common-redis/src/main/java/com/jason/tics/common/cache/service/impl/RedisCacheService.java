package com.jason.tics.common.cache.service.impl;

import cn.hutool.core.util.StrUtil;
import com.jason.tics.common.cache.service.CacheService;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Jason
 */
@Slf4j
@Service
public class RedisCacheService implements CacheService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // =============================common============================
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return 是否成功
     */
    @Override
    public Boolean expire(String key, long time) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
            }
            return Boolean.TRUE;
        }
        catch (Exception e) {
             log.error("Set expire error: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    /**
     * 指定缓存失效时间
     * @param key 键
     */
    @Override
    public void cancelExpiration(String key) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        try {
            redisTemplate.expire(key, 0, TimeUnit.MILLISECONDS);
        }
        catch (Exception e) {
            log.error("Set expire error: {}", e.getMessage());
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回-1代表为永久有效 失效时间为0，说明该主键未设置失效时间（失效时间默认为-1）
     */
    @Override
    public Long getExpire(String key) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false 不存在
     */
    @Override
    public Boolean hasKey(String key) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        try {
            return redisTemplate.hasKey(key);
        }
        catch (Exception e) {
             log.error("Error getting hasKey: {}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @Override
    public void del(String... key) {
        if (key != null && key.length > 0) {
            for (String s : key) {
                if (s.contains(StrUtil.SPACE)) {
                    throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
                }
            }

            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            }
            else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    // ============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    @Override
    public <T> T get(String name, Object key) {
        return get(name+key.toString());
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<T> find(String key) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        return Optional.ofNullable((T) redisTemplate.opsForValue().get(key));
    }


    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    @Override
    public <T> Optional<T> find(String name, Object key) {
        return find(name + key.toString());
    }

    /**
     * 普通缓存获取并删除
     * @param key 键
     * @return 值
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAndDel(String key) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        return (T) redisTemplate.opsForValue().getAndDelete(key);
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    @Override
    public boolean set(String key, Object value, long time) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
            }
            else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        }
        catch (Exception e) {
             log.error("Redis opsForValue error: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false 失败
     */
    @Override
    public boolean set(String key, Object value) {
        return set(key, value, 0);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false 失败
     */
    @Override
    public boolean set(String name, Object key, Object value, long time) {
        return set(name+key.toString(), value, time);
    }

    /**
     * 递增 此时value值必须为int类型 否则报错
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return 自增后的值
     */
    @Override
    public Long incr(String key, long delta) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return 自减后的值
     */
    @Override
    public Long decr(String key, long delta) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        if (delta < 0) {
            throw new RuntimeException("递减因子必须小于0");
        }
        return stringRedisTemplate.opsForValue().increment(key, -delta);
    }

    @Override
    public boolean setLongValue(String key, Long value, long time) {
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        try {
            if (time > 0) {
                stringRedisTemplate.opsForValue().set(key, String.valueOf(value), time, TimeUnit.MILLISECONDS);
            }
            else {
                stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
            }
            return true;
        }
        catch (Exception e) {
             log.error("setLongValue() error: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    @Override
    public Long getLongValue(String key) {
        if (key == null) {
            return null;
        }
        if (key.contains(StrUtil.SPACE)) {
            throw new TicsException(ExceptionResponseEnum.INTERNAL_ERROR);
        }
        String result = stringRedisTemplate.opsForValue().get(key);
        if (result == null) {
            return null;
        }
        return Long.valueOf(result);
    }
}
