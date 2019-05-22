package com.austshop.austshop.service;

import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.Utils.MD5Utils;
import com.austshop.austshop.entity.Car;
import com.austshop.austshop.entity.Order;
import com.austshop.austshop.entity.User;
import com.austshop.austshop.exception.AdminNotFoundException;
import com.austshop.austshop.exception.OperationFailException;
import com.austshop.austshop.exception.PasswordNotMatchException;
import com.austshop.austshop.exception.UserExistException;
import com.austshop.austshop.mapper.CarMapper;
import com.austshop.austshop.mapper.OrderMapper;
import com.austshop.austshop.mapper.UserMapper;
import com.austshop.austshop.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * zpwan
 * 2019/3/28
 */
@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
            private CarMapper carMapper;

    @Autowired
            private OrderMapper orderMapper;

    //    当前时间戳
    SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    // 当前日期
    long date = (new Date()).getTime();

    @Override
    public void insertNewUser(String userName, String password) {
        User user = userMapper.checkUserExist(userName);
        if (user == null) {
            String userAvatar = "http://127.0.0.1:9898/C8C1B3F780DBC2A19D67A31EE5A51000.jpg";
//            随机生成md5
            String salt = MD5Utils.getRandomMD5();
//            对输入的密码和md5一起加密
            String md5password = MD5Utils.getEncrpytedPassword(password, salt);
//
            Integer result = userMapper.getUserInsert(userName, salt, md5password, userAvatar, date, date);
            if (result != 1) {
                throw new OperationFailException();
            }
        } else {
            throw new UserExistException();
        }
    }

    @Override
    public ModelMap getUserlogin(String userName, String password) {
        ModelMap modelMap = new ModelMap();
//        JwtUtils jwtUtils = new JwtUtils();
        //        验证用户是否存在
        User user = userMapper.checkUserExist(userName);
        if (user != null) {
            //            获取用户盐
            String salt = user.getSalt();
            //            获取用户加密的密码
            String md5pwd = user.getPassword();
            //           对输入的密码加密在于原密码进行比对
            if (md5pwd.equals(MD5Utils.getEncrpytedPassword(password, salt))) {
                //               根据用户名为用户生成一个定期的token
//                String token = jwtUtils.createToken(user.getUserId());
                String token = JsonWebTokenUtils.createToken(user.getUserId());
//                返回给客户端的信息中不能有用户密码盐
                user.setPassword("");
                user.setSalt("");
                modelMap.put("data", user);
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
    public Integer changeUserPwd(String oldpwd, String newpwd, Long userId) {
        User user = userMapper.getUserByuserId(userId);
        if (user != null) {
//        获取盐
            String salt = user.getSalt();
//            获取用户原密码
            String password = user.getPassword();
//            先校验原密码准确性
            if (password.equals(MD5Utils.getEncrpytedPassword(oldpwd, salt))) {
//                对新密码进行加密
                newpwd = MD5Utils.getEncrpytedPassword(newpwd, salt);
                Integer num = userMapper.changeuserpassword(newpwd, userId);
            } else {
                throw new PasswordNotMatchException();
            }
        } else {
            throw new UserExistException();
        }

        return null;
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserByUserId(Long userId) {
        User user = userMapper.getUserByuserId(userId);
        if (user != null) {
            user.setPassword("");
            user.setSalt("");
        } else {
            throw new UserExistException();
        }
        return user;
    }

    /**
     * 编辑用户
     *
     * @param user
     * @return
     */
    @Override
    public Integer editUserInfo(User user, Long userId) {
        User u = userMapper.getUserByuserId(userId);
        if (u != null) {
            Integer result = userMapper.editUserInfo(userId, user.getUserName(), user.getPhoneNumber(), user.getEmail(),
                    user.getUserAvatar(), user.getGender(), user.getBirthday(), user.getAddress(), user.getWork(),
                    user.getDescription(), user.getTrueName(), user.getPhoneNumber(), user.getIdentityNo(), date);
            if (result != 1) {
                throw new OperationFailException();
            }
        } else {
            throw new UserExistException();
        }
        return 1;
    }

    /**
     * 校验用户实名
     *
     * @param userId
     * @return
     */
    @Override
    public boolean checkUserReal(Long userId) {
        User user = userMapper.getUserByuserId(userId);
        if (user != null) {
            if (user.getTrueName() != null && user.getIdentityNo() != null) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new UserExistException();
        }
    }

    @Override
    public List<Car> getUserOwnercars(long userId) {
        List<Car> cars = carMapper.getUserOwnCar(userId);
        if(cars == null){
            throw new OperationFailException();
        }
        return cars;
    }

    @Override
    public List<Order> getUserOrders(long userId) {
        List<Order> orders = orderMapper.getOrderInfo(userId);
        if(orders == null){
            throw new OperationFailException();
        }
        return orders;
    }
}
