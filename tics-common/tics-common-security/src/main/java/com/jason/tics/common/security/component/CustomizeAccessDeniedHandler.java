package com.jason.tics.common.security.component;

import cn.hutool.json.JSONUtil;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.response.ServerResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问资源的请求因权限不够被拒绝后的处理器
 *
 * @author Jason
 */
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ServerResponseEntity<Object> fail = ServerResponseEntity.fail(ExceptionResponseEnum.NO_PERMISSION);
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSONUtil.toJsonStr(fail));
    }
}