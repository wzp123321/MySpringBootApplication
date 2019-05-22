package com.austshop.austshop.controller;

import com.austshop.austshop.Utils.JsonWebTokenUtils;
import com.austshop.austshop.entity.Car;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/carmodule")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping(value = "/car/insert")
    public ResultResponse handleCarInsert(
            Car car, HttpServletRequest request
    ) {
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);

        carService.insertCar(car, userId);
        return new ResultResponse(200, "新增成功", "SUCCESS");
    }

    @GetMapping(value = "/car/list")
    public ResultResponse handleAllCars(
            @RequestParam(value = "currentpage", defaultValue = "1") Integer currentpage,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "status", required = true) Integer status,
            ModelMap modelMap) {
        ModelMap cars = carService.getAllCars(limit, currentpage,status, modelMap);
        return new ResultResponse(200, "查询成功", cars);
    }

    @GetMapping(value = "/car/checked")
    public ResultResponse handleCarChecked(
            @RequestParam(value = "carId", required = true) long carId,
            HttpServletRequest request) {
        String token = request.getHeader("token");

        Long userId = JsonWebTokenUtils.getAppUID(token);
        carService.getCarChecked(carId,userId);
        return new ResultResponse(200, "查询成功", "SUCCESS");
    }

    @GetMapping(value="/car/typelist")
    public ResultResponse HandleGetTypeCars(
            @RequestParam(value = "currentpage", defaultValue = "1") Integer currentpage,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "minprice", defaultValue = "0") Integer minprice,
            @RequestParam(value = "maxprice", defaultValue = "0") Integer maxprice,
            @RequestParam(value = "Brand", defaultValue = "null") String Brand,
            @RequestParam(value = "Series",defaultValue = "null") String Series,
            ModelMap modelMap){

        ModelMap cars = carService.getSomeTypeCars(limit,currentpage,Brand,Series,minprice,maxprice,modelMap);
        return new ResultResponse(200,"查询成功",modelMap);
    }

    @GetMapping(value="/car/carinfo")
    public ResultResponse handleCarInfo(
            @RequestParam(value="carId",required = true) long carId,
            ModelMap modelMap
    ){
        modelMap = carService.getCarInfo(carId);
        return new ResultResponse(200,"查询成功",modelMap);
    }

}
