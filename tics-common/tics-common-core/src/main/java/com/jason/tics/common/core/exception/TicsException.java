package com.jason.tics.common.core.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

/**
 * @author Jason
 */
@Getter
@Setter
public class TicsException extends RuntimeException implements Supplier<TicsException> {
    //枚举出来的异常响应类型
    private ServerResponse serverResponse;
    //异常抛出的额外数据
    private Object object;

    public TicsException(String message) {
        super(message);
    }

    public TicsException(String message, Object object) {
        super(message);
        this.object = object;
    }

    public TicsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicsException(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public TicsException(ServerResponse serverResponse, Object object) {
        this.serverResponse = serverResponse;
        this.object = object;
    }

    @Override
    public TicsException get() {
        return this;
    }
}
