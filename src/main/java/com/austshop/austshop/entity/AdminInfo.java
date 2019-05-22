package com.austshop.austshop.entity;

import lombok.Data;

@Data
public class AdminInfo {
    private Long userId;
    private String userName;
    private Integer status;
    private Integer authority;

    public AdminInfo() {
    }

    public AdminInfo(Long userId, String userName, Integer status, Integer authority) {
        this.userId = userId;
        this.userName = userName;
        this.status = status;
        this.authority = authority;
    }

}
