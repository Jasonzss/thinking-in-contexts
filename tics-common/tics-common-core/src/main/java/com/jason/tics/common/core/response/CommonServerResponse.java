package com.jason.tics.common.core.response;

import cn.hutool.http.HttpStatus;
import com.jason.tics.common.core.exception.ServerResponse;

/**
 * @author Jason
 */
public enum CommonServerResponse implements ServerResponse {
    /**
     * 请求成功
     */
    OK("0000", HttpStatus.HTTP_OK, "ok")

    ;

    private final String code;
    private final int httpCode;
    private String msg;

    CommonServerResponse(String code, int httpCode, String msg) {
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
    public CommonServerResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
