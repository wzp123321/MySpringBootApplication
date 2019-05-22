package com.austshop.austshop.entity;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String userName;
    private String trueName;
    private String password;
    private String salt;
    private String phoneNumber;
    private String userAvatar;
    private Integer gender;
    private String email;
    private String work;
    private String birthday;
    private String description;
    private String address;
    private String Alipayaccount;
    private double balance;
    private String identityNo;
    private long regTime;
    private long modifyTime;

//    构造方法
    public User() {
    }

    public User(Long userId, String userName, String trueName, String password, String salt, String phoneNumber, String userAvatar, Integer gender, String email, String work, String birthday, String description, String address, String alipayaccount,
                double balance, String identityNo, long regTime, long modifyTime) {
        this.userId = userId;
        this.userName = userName;
        this.trueName = trueName;
        this.password = password;
        this.salt = salt;
        this.phoneNumber = phoneNumber;
        this.userAvatar = userAvatar;
        this.gender = gender;
        this.email = email;
        this.work = work;
        this.birthday = birthday;
        this.description = description;
        this.address = address;
        Alipayaccount = alipayaccount;
        this.balance = balance;
        this.identityNo = identityNo;
        this.regTime = regTime;
        this.modifyTime = modifyTime;
    }
}
