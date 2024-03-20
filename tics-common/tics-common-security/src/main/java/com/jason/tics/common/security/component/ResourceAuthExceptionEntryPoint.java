package com.jason.tics.common.security.component;

import cn.hutool.json.JSONUtil;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证失败的处理器
 * @author Jason
 */
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ServerResponseEntity<Object> fail = ServerResponseEntity.fail(ExceptionResponseEnum.USER_NOT_LOGIN);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(fail));
    }
}