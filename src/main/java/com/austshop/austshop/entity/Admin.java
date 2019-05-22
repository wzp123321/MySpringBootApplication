package com.austshop.austshop.entity;

import lombok.Data;

@Data
public class Admin {
    private Long userId;
    private String userName;
    private String phoneNumber;
    private String password;
    private String salt;
    private Integer status;
    private Integer authority;
    private String modifyTime;

    public Admin() {

    }

    public Admin(Long userId, String userName, String password, String salt, Integer status, Integer authority, String modifyTime) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.status = status;
        this.authority = authority;
        this.modifyTime = modifyTime;
    }


}
