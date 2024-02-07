package com.jason.tics.dictionary.constraints;

import com.jason.tics.dictionary.validator.WordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 判断字符串是否为单词
 * @see com.jason.tics.dictionary.service.DictionaryService isWord(String query)
 * @author Jason
 */
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WordValidator.class)
public @interface IsWord {
    String message() default "不存在的单词";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
