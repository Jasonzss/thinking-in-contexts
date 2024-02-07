package com.jason.tics.common.security.config;


import com.jason.tics.common.security.resolver.TicsSortArgumentResolver;
import com.jason.tics.common.security.resolver.UidArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Jason
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UidArgumentResolver());
    }
}
