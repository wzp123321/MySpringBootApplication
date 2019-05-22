package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.Car;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface ICarService {

    /**
     * 新增挂卖车辆
     *
     * @param car
     * @return
     */
    public Integer insertCar(Car car, long userId);

    /**
     * 按照状态获取所有车辆
     *
     * @return
     */
    public ModelMap getAllCars(Integer limit, Integer current, Integer status, ModelMap modelMap);

    /**
     * 按照某些条件查询挂卖车辆
     * 品牌/车系/价格区间
     *
     * @param limit
     * @param current
     * @param Brand
     * @param Series
     * @param minprice
     * @param maxprice
     * @param modelMap
     * @return
     */
    public ModelMap getSomeTypeCars(Integer limit, Integer current, String Brand, String Series, Integer minprice, Integer maxprice, ModelMap modelMap);

    /**
     * 审核车辆
     *
     * @param carId * @param handlerId
     * @return
     */
    public Integer getCarChecked(long carId, long handlerId);

    /**
     * 根据id查询车辆信息
     *
     * @param carId
     * @return
     */
    public ModelMap getCarInfo(long carId);
}

