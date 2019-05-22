package com.austshop.austshop.entity;

import lombok.Data;

@Data
public class ResultResponse {
    private Integer code = 200;
    private String Message ;
    private Object data;

    public ResultResponse() {
    }

    public ResultResponse(Integer code, String message) {
        this.code = code;
        Message = message;
    }

    public ResultResponse(Integer code, String message, Object data) {
        this.code = code;
        Message = message;
        this.data = data;
    }
}
