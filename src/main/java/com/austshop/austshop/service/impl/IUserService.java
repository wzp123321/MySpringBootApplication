package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.Car;
import com.austshop.austshop.entity.Order;
import com.austshop.austshop.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * zpwan
 * 2019/3/28
 */
public interface IUserService {


    /**
     * 系统用户注册
     * @param userName
     * @param password
     * @return
     */
    public void insertNewUser(String userName, String password);

    /**
     * 系统用户登录
     * @param userName
     * @param password
     * @return
     */
    public ModelMap getUserlogin(String userName, String password);

    /**
     * 修改用户密码
     * @param oldpwd
     * @param newpwd
     * @param userId
     * @return
     */
    public Integer changeUserPwd(String oldpwd,String newpwd,Long userId);

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    public User getUserByUserId(Long userId);

    /**
     * 编辑用户
     * @param user
     * @return
     */
    public Integer editUserInfo(User user,Long userId);

    /**
     * 校验用户是否实名
     * @param userId
     * @return
     */
    public boolean checkUserReal(Long userId);

    /**
     * 获取用户所有车辆
     * @param userId
     * @return
     */
    public List<Car> getUserOwnercars(long userId);

    /**
     * 获取用户所有订单
     * @param userId
     * @return
     */
    public List<Order> getUserOrders(long userId);

}
