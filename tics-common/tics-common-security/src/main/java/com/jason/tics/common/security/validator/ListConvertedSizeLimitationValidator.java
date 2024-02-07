package com.jason.tics.common.security.validator;

import com.jason.tics.common.core.utils.SpringContextUtil;
import com.jason.tics.common.security.annotation.ListConvertedSizeLimitation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import javax.persistence.AttributeConverter;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author Jason
 */
@Deprecated
public class ListConvertedSizeLimitationValidator implements
        ConstraintValidator<ListConvertedSizeLimitation, List<?>> {
    private Integer max;
    private AttributeConverter<List<?>, String> converter;

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        return converter.convertToDatabaseColumn(value).length() <= max;
    }

    @Override
    public void initialize(ListConvertedSizeLimitation constraintAnnotation) {
        this.max = constraintAnnotation.value();
        converter = SpringContextUtil.getBean(constraintAnnotation.converter());
    }
}
