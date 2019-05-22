package com.austshop.austshop.entity;

import lombok.Data;

@Data
public class Order {
    private long orderId;
    private long buyerId;
    private long sellerId;
    private long carId;
    private double dealprice;
    private long handlerId;
    private Integer status;
    private String dealdate;

    public Order() {
    }

    public Order(long orderId, long buyerId, long sellerId, long carId,
                 double dealprice, long handlerId, Integer status, String dealdate) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.carId = carId;
        this.dealprice = dealprice;
        this.handlerId = handlerId;
        this.status = status;
        this.dealdate = dealdate;
    }
}
