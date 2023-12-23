package com.jason.tics.common.exception;

/**
 * @author Jason
 */
public interface ExceptionResponse {
    /**
     * 获取错误编号
     * @return code
     */
    String getCode();

    /**
     * 获取信息
     * @return msg
     */
    String getMsg();

    /**
     * 获取异常对应的http响应码
     * @return http响应码
     */
    int getHttpCode();

    /**
     * 设置自定义异常信息
     * @param msg 信息
     */
    ExceptionResponse setMsg(String msg);
}
