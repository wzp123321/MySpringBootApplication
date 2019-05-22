package com.austshop.austshop.controller;

import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.Utils.MD5Utils;
import com.austshop.austshop.entity.Car;
import com.austshop.austshop.entity.Order;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.entity.User;
import com.austshop.austshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * zpwan
 * 2019/3/28
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/usermodule")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 系统用户注册
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/user/register")
    public ResultResponse handleUserRegister(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
        userService.insertNewUser(username, password);
        return new ResultResponse(200, "注册成功", "SUCCESS");
    }

    /**
     * 系统用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = "/user/login")
    public ResultResponse handleUserLogin(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            ModelMap modelMap
    ) {
        modelMap = userService.getUserlogin(username, password);
        return new ResultResponse(200, "登录成功", modelMap);
    }

    /**
     * 修改用户密码
     *
     * @param oldpassword
     * @param newpassword
     * @param request
     * @return
     */
    @PostMapping(value = "/user/changepwd")
    public ResultResponse handlePwdChange(
            @RequestParam(value = "oldpassword", required = true) String oldpassword,
            @RequestParam(value = "newpassword", required = true) String newpassword,
            HttpServletRequest request
    ) {
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);
        userService.changeUserPwd(oldpassword, newpassword, userId);
        return new ResultResponse(200, "修改成功", "SUCCESS");
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("/user/info")
    public ResultResponse getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);
        User user = userService.getUserByUserId(userId);
        return new ResultResponse(200, "查询成功", user);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/user/infobyid")
    public ResultResponse getUserInfo(
            @RequestParam(value="userId",required = true) long userId
    ) {
        User user = userService.getUserByUserId(userId);
        return new ResultResponse(200, "查询成功", user);
    }

    /**
     * 用户编辑
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping(value = "/user/edit")
    public ResultResponse handleEditUserInfo(User user, HttpServletRequest request) {
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);
        userService.editUserInfo(user, userId);
        return new ResultResponse(200, "修改成功", "SUCCESS");
    }


    /**
     * 校验用户实名
     * @param request
     * @return
     */
    @GetMapping(value = "/user/checkreal")
    public ResultResponse handleCheckUserReal(HttpServletRequest request) {
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);
        boolean flag = userService.checkUserReal(userId);
//        flag为true说明已实名认证
        return new ResultResponse(200,"SUCCESS",flag);
    }

    /**
     * 获取用户所有的车辆
     * @param request
     * @return
     */
    @GetMapping(value="/user/getowncar")
    public  ResultResponse handleUserOwnerCar(
            HttpServletRequest request
    ){
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);

        List<Car> cars = userService.getUserOwnercars(userId);

        return new ResultResponse(200,"查询成功",cars);
    }

    /**
     *获取用户所有的订单
     * @param request
     * @return
     */
    @GetMapping(value="/user/getorders")
    public  ResultResponse handleUserOrder(
            HttpServletRequest request
    ){
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);

        List<Order> orders = userService.getUserOrders(userId);

        return new ResultResponse(200,"查询成功",orders);
    }
}
