package com.austshop.austshop.md5;

import org.springframework.util.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 将所有有关md5加密的方法放在这里
 */
public class PasswordMD5 {
    /**
     * 对用户密码进行加密
     * @param src  用户输入的密码
     * @param salt 为用户生成的盐
     * @return 根据用户输入的密码以及为用户生成的随机盐加密之后生成的存储到数据库的密码
     */
    public static String getEncrpytedPassword(String src, String salt) {
        // 将原密码加密
        String s1 = md5(src);
        // 将盐加密
        String s2 = md5(salt);
        // 将2次加密结果拼接，再加密
        String s3 = s1 + s2;
        String result = md5(s3);
        // 将以上结果再加密5次
        for (int i = 0; i < 5; i++) {
            result = md5(result);
        }
        // 返回
        return result;
    }
    /**
     * 获取随机生成的盐
     *
     * @return salt
     */
    public static String getRandomMD5() {
        return UUID.randomUUID().toString().toUpperCase();
    }
    /**
     * 使用MD5算法对数据进行加密
     * @param src 原文
     * @return 密文
     */
    public static String md5(String src) {
        return DigestUtils
                .md5DigestAsHex(src.getBytes())
                .toUpperCase();
    }

}
