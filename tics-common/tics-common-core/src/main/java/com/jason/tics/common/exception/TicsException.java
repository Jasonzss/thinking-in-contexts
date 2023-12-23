package com.jason.tics.common.exception;

/**
 * @author Jason
 */
public class TicsException extends RuntimeException {
    private final ExceptionResponse exceptionResponse;

    public ExceptionResponse getExceptionResponse() {
        return exceptionResponse;
    }

    public TicsException(ExceptionResponse exceptionResponse) {
        super(exceptionResponse.getMsg());
        this.exceptionResponse = exceptionResponse;
    }

    public TicsException(String message, ExceptionResponse exceptionResponse) {
        super(message);
        this.exceptionResponse = exceptionResponse;
    }

    public TicsException(String message, Throwable cause, ExceptionResponse exceptionResponse) {
        super(message, cause);
        this.exceptionResponse = exceptionResponse;
    }

    public TicsException(Throwable cause, ExceptionResponse exceptionResponse) {
        super(cause);
        this.exceptionResponse = exceptionResponse;
    }

    public TicsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExceptionResponse exceptionResponse) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionResponse = exceptionResponse;
    }
}
