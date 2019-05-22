package com.austshop.austshop.exception;

/**
 * zpwan
 * 2019/3/21
 */
public class TokenCheckException extends  RuntimeException {
    public TokenCheckException() {
    }

    public TokenCheckException(String message) {
        super(message);
    }

    public TokenCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenCheckException(Throwable cause) {
        super(cause);
    }

    public TokenCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
