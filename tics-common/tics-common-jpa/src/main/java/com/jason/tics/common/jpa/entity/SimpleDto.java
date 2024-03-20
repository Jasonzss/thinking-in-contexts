package com.jason.tics.common.jpa.entity;

import cn.hutool.core.bean.BeanUtil;

/**
 * 使用 {@link cn.hutool.core.bean.BeanUtil} 进行字段复制
 * 可以省去自己实现getSource方法，但是面对字段有较多不同的pojo还是自己实现较好
 * @author Jason
 */
public abstract class SimpleDto<ID, T> implements Dto<ID, T>{
    private final Class<T> clazz;

    public SimpleDto() {
        this.clazz = getSourceClass();
    }

    public abstract Class<T> getSourceClass();

    @Override
    public T getSource() {
        return BeanUtil.copyProperties(this, clazz);
    }
}
