package com.jason.tics.dictionary.components;

import com.jason.tics.dictionary.service.QueryStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Jason
 */
@Component
public class DictionaryCacheKeyGenerator implements KeyGenerator {
    @Autowired
    private QueryStringService queryStringService;

    @NonNull
    @Override
    public Object generate(@NonNull Object target, @NonNull Method method, Object... params) {
        return queryStringService.handleQueryString((String) params[0]);
    }
}
