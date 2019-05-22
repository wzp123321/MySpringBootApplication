package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CarMapper {

//    新增挂卖车辆
    @Insert("insert into waitsale_cars_info(Brand,Series,Vehicle,carAvatar,priviewAvatar,truePrice,spreadPrice,driverlength,assessPrice" +
            ",color,seat,transmission,displacement,address,ownerId,handlerId,registrationTime,hangsellTime) values(" +
            "#{Brand},#{Series},#{Vehicle},#{carAvatar},#{priviewAvatar},#{truePrice},#{spreadPrice},#{driverlength},#{assessPrice},#{color},#{seat},#{transmission},#{displacement},#{address},#{ownerId},#{handlerId},#{registrationTime},#{hangsellTime})")
    public Integer insertCar(@Param("Brand") String Brand,@Param("Series") String Series,@Param("Vehicle") String Vehicle,@Param("carAvatar") String carAvatar,
                             @Param("priviewAvatar") String priviewAvatar,@Param("truePrice") double truePrice,
                             @Param("spreadPrice") double spreadPrice, @Param("driverlength") double driverlength,@Param("assessPrice") double assessPrice,@Param("color") String color,@Param("seat") String seat,@Param("transmission") String transmission,@Param("displacement") String displacement,@Param("address") String address,@Param("ownerId") long ownerId,
                             @Param("handlerId") long handlerId,@Param("registrationTime") String registrationTime,@Param("hangsellTime") String hangsellTime);


    /**
     * 查询所有挂卖车辆
     * @return
     */
    @Select("select * from waitsale_cars_info where status = #{status} order by hangsellTime desc limit #{currentpage},#{limit}")
    public List<Car> getAllCars(@Param("currentpage")  Integer currentpage, @Param("limit") Integer limit,@Param("status") Integer status);

// 按照条件查询挂卖车辆
    /**
     * 按照品牌查询挂卖车辆
     * @param currentpage
     * @param limit
     * @param Brand
     * @return
     */
    @Select("select * from waitsale_cars_info where Brand = #{Brand} and status = 1 order by hangsellTime limit #{currentpage},#{limit}")
    public List<Car> getAllBrandeCars(@Param("currentpage")  Integer currentpage, @Param("limit") Integer limit,@Param("Brand") String Brand);

    /**
     * 按照车系查询挂卖车辆
     * @param currentpage
     * @param limit
     * @param Series
     * @return
     */
    @Select("select * from waitsale_cars_info where Series = #{Series} and status = 1 order by hangsellTime desc limit #{currentpage},#{limit}")
    public List<Car> getAllSerieseCars(@Param("currentpage")  Integer currentpage, @Param("limit") Integer limit,@Param("Series") String Series);

    /**
     * 根据价格区间查询挂卖车辆
     * @param currentpage
     * @param limit
     * @param minprice
     * @param maxprice
     * @return
     */
    @Select("select * from waitsale_cars_info where status = 1 and truePrice < #{maxprice} and truePrice > #{minprice} order by hangsellTime desc limit #{currentpage},#{limit}")
    public List<Car> getAllPriceCars(@Param("currentpage")  Integer currentpage, @Param("limit") Integer limit,@Param("minprice") Integer minprice,@Param("maxprice") Integer maxprice);

    /**
     * 按照品牌查询挂卖车辆总条数
     * @param currentpage
     * @param limit
     * @param Brand
     * @return
     */
    @Select("select count(*) from waitsale_cars_info where Brand = #{Brand} and status = 1 order by hangsellTime desc limit #{currentpage},#{limit}")
    public Integer getAllBrandeCarsTotal(@Param("currentpage")  Integer currentpage, @Param("limit") Integer limit,@Param("Brand") String Brand);

    /**
     * 按照车系查询挂卖车辆总条数
     * @param currentpage
     * @param limit
     * @param Series
     * @return
     */
    @Select("select count(*) from waitsale_cars_info where status = 1 and Series = #{Series} order by hangsellTime desc limit #{currentpage},#{limit}")
    public Integer getAllSerieseCarsTotal(@Param("currentpage")  Integer currentpage, @Param("limit") Integer limit,@Param("Series") String Series);

    /**
     * 根据价格区间查询挂卖车辆总条数
     * @param currentpage
     * @param limit
     * @param minprice
     * @param maxprice
     * @return
     */
    @Select("select count(*) from waitsale_cars_info where status = 1 and truePrice < #{maxprice} and truePrice > #{minprice} order by hangsellTime desc limit #{currentpage},#{limit}")
    public Integer getAllPriceCarsTotal(@Param("currentpage")  Integer currentpage, @Param("limit") Integer limit,@Param("minprice") Integer minprice,@Param("maxprice") Integer maxprice);


    /**
     * 获取总条数
     * @param status
     * @return
     */
    @Select("select count(*) from waitsale_cars_info where status = #{status} ")
    public Integer getCarListPagesByStatus(@Param("status") Integer status);

    /**
     * 审核汽车
     * @param carId
     * @return
     */
    @Update("update waitsale_cars_info set status = 1 where carId = #{carId}")
    public Integer changeCarStatus(@Param("carId") long carId,@Param("handlerId") long handlerId);

    /**
     * 获取车辆信息
     * @param carId
     * @return
     */
    @Select("select * from waitsale_cars_info where carId = #{carId}")
    public Car getCarInfoById(@Param("carId") long carId);

    /**
     * 获取用户所有的车辆
     * @param ownerId
     * @return
     */
    @Select("select * from waitsale_cars_info where ownerId = #{ownerId}")
    public List<Car> getUserOwnCar(@Param("ownerId") long ownerId);

}
