package com.jason.tics.common.security.config;

import com.jason.tics.common.security.adapter.PublicUrlsAdapter;
import com.jason.tics.common.security.component.CustomizeAccessDeniedHandler;
import com.jason.tics.common.security.component.ResourceAuthExceptionEntryPoint;
import com.jason.tics.common.security.filter.JwtAuthenticationTokenFilter;
import com.jason.tics.common.security.service.SecuritySecurityCheckService;
import com.jason.tics.common.security.service.TicsUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Jason
 */
//Spring Security框架开启
@EnableWebSecurity
//授权全局配置
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {
    @Autowired
    private TicsUserDetailsService ticsUserDetailsService;

    @Autowired
    private PublicUrlsAdapter publicUrlsAdapter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //支持跨域
        http.cors().and()
                //csrf关闭
                .csrf().disable()
                //不使用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(
                        //设置白名单url
                        rep -> rep.antMatchers(publicUrlsAdapter.listUrlsWithNoAuthentication().toArray(new String[0]))
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .exceptionHandling()
                // AuthenticationException 异常处理
                .authenticationEntryPoint(new ResourceAuthExceptionEntryPoint())
                // AccessDeniedException 异常处理
                .accessDeniedHandler(new CustomizeAccessDeniedHandler())
                .and()
                //token过滤
                .addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                //自定义的用户信息获取服务
                .userDetailsService(ticsUserDetailsService);

        return http.build();
    }


    /**
     * 获取AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * 密码加密解密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("ssc")
    public SecuritySecurityCheckService permissionService() {
        return new SecuritySecurityCheckService();
    }

}
