package com.austshop.austshop.exception;

/**
 * 字典查重失败异常
 * zpwan
 * 2019/3/19
 */
public class DictionaryCheckKeyException extends RuntimeException{
    public DictionaryCheckKeyException() {
        super();
    }

    public DictionaryCheckKeyException(String message) {
        super(message);
    }

    public DictionaryCheckKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DictionaryCheckKeyException(Throwable cause) {
        super(cause);
    }

    protected DictionaryCheckKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
