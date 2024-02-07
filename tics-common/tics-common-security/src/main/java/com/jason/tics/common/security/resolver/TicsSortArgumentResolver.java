package com.jason.tics.common.security.resolver;

import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.common.security.annotation.SortableEntity;
import com.jason.tics.common.security.annotation.Sortable;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author Jason
 */
@Deprecated
public class TicsSortArgumentResolver extends PageableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return super.supportsParameter(parameter) && parameter.hasParameterAnnotation(SortableEntity.class);
    }

    @NonNull
    @Override
    public Pageable resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        //判断排序参数是否合法
        Pageable pageable = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        Field[] fields = Objects.requireNonNull(parameter.getParameterAnnotation(SortableEntity.class)).entity().getDeclaredFields();

        for (Sort.Order order : pageable.getSort()) {
            for (Field field : fields) {
                if (field.getName().equals(order.getProperty()) && !field.isAnnotationPresent(Sortable.class)) {
                    throw new TicsException(ExceptionResponseEnum.SORT_PARAM_UN_SUPPORTED);
                }
            }
        }
        return pageable;
    }
}
