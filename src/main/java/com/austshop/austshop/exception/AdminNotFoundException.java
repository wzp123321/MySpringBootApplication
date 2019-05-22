package com.austshop.austshop.exception;

/**
 * 用户不存在异常
 * zpwan
 * 2019/3/19
 */
public class AdminNotFoundException extends  RuntimeException{
    public AdminNotFoundException() {
    }

    public AdminNotFoundException(String message) {
        super(message);
    }

    public AdminNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminNotFoundException(Throwable cause) {
        super(cause);
    }

    public AdminNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
