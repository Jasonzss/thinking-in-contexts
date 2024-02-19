package com.jason.tics.common.core.exception;

import cn.hutool.http.HttpStatus;

/**
 * @author Jason
 */
public enum ExceptionResponseEnum implements ServerResponse {
    /**
     * 用于直接显示提示用户的错误，内容由输入内容决定
     */
    SHOW_FAIL("1001", HttpStatus.HTTP_BAD_REQUEST, ""),

    /**
     * 权限相关
     */
    NO_PERMISSION("2001", HttpStatus.HTTP_UNAUTHORIZED, "无权访问此资源"),

    /**
     * 媒体资源相关
     */
    MEDIA_NOT_FOUND("3001", HttpStatus.HTTP_NOT_FOUND, "无法找到资源"),

    /**
     * 数据库相关
     */
    NOT_SUPPORTED_ID_GENERATE_ENTITY("4001", HttpStatus.HTTP_INTERNAL_ERROR, "该实体不兼容此id生成器"),

    /**
     * 请求参数相关
     */
    BAD_URL_PARAM("5001", HttpStatus.HTTP_BAD_REQUEST, "不支持的请求参数"),
    INVALID_REQUEST_PARAM("5002", HttpStatus.HTTP_BAD_REQUEST, "非法请求参数"),
    SORT_PARAM_UN_SUPPORTED("5003", HttpStatus.HTTP_BAD_REQUEST, "不支持的排序参数"),

    /**
     * 内部开发代码有误
     */
    INTERNAL_ERROR("6001", HttpStatus.HTTP_INTERNAL_ERROR, "网站发生了错误"),


    DEVELOPMENT_IN_PROGRESS("7001", HttpStatus.HTTP_INTERNAL_ERROR, "此功能开发中"),

    /**
     * 增删改查相关
     */
    RESOURCE_NOT_FOUND("8001", HttpStatus.HTTP_NOT_FOUND, "找不到该资源"),
    UNCHANGEABLE_FIELD("8002",HttpStatus.HTTP_BAD_REQUEST, "该字段无法被修改"),
    RESOURCE_ALREADY_EXISTS("8003",HttpStatus.HTTP_CONFLICT, "该资源已存在，无法新增"),

    /**
     * 练习模块
     */
    QUESTION_NOT_EXIST("9001", HttpStatus.HTTP_NOT_FOUND, "不存在的题目")


    ;

    private final String code;
    private final int httpCode;
    private String msg;

    ExceptionResponseEnum(String code, int httpCode, String msg) {
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
    public ExceptionResponseEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
