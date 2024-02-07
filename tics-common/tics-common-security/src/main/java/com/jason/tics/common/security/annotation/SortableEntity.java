package com.jason.tics.common.security.annotation;

import com.jason.tics.common.security.validator.SortableEntityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jason
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Constraint(validatedBy = SortableEntityValidator.class)
public @interface SortableEntity {
    /**
     * 需要分页查询的实体类
     */
    Class<?> entity();

    /**
     * 是否开启可排序
     */
    boolean isSortable() default true;

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
