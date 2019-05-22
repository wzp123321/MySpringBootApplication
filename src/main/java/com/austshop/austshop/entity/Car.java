package com.austshop.austshop.entity;

import lombok.Data;

@Data
public class Car {
    private long carId;
    private String brand;
    private String Series;
    private String Vehicle;
    private  String carAvatar;
    private  String priviewAvatar;
    private  double truePrice;
    private double assessPrice;
    private  double spreadPrice;
    private double driverlength;
    private  String color;
    private  String seat;
    private  String transmission;
    private  String displacement;
    private  String address;
    private  Integer status;
    private long ownerId;
    private long handlerId;
    private  String registrationTime;
    private  String hangsellTime;

    public Car() {
    }

    public Car(long carId, String brand, String series, String vehicle, String carAvatar,
               String priviewAvatar, double truePrice, double spreadPrice,double driverlength, double assessPrice,String color, String seat, String transmission, String displacement, String address, Integer status, long ownerId,
               long handlerId, String registrationTime, String hangsellTime) {
        this.carId = carId;
        this.brand = brand;
        Series = series;
        Vehicle = vehicle;
        this.carAvatar = carAvatar;
        this.priviewAvatar = priviewAvatar;
        this.truePrice = truePrice;
        this.spreadPrice = spreadPrice;
        this.driverlength = driverlength;
        this.assessPrice = assessPrice;
        this.color = color;
        this.seat = seat;
        this.transmission = transmission;
        this.displacement = displacement;
        this.address = address;
        this.status = status;
        this.ownerId = ownerId;
        this.handlerId = handlerId;
        this.registrationTime = registrationTime;
        this.hangsellTime = hangsellTime;
    }

}
