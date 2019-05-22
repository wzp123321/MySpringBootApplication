package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * 用户模块sql语句
 */
public interface UserMapper {

    /**
     *新增系统用户
     * @param userName
     * @param salt
     * @param password
     * @param userAvatar
     * @param regDate
     * @param modifyDate
     * @return
     */
    @Insert("insert into common_user_info(userName,salt,password,userAvatar,regDate,modifyDate) values(" +
            "#{userName},#{salt},#{password},#{userAvatar},#{regDate},#{modifyDate})")
    public Integer getUserInsert(@Param("userName") String userName, @Param("salt") String salt, @Param("password") String password,
                                 @Param("userAvatar") String userAvatar, @Param("regDate") long regDate,@Param("modifyDate") long modifyDate);


    /**
     * 根据用户名/userId查询系统用户 校验用户存在性
     * @param userName
     * @return
     */
    @Select("select * from common_user_info where userName = #{userName}")
    public User checkUserExist(@Param("userName") String userName);

    /**
     * 根据用户名/userId查询系统用户 校验用户存在性
     * @param userId
     * @return
     */
    @Select("select * from common_user_info where userId = #{userId}")
    public User getUserByuserId(@Param("userId") Long userId);

    /**
     * 修改用户密码
     * @param newpassword
     * @param userId
     * @return
     */
    @Update("update common_user_info set password = #{newpassword} where userId= #{userId}")
     public Integer changeuserpassword(@Param("newpassword") String newpassword, @Param("userId") Long userId);

    /**
     *用户基本资料编辑
     * @param userName
     * @param phoneNumber
     * @param email
     * @param userAvatar
     * @param gender
     * @param birthday
     * @param address
     * @param work
     * @param description
     * @param trueName
     * @param Alipayaccount
     * @param identityNo
     * @param modifyTime
     * @return
     */
    @Update("update common_user_info set userName = #{userName},phoneNumber = #{phoneNumber},email = #{email}," +
            "userAvatar = #{userAvatar},gender = #{gender},birthday = #{birthday},address = #{address}," +
            "work = #{work},description = #{description},trueName = #{trueName},Alipayaccount = #{Alipayaccount}," +
            "identityNo = #{identityNo}," +
            "modifyTime = #{modifyTime} where userId = #{userId}")
    public Integer editUserInfo(@Param("userId") Long userId ,@Param("userName") String userName,@Param("phoneNumber") String phoneNumber,
                                @Param("email") String email,@Param("userAvatar") String userAvatar,
                                @Param("gender") Integer gender,@Param("birthday") String birthday,
                                @Param("address") String address,@Param("work") String work,
                                @Param("description") String description,@Param("trueName") String trueName,
                                @Param("Alipayaccount") String Alipayaccount,@Param("identityNo") String identityNo,
                                @Param("modifyTime") long modifyTime);
}
