package com.austshop.austshop.controller;

import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.impl.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员模块控制层
 * RestController = ResponseBody + Controller
 */
@RestController
@CrossOrigin
@RequestMapping(value="/usermodule")
public class AdminUserController {

    //    自动装配
    @Autowired
    private IAdminService iAdminService;

    @PostMapping(value="/admin/add")
    public ResultResponse handleAdminAdd(
            @RequestParam(value="userId",required = true) long userId
    ){
        iAdminService.getAdminInsert(userId);
        return new ResultResponse(200,"新增成功","SUCCESS");
    }


    // @RequestBody此注解用于接收前端发送的json数据 得到的数据如下{"userName":"万直鹏"}
    //RequestParam前端处理json数据然后再发送到后端
    @PostMapping(value = "/admin/login")
    public ResultResponse handleAdminUserLogin(@RequestParam(value = "username", required = true) String username,
                                               @RequestParam(value = "password", required = true) String password,
                                               ModelMap modelMap
    ) {
        modelMap = iAdminService.adminlogin(username, password);

        return new ResultResponse(200, "SUCCESS", modelMap);
    }


    /**
     * 按照状态获取管理员列表
     *
     * @param status      状态（非必填）
     * @param limit       限制数据条数（非必填）
     * @param currentpage 当前页数（非必填）
     * @return 管理员列表
     */
    @GetMapping(value = "/admin/list")
    public ResultResponse getAdminUserList(@RequestParam(value = "status", defaultValue = "2") Integer status,
                                           @RequestParam(value = "currentpage", defaultValue = "1") Integer currentpage,
                                           @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                           ModelMap modelMap
    ) {
        modelMap = iAdminService.getadminlist(limit, currentpage, status);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * 修改用户状态
     *
     * @param userId 用户id
     * @param status 用户状态
     * @return 整数
     */
    @PostMapping(value = "/admin/status")
    public ResultResponse changeAdminUserStatus(@RequestParam(value = "userId", required = true) Long userId,
                                                @RequestParam(value = "status", required = true) Integer status) {
        iAdminService.changeadminstatus(userId, status);
        return new ResultResponse(200, "修改成功","SUCCESS");
    }

    /**
     * 删除管理员
     *
     * @param userId 管理员id
     * @return json数据
     */
    @GetMapping(value = "/admin/delete")
    public ResultResponse handleAdminDelete(@RequestParam(value = "userId", required = true) Long userId) {
        iAdminService.deleteAdmin(userId);
        return new ResultResponse(200, "删除成功","SUCCESS");
    }

}

