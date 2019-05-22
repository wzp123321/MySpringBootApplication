package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.Order;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface IOrderService {
    /**
     * 新增订单
     * @param buyerId
     * @param sellerId
     * @param carId
     * @param dealprice
     * @return
     */
    public void addOrder(long buyerId,long sellerId,long carId, double dealprice);

    /**
     * 获取全部订单
     * @param currentpage
     * @param limit
     * @return
     */
    public ModelMap getAllorders(Integer currentpage, Integer limit);

    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    public ModelMap getOrderById(long orderId);

    /**
     * 获取不同状态的订单数
     * @param status
     * @return
     */
    public Integer getStatusPages(Integer status);

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    public void cancelOrder(long orderId);

    /**
     * 用户看完车
     * @param orderId
     * @return
     */
    public void lookOverCar(long orderId);

    /**
     * 用户完成付款
     * @param orderId
     * @return
     */
    public void payOver(long orderId);
    /**
     * 卖家确认
     * @param orderId
     * @return
     */
    public void sellerSure(long orderId);
    /**
     * 官方审核
     * @param orderId
     * @return
     */
    public void finishAssess(long orderId);

}
