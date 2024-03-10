package com.jason.tics.common.cache.service;

import java.util.Optional;

/**
 * @author Jason
 */
public interface CacheService {
    Boolean expire(String key, long time);

    /**
     * 指定缓存失效时间
     * @param key 键
     */
    void cancelExpiration(String key);

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回-1代表为永久有效 失效时间为0，说明该主键未设置失效时间（失效时间默认为-1）
     */
    Long getExpire(String key);

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false 不存在
     */
    Boolean hasKey(String key);
    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    void del(String... key);

    // ============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    <T> T get(String key);

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    <T> T get(String name, Object key);

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    <T> Optional<T> find(String key);


    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    <T> Optional<T> find(String name, Object key);

    /**
     * 普通缓存获取并删除
     * @param key 键
     * @return 值
     */
    <T> T getAndDel(String key);

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    boolean set(String key, Object value, long time);

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false 失败
     */
    boolean set(String key, Object value);

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false 失败
     */
    boolean set(String name, Object key, Object value, long time);
    /**
     * 递增 此时value值必须为int类型 否则报错
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return 自增后的值
     */
    Long incr(String key, long delta);

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return 自减后的值
     */
    Long decr(String key, long delta);

    boolean setLongValue(String key, Long value, long time);

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    Long getLongValue(String key);
}
