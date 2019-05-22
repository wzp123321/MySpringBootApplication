package com.austshop.austshop.controller;

import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.entity.Order;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/ordermodule")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order/insert")
    public ResultResponse handlerOrderInsert(
            @RequestParam(value = "sellerId", required = true) long sellerId,
            @RequestParam(value = "carId", required = true) long carId,
            @RequestParam(value = "price", required = true) double price,
            HttpServletRequest request
    ) {
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);
        orderService.addOrder(userId, sellerId, carId, price);
        return new ResultResponse(200, "新增成功", "SUCCESS");
    }

    // 获取全部订单订单
    @GetMapping(value = "/order/list")
    public ResultResponse getOrderlist(
            @RequestParam(value = "current", defaultValue = "1") Integer current,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            ModelMap modelMap) {
        modelMap = orderService.getAllorders(current, limit);
        return new ResultResponse(200, "查询成功", modelMap);
    }

    //    查询订单信息
    @GetMapping(value = "/order/info")
    public ResultResponse handleOrderInfo(
            @RequestParam(value = "orderId", required = true) long orderId,
            ModelMap modelMap
    ) {
        modelMap = orderService.getOrderById(orderId);
        return new ResultResponse(200, "查询成功", modelMap);
    }

    @GetMapping(value = "/order/page")
    public ResultResponse handleGetStatusPage(
            @RequestParam(value = "status", required = true) Integer status
    ) {
        return new ResultResponse(200, "查询成功", orderService.getStatusPages(status));
    }

    // 取消订单
    @PostMapping(value = "/order/cancel")
    public ResultResponse handleUserCancel(
            @RequestParam(value = "orderId", required = true) long orderId
    ) {
        orderService.cancelOrder(orderId);
        return new ResultResponse(200, "修改成功", "SUCCESS");
    }

    // 用户看车
    @PostMapping(value = "/order/userlook")
    public ResultResponse handleUserlook(
            @RequestParam(value = "orderId", required = true) long orderId
    ) {
        orderService.lookOverCar(orderId);
        return new ResultResponse(200, "修改成功", "SUCCESS");
    }

    //    用户付款
    @PostMapping(value = "/order/userpay")
    public ResultResponse handleUserpay(
            @RequestParam(value = "orderId", required = true) long orderId
    ) {
        orderService.payOver(orderId);
        return new ResultResponse(200, "修改成功", "SUCCESS");
    }

    //    卖家确认
    @PostMapping(value = "/order/sellersure")
    public ResultResponse handleSellerSure(
            @RequestParam(value = "orderId", required = true) long orderId
    ) {
        orderService.sellerSure(orderId);
        return new ResultResponse(200, "修改成功", "SUCCESS");
    }

    //    官方确认
    @PostMapping(value = "/order/assess")
    public ResultResponse handleAssess(
            @RequestParam(value = "orderId", required = true) long orderId
    ) {
        orderService.finishAssess(orderId);
        return new ResultResponse(200, "修改成功", "SUCCESS");
    }
}
