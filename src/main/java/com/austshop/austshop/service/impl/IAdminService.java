package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.Admin;
import com.austshop.austshop.entity.AdminInfo;
import com.austshop.austshop.exception.AdminNotFoundException;
import com.austshop.austshop.exception.PasswordNotMatchException;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * zpwan
 * 2019/3/19
 */
public interface IAdminService {

    /**
     * 新增管理员
     * @param userId
     * @return
     */
    void getAdminInsert(long userId);

    /**
     * 管理员登录
     * @param userName 用户名
     * @param password 密码
     * @return 管理员对象
     * @throws AdminNotFoundException
     * @throws PasswordNotMatchException
     */
    ModelMap adminlogin(String userName , String password) throws AdminNotFoundException,PasswordNotMatchException;

    /**
     * 获取管理员列表
     * @param limit
     * @param currentpage
     * @param status
     * @return
     */
    ModelMap getadminlist(Integer limit, Integer currentpage, Integer status);

    /**
     * 修改管理员状态
     * @param userId
     * @param status
     */
    void changeadminstatus(Long userId , Integer status);

    /**
     *
     * @param userId
     */
    void deleteAdmin(Long userId);
}
