package com.austshop.austshop.exception;

/**
 * 查询失败异常
 * zpwan
 * 2019/3/19
 */
public class OperationFailException extends RuntimeException{
    public OperationFailException() {
    }

    public OperationFailException(String message) {
        super(message);
    }

    public OperationFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationFailException(Throwable cause) {
        super(cause);
    }

    public OperationFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
