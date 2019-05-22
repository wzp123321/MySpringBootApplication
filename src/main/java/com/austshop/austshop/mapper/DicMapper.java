package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.Dictionary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DicMapper {

    /**
     * 查询字典全部数据
     *  ---管理后台
     * @param current 当前页
     * @param limit   每页条数
     * @return 字典列表
     */
    @Select("select * from apply_dictionary_info order by createTime desc limit #{current},#{limit}")
    public List<Dictionary> getDicList(@Param("current") Integer current, @Param("limit") Integer limit);

    /**
     * 查询字典某种类别数据
     *  ---管理后台
     * @param current  当前页
     * @param limit    每页条数
     * @param typeCode 类别
     * @return 字典列表
     */
    @Select("select * from apply_dictionary_info where typeCode = #{typeCode} order by createTime desc limit #{current},#{limit}")
    public List<Dictionary> getDicListBytypeCode(@Param("current") Integer current, @Param("limit") Integer limit, @Param("typeCode") Integer typeCode);

    /**
     * 查询类别种类按照父级id
     * ---客户端
     *  @param parentId
     * @param typeCode 类别
     * @return 字典列表
     */
    @Select("select * from apply_dictionary_info where typeCode = #{typeCode} and parentId = #{parentId} order by createTime")
    public List<Dictionary> getDicBytypeCode(@Param("typeCode") Integer typeCode,@Param("parentId") Integer parentId);

    /**
     * 根据id查询value
     * @param id
     * @return
     */
    @Select("select value from apply_dictionary_info where id = #{id}")
    public String getValueById(@Param("id") Integer id);

    /**
     * 新增字典
     *
     * @param typeCode    字典类别
     * @param key         key值
     * @param value       value值
     * @param description 描述
     * @param parentId    父级id
     * @param creator     作者
     * @param createTime  创建时间
     * @return 整数
     */
    @Insert("insert into apply_dictionary_info(typeCode,`key`,value,description,price,parentId,parentValue,creator,createTime) values(#{typeCode},#{key},#{value},#{description},#{price},#{parentId},#{parentValue},#{creator},#{createTime})")
    public Integer getDicAdd(@Param("typeCode") Integer typeCode, @Param("key") String key, @Param("value") String value, @Param("description") String description,@Param("price") Double price, @Param("parentId") Integer parentId,@Param("parentValue") String parentValue, @Param("creator") String creator, @Param("createTime") String createTime);

    /**
     * 根据id更新字典
     * ---管理后台
     * @param id         字典id
     * @param key        key值
     * @param value      value值
     * @param createTime 创建时间
     * @return 整数
     */
    @Insert("update apply_dictionary_info set `key` = #{key}, value = #{value} , createTime = #{createTime} where id = #{id}")
    public Integer getDicUpdate(@Param("id") Integer id, @Param("key") String key, @Param("value") String value, @Param("createTime") String createTime);


    /**
     * 根据字典类别查询描述
     *
     * @param typeCode 字典类别
     * @return
     */
    @Select("select distinct description from apply_dictionary_info where typeCode = #{typeCode}")
    public String getDescriptionBytypeCode(@Param("typeCode") Integer typeCode);

    /**
     * 查询数据总数
     *
     * @return 数据总数
     */
    @Select("select count(*) from apply_dictionary_info")
    public Integer getAllTotal();

    /**
     * 根据类别查询总数·
     *
     * @param typeCode 类别
     * @return 总数
     */
    @Select("select count(*) from apply_dictionary_info where typeCode = #{typeCode}")
    public Integer getAllTotalBytypeCode(@Param("typeCode") Integer typeCode);

    /**
     * 字典查重
     *
     * @param key key值
     * @return 字典对象
     */
    @Select("select * from apply_dictionary_info where `key` = #{key}")
    public Dictionary getDicByKey(@Param("key") String key);

    /**
     * 获取某个类别所有数据
     * @param typeCode
     * @return
     */
    @Select("select * from apply_dictionary_info where typeCode = #{typeCode}")
    public List<Dictionary> getAlltypeCode(@Param("typeCode") Integer typeCode);

    /**
     * 根据key获取车型价格
     * @param key
     * @return
     */
    @Select("select price from apply_dictionary_info where `key` = #{key} or `value` =#{key}")
    public Double getpricebykey(@Param("key") String key);

}
