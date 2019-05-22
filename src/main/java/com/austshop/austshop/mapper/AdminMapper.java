package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.Admin;
import com.austshop.austshop.entity.AdminInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 管理员
 */
public interface AdminMapper {

    /**
     * 新增管理员
     * @param userName
     * @param phoneNumber
     * @param password
     * @param salt
     * @param modifyTime
     * @return
     */
    @Insert("insert into admin_user_info(userName,phoneNumber,password,salt,modifyTime) values(#{userName},#{phoneNumber},#{password},#{salt},#{modifyTime})")
    public Integer getadminadd(@Param("userName") String userName,@Param("phoneNumber") String phoneNumber,
                               @Param("password") String password,@Param("salt") String salt,@Param("modifyTime") String modifyTime);

    /**
     * 根据管理员用户名查询信息
     * @param userName  管理员用户名
     * @return  用户信息
     */
    @Select("select * from admin_user_info where userName = #{userName} or phoneNumber = #{userName} ")
    public Admin selectAdminByUserName(@Param("userName") String userName);

    /**
     *按照状态查询管理员列表
     * @param status 状态
     * @return  管理员列表
     */
    @Select("select userId,userName,status,authority from admin_user_info where status = #{status} and status!=2 order by modifyTime desc limit #{currentpage},#{limit}")
    public List<AdminInfo> getAdminInfoListByStatus(@Param("currentpage") Integer currentpage,@Param("limit") Integer limit,@Param("status") Object status);

    /**
     * 分页查询
     * @param currentpage
     * @param limit
     * @return
     */
    @Select("select userId,userName,status,authority from admin_user_info where status!=2 order by modifyTime desc limit #{currentpage},#{limit}")
    public List<AdminInfo> getAdminInfoList(@Param("currentpage") Integer currentpage,@Param("limit") Integer limit);

    /**
     * 修改管理员状态以及修改时间
     * @param status 状态
     * @param userId id
     * @param modifyTime 时间
     * @return 整数
     */
    @Update("update admin_user_info set status = #{status} , modifyTime = #{modifyTime} where userId = #{userId}")
    public Integer changeAdminStatus(@Param("status") Integer status ,@Param("userId") Long userId , @Param("modifyTime") String modifyTime);

    /**
     * 获取全部数据总条数
     * @return 总条数
     */
    @Select("select count(*) from admin_user_info where status = 0 or status = 1")
    public Integer gettotals();

    /**
     * 获取状态总条数
     * @return 总条数
     */
    @Select("select count(*) from admin_user_info where status = #{status}")
    public Integer getstatustotals(@Param("status") Integer status);

    /**
     * 删除管理员
     * @param userId 管理员id
     * @return 整数
     */
    @Update("update admin_user_info set status = 2 , modifyTime = #{modifyTime} where userId = #{userId}")
    public Integer getAdminDelete(@Param("userId") Long userId,@Param("modifyTime") String modifyTime);
}
