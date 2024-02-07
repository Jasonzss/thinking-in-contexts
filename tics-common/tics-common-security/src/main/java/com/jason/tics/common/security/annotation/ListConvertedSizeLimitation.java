package com.jason.tics.common.security.annotation;

import com.jason.tics.common.jpa.converter.ListConverter;

import javax.persistence.AttributeConverter;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Jason
 */
@Target(FIELD)
@Retention(RUNTIME)
@Deprecated
public @interface ListConvertedSizeLimitation {
    /**
     * list转换为字符类型后的长度限制
     */
    int value() default 100;

    /**
     * 转换时使用的converter
     */
    Class<? extends AttributeConverter<List<?>, String>> converter() default ListConverter.class;

    String message() default "List集合转变的字符串过长";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
