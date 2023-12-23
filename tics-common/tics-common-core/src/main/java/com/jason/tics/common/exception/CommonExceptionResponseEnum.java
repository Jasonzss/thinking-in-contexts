package com.jason.tics.common.exception;

import cn.hutool.http.HttpStatus;

/**
 * @author Jason
 */
public enum CommonExceptionResponseEnum implements ExceptionResponse{
    /**
     * 权限相关
     */
    NO_PERMISSION("1001", HttpStatus.HTTP_UNAUTHORIZED, "无权访问此资源"),

    /**
     * 媒体资源相关
     */
    MEDIA_NOT_FOUND("2001", HttpStatus.HTTP_NOT_FOUND, "无法找到资源")


    ;

    private final String code;
    private final int httpCode;
    private String msg;

    CommonExceptionResponseEnum(String code, int httpCode, String msg) {
        this.code = code;
        this.httpCode = httpCode;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public CommonExceptionResponseEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
