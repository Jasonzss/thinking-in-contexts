package com.jason.tics.common.jpa.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ReflectUtil;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.common.jpa.annotation.Unchangeable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jason
 */
public class JpaCrudUtil {
    /**
     * 打上这些标签的属性无法被修改
     * 不过这个功能可以在@Column注解中定义
     */
    private static final Set<Class<? extends Annotation>> UNCHANGEABLE_ANNOTATIONS = new HashSet<>();

    static {
        UNCHANGEABLE_ANNOTATIONS.add(Unchangeable.class);
        UNCHANGEABLE_ANNOTATIONS.add(CreationTimestamp.class);
        UNCHANGEABLE_ANNOTATIONS.add(UpdateTimestamp.class);
    }


    /**
     * 把更新实体的逻辑抽象出来的业务方法，称为Util可能不太好，但问题不大
     * @param origin 源对象，即更新后字段的来源
     * @param id 被修改对象的id
     * @param repository 被修改对象对应的JpaRepository
     * @param <T> 被修改对象的泛型
     * @param <ID> 被修改对象id的泛型
     * @return 修改后的对象
     */
    public static <T, ID> T updateResource(T origin, ID id, JpaRepository<T, ID> repository){
        checkUpdateParam(origin);
        T t = repository.findById(id).orElseThrow(new TicsException(ExceptionResponseEnum.RESOURCE_NOT_FOUND));
        BeanUtil.copyProperties(origin, t, CopyOptions.create(origin.getClass(), true));
        return repository.save(t);
    }

    public static <T, ID, DTO> T updateResource(Class<T> clazz, ID id, JpaRepository<T, ID> repository, DTO dto){
        return updateResource(dtoTransform(clazz, dto), id, repository);
    }

    private static <T> void checkUpdateParam(T origin){
        for (Field field : origin.getClass().getFields()) {
            for (Class<? extends Annotation> unchangeableAnnotation : UNCHANGEABLE_ANNOTATIONS) {
                if (field.isAnnotationPresent(unchangeableAnnotation)) {
                    throw new TicsException(ExceptionResponseEnum.UNCHANGEABLE_FIELD);
                }
            }
        }
    }

    private static <T, DTO> T dtoTransform(Class<T> clazz, DTO dto){
        T t = ReflectUtil.newInstance(clazz);

        for (Field field : dto.getClass().getFields()) {
            for (Field clazzField : clazz.getFields()) {
                if(field.getName().equals(clazzField.getName())){
                    ReflectUtil.setFieldValue(t, clazzField, ReflectUtil.getFieldValue(dto, field));
                }
            }
        }

        return t;
    }
}
