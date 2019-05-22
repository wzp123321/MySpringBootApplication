package com.austshop.austshop.mapper;

import com.austshop.austshop.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderMapper {

    /**
     * 新增订单
     *
     * @param buyerId
     * @param sellerId
     * @param carId
     * @param handlerId
     * @param dealprice
     * @param dealdate
     * @return
     */
    @Insert("insert into deal_order_info(buyerId,sellerId,carId,handlerId,dealprice,dealdate)" +
            "values(#{buyerId},#{sellerId},#{carId},#{handlerId},#{dealprice},#{dealdate})")
    public Integer getOrderInstert(@Param("buyerId") long buyerId, @Param("sellerId") long sellerId,
                                   @Param("carId") long carId, @Param("handlerId") long handlerId,
                                   @Param("dealprice") double dealprice, @Param("dealdate") String dealdate);


    /**
     * 获取全部订单
     *
     * @return
     */
    @Select("select * from deal_order_info where status !=0 limit #{currentpage} , #{limit}")
    public List<Order> getAllOrders(@Param("currentpage") Integer currentpage, @Param("limit") Integer limit);

    /**
     * 根据订单id查询订单详情
     *
     * @param orderId
     * @return
     */
    @Select("select * from deal_order_info where orderId = #{orderId}")
    public Order getorderbyid(@Param("orderId") Long orderId);

    /**
     * 获取订单总数
     *
     * @return
     */
    @Select("select count(*) from deal_order_info where status !=0")
    public Integer getpages();

    /**
     * 获取各状态订单总数
     *
     * @return
     */
    @Select("select count(*) from deal_order_info where status = #{status}")
    public Integer getpagesbystatus(@Param("status") Integer status);

    /**
     * 根据买家或者卖家id查询订单信息
     *
     * @param userId
     * @return
     */
    @Select("select * from deal_order_info where status !=0 and  buyerId = #{userId} or sellerId = #{userId}")
    public List<Order> getOrderInfo(@Param("userId") long userId);

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    @Update("update deal_order_info set status = 0 where orderId = #{orderId}")
    public Integer cancelOrder(@Param("orderId") long orderId);

    /**
     * 买家看车
     *
     * @param orderId
     * @return
     */
    @Update("update deal_order_info set status = 2 where orderId = #{orderId}")
    public Integer userLookCar(@Param("orderId") long orderId);

    /**
     * 用户付款成功
     *
     * @param orderId
     * @return
     */
    @Update("update deal_order_info set status = 3 where orderId = #{orderId}")
    public Integer paysucccess(@Param("orderId") long orderId);

    /**
     * 卖家通过
     *
     * @param orderId
     * @return
     */
    @Update("update deal_order_info set status = 4 where orderId = #{orderId}")
    public Integer sellerSure(@Param("orderId") long orderId);

    /**
     * 官方审核
     *
     * @param orderId
     * @return
     */
    @Update("update deal_order_info set status = 5 where orderId = #{orderId}")
    public Integer finishSure(@Param("orderId") long orderId);
}
