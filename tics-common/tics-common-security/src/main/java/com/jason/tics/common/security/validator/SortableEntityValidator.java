package com.jason.tics.common.security.validator;

import com.jason.tics.common.security.annotation.Sortable;
import com.jason.tics.common.security.annotation.SortableEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * @author Jason
 */
@Getter
@Setter
public class SortableEntityValidator implements ConstraintValidator<SortableEntity, Pageable> {
    private Field[] fields;
    private boolean isSortable;

    @Override
    public void initialize(SortableEntity constraintAnnotation) {
        this.fields = constraintAnnotation.entity().getFields();
        this.isSortable = constraintAnnotation.isSortable();
    }

    @Override
    public boolean isValid(Pageable value, ConstraintValidatorContext context) {
        if (!isSortable && !value.getSort().isEmpty()){
            //不开启排序功能的时候传入排序参数，直接判错
            context.buildConstraintViolationWithTemplate("不支持排序功能").addConstraintViolation();
            return false;
        }

        //判断排序参数是否合法
        for (Sort.Order order : value.getSort()) {
            for (Field field : fields) {
                if (field.getName().equals(order.getProperty()) && !field.isAnnotationPresent(Sortable.class)) {
                    context.buildConstraintViolationWithTemplate("不合法的排序参数")
                            .addConstraintViolation();
                    return false;
                }
            }
        }

        return true;
    }
}
