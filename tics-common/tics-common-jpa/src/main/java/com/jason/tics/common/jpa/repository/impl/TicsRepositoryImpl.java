package com.jason.tics.common.jpa.repository.impl;

import cn.hutool.core.util.ReflectUtil;
import com.google.gson.internal.Primitives;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.common.jpa.entity.Dto;
import com.jason.tics.common.jpa.repository.TicsRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.annotation.Nullable;
import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jason
 */
public class TicsRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements TicsRepository<T, ID> {
    public TicsRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
    }

    /**
     * 修改对象的部分属性
     * @param id 被修改对象的id
     * @param attrs 待修改部分属性，attr的名称决定了修改的字段
     * @return 返回更新后的对象，如果为null则代表源对象不存在，无法更新
     */
    @Override
    public T partialUpdate(ID id, Map<String, Object> attrs) {
        T entity = findById(id).orElse(null);
        if (entity != null) {
            Class<?> clazz = entity.getClass();
            for (Map.Entry<String, Object> entry : attrs.entrySet()) {
                String attributeName = entry.getKey();
                Object value = entry.getValue();

                //查看是否存在同名字段
                if (ReflectUtil.hasField(clazz, attributeName)) {
                    try {
                        Field field = clazz.getField(attributeName);
                        if (field.getType() == value.getClass()){
                            //类型相同直接嵌入
                            ReflectUtil.setFieldValue(entity, field, value);
                        }
                    } catch (NoSuchFieldException e) {
                        throw new TicsException(ExceptionResponseEnum.FIELD_NOT_EXISTS);
                    }
                }else{
                    //不存在同名字段，去内嵌复杂类中查找
                    for (Field field : clazz.getFields()) {
                        if (Primitives.isWrapperType(field.getType())) {
                            continue;
                        }

                        if (ReflectUtil.hasField(field.getType(), attributeName)) {
                            try {
                                Field f = field.getType().getField(attributeName);
                                if (f.getType() == value.getClass()){
                                    ReflectUtil.setFieldValue(entity, f, value);
                                }
                            } catch (NoSuchFieldException e) {
                                throw new TicsException(ExceptionResponseEnum.FIELD_NOT_EXISTS);
                            }
                        }
                    }
                }
            }
            // 保存实体更新
            save(entity);
        }
        return entity;
    }

    @Deprecated
    @Nullable
    private Method getGetter(Class<?> clazz, String field){
        Method m = null;

        for (Method method : clazz.getDeclaredMethods()) {
            String name = method.getName();
            if (!"getClass".equals(name)) {
                if (method.getParameterCount() == 0) {
                    if (name.startsWith("get")){
                        if (name.substring(3).equalsIgnoreCase(field)) {
                            m = method;
                        }
                    }else if (name.startsWith("is")){
                        if (name.substring(2).equalsIgnoreCase(field)) {
                            m = method;
                        }
                    }
                }
            }
        }

        return m;
    }

    @Override
    public T addByPojo(Dto<ID, T> dto) {
        return save(dto.getSource());
    }

    @Override
    public T updateByPojo(ID id, Dto<ID, T>  dto) {
        T source = dto.getSource();
        ReflectUtil.setFieldValue(source, getIdField(source), id);
        return save(source);
    }

    private Field getIdField(T source){
        for (Field d : source.getClass().getDeclaredFields()) {
            if (d.isAnnotationPresent(Id.class) || d.isAnnotationPresent(EmbeddedId.class)) {
                return d;
            }
        }

        throw new TicsException(ExceptionResponseEnum.ID_NOT_FOUND);
    }
}
