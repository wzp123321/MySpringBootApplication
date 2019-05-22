package com.austshop.austshop.service;

import com.austshop.austshop.entity.Car;
import com.austshop.austshop.entity.Order;
import com.austshop.austshop.entity.User;
import com.austshop.austshop.exception.OperationFailException;
import com.austshop.austshop.mapper.CarMapper;
import com.austshop.austshop.mapper.OrderMapper;
import com.austshop.austshop.mapper.UserMapper;
import com.austshop.austshop.service.impl.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "orderService")
public class OrderService implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CarMapper carMapper;

    //    当前时间戳
    SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式

    @Override
    public void addOrder(long buyerId, long sellerId, long carId, double dealprice) {
        Integer result = orderMapper.getOrderInstert(buyerId, sellerId, carId,
                buyerId, dealprice, dformat.format(new Date()));
        if (result != 1) {
            throw new OperationFailException();
        }
    }

    @Override
    public ModelMap getAllorders(Integer currentpage, Integer limit) {
        List<Order> orders = orderMapper.getAllOrders((currentpage - 1) * limit, limit);
        if (orders == null) {
            throw new OperationFailException();
        }
        int total = orderMapper.getpages();
//        最外层的map
        ModelMap modelMap1 = new ModelMap();
//        每一个订单各自的map
        ModelMap modelMap2;
//        存储每个订单map的list
        List<ModelMap> modelMaps = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            modelMap2 = new ModelMap();
            User buyer = userMapper.getUserByuserId(orders.get(i).getBuyerId());
            User seller = userMapper.getUserByuserId(orders.get(i).getSellerId());
            Car car = carMapper.getCarInfoById(orders.get(i).getCarId());
            modelMap2.put("orderId", orders.get(i).getOrderId());
            modelMap2.put("buyer", buyer);
            modelMap2.put("seller", seller);
            modelMap2.put("car", car);
            modelMap2.put("dealprice", orders.get(i).getDealprice());
            modelMap2.put("handlerId", orders.get(i).getHandlerId());
            modelMap2.put("status", orders.get(i).getStatus());
            modelMap2.put("dealdate", orders.get(i).getDealdate());
            modelMaps.add(modelMap2);
        }
        modelMap1.put("data", modelMaps);
        modelMap1.put("total", total);
        return modelMap1;
    }

    @Override
    public ModelMap getOrderById(long orderId) {
        ModelMap modelMap = new ModelMap();
        Order order = orderMapper.getorderbyid(orderId);
        if (order != null) {
            User buyer = userMapper.getUserByuserId(order.getBuyerId());
            User seller = userMapper.getUserByuserId(order.getSellerId());
            Car car = carMapper.getCarInfoById(order.getCarId());
            modelMap.put("orderId", order.getOrderId());
            modelMap.put("buyer", buyer);
            modelMap.put("seller", seller);
            modelMap.put("car", car);
            modelMap.put("dealprice", order.getDealprice());
            modelMap.put("handlerId", order.getHandlerId());
            modelMap.put("status", order.getStatus());
            modelMap.put("dealdate", order.getDealdate());
            return modelMap;
        } else {
            throw new OperationFailException();
        }
    }

//获取不同订单的总数
    @Override
    public Integer getStatusPages(Integer status) {
        return orderMapper.getpagesbystatus(status);
    }

    @Override
    public void cancelOrder(long orderId) {
        Integer result = orderMapper.cancelOrder(orderId);
        if (result != 1) {
            throw new OperationFailException();
        }
    }

    @Override
    public void lookOverCar(long orderId) {
        Integer result = orderMapper.userLookCar(orderId);
        if (result != 1) {
            throw new OperationFailException();
        }
    }

    @Override
    public void payOver(long orderId) {
        Integer result = orderMapper.paysucccess(orderId);
        if (result != 1) {
            throw new OperationFailException();
        }
    }

    @Override
    public void sellerSure(long orderId) {
        Integer result = orderMapper.sellerSure(orderId);
        if (result != 1) {
            throw new OperationFailException();
        }
    }

    @Override
    public void finishAssess(long orderId) {
        Integer result = orderMapper.finishSure(orderId);
        if (result != 1) {
            throw new OperationFailException();
        }
    }
}
