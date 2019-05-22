package com.austshop.austshop.service;

import com.austshop.austshop.Utils.MD5Utils;
import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.entity.Admin;
import com.austshop.austshop.entity.AdminInfo;
import com.austshop.austshop.entity.User;
import com.austshop.austshop.exception.AdminNotFoundException;
import com.austshop.austshop.exception.PasswordNotMatchException;
import com.austshop.austshop.exception.OperationFailException;
import com.austshop.austshop.exception.UserExistException;
import com.austshop.austshop.mapper.AdminMapper;
import com.austshop.austshop.mapper.UserMapper;
import com.austshop.austshop.service.impl.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * zpwan
 * 2019/3/18
 */
@Service("adminService")
public class AdminService implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    //    当前时间戳
    SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @Override
    public void getAdminInsert(long userId) {
        User user =userMapper.getUserByuserId(userId);
        Admin admin = adminMapper.selectAdminByUserName(user.getUserName());
        if(admin == null){
            if(user!=null){
                Integer result = adminMapper.getadminadd(user.getUserName(),user.getPhoneNumber(),user.getPassword(),user.getSalt(),dformat.format(new Date()));
                if(result !=1){
                    throw new OperationFailException();
                }
            }else{
                throw new UserExistException();
            }
        }
    }

    @Override
    public ModelMap adminlogin(String userName, String password) throws AdminNotFoundException, PasswordNotMatchException {
        ModelMap modelMap = new ModelMap();
        //        验证用户是否存在
        Admin admin = adminMapper.selectAdminByUserName(userName);
        if (admin != null) {
            //            获取用户盐
            String salt = admin.getSalt();
            //            获取用户加密的密码
            String md5pwd = admin.getPassword();
            //           对输入的密码加密在于原密码进行比对
            if (md5pwd.equals(MD5Utils.getEncrpytedPassword(password, salt))) {
                //               根据用户名为用户生成一个定期的token
                String token = JsonWebTokenUtils.createToken(admin.getUserId());
                modelMap.put("data", admin);
                modelMap.put("token", token);

                return modelMap;
            } else {
                throw new PasswordNotMatchException();
            }
        } else {
            throw new AdminNotFoundException();
        }
    }

    @Override
    public ModelMap getadminlist(Integer limit, Integer currentpage, Integer status) {
        List<AdminInfo> admins = new ArrayList<>();
        ModelMap modelMap = new ModelMap();
        Integer total = 0;
        if (status == 2) {
            admins = adminMapper.getAdminInfoList((currentpage - 1) * limit, limit);
            total = adminMapper.gettotals();
        } else {
            admins = adminMapper.getAdminInfoListByStatus((currentpage - 1) * limit, limit, status);
            total = adminMapper.getstatustotals(status);
        }
        if (admins != null) {
            modelMap.put("data", admins);
            modelMap.put("total", total);
            return modelMap;
        } else {
            throw new OperationFailException();
        }
    }

    @Override
    public void changeadminstatus(Long userId, Integer status) {
//        根据用户状态改变状态
        status = status == 1 ? 0 : 1;
        Integer num = adminMapper.changeAdminStatus(status, userId, dformat.format(new Date()));
        if (num != 1) {
            throw new OperationFailException();
        }
    }

    @Override
    public void deleteAdmin(Long userId) {
        Integer num = adminMapper.getAdminDelete(userId, dformat.format(new Date()));
        if (num != 1) {
            throw new OperationFailException();
        }
    }
}
