package com.austshop.austshop.service;

import com.austshop.austshop.entity.Car;
import com.austshop.austshop.entity.User;
import com.austshop.austshop.exception.OperationFailException;
import com.austshop.austshop.mapper.CarMapper;
import com.austshop.austshop.mapper.DicMapper;
import com.austshop.austshop.mapper.UserMapper;
import com.austshop.austshop.service.impl.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "carService")
public class CarService implements ICarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DicMapper dicMapper;

    //    当前时间戳
    SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    // 当前日期
    long date = (new Date()).getTime();

    @Override
    public Integer insertCar(Car car, long userId) {

        User user = userMapper.getUserByuserId(userId);

        double price = dicMapper.getpricebykey(car.getVehicle());

        int re = 0;
        for (int i = 1; i <= (int) Math.ceil(car.getDriverlength() / 6); i++) {
            re += i;
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);

        Integer result = carMapper.insertCar(car.getBrand(), car.getSeries(), car.getVehicle(), car.getCarAvatar(),
                car.getPriviewAvatar(), price, car.getSpreadPrice(), car.getDriverlength(),
                Double.parseDouble(nf.format((price - (double) re * price / 15))), car.getColor(), car.getSeat(), car.getTransmission(),
                car.getDisplacement(), user.getAddress(), userId, userId, car.getRegistrationTime(),
                dformat.format(new Date()));
        if (result != 1) {
            throw new OperationFailException();
        }
        return result;
    }

    @Override
    public ModelMap getAllCars(Integer limit, Integer currentpage, Integer status, ModelMap modelMap) {
        List<Car> cars = carMapper.getAllCars((currentpage - 1) * limit, limit, status);
        Integer total = carMapper.getCarListPagesByStatus(status);
        modelMap.put("data", cars);
        modelMap.put("total", total);
        return modelMap;
    }

    /**
     * 审核车辆
     *
     * @param carId
     * @return
     */
    @Override
    public Integer getCarChecked(long carId,long handlerId) {
        Integer result = carMapper.changeCarStatus(carId,handlerId);
        if (result != 1) {
            throw new OperationFailException();
        }
        return result;
    }

    /**
     * 按照某些条件查询挂卖车辆
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
    @Override
    public ModelMap getSomeTypeCars(Integer limit, Integer current, String Brand, String Series, Integer minprice, Integer maxprice, ModelMap modelMap) {
        List<Car> cars = new ArrayList<>();
        Integer total = 0;
        if (!Brand.equals("null")) {
            cars = carMapper.getAllBrandeCars((current - 1) * limit, limit, Brand);
            total = carMapper.getAllBrandeCarsTotal((current - 1) * limit, limit, Brand);
        }
        if (!Series.equals("null")) {
            cars = carMapper.getAllSerieseCars((current - 1) * limit, limit, Series);
            total = carMapper.getAllSerieseCarsTotal((current - 1) * limit, limit, Series);
        }
        if (minprice != 0 || maxprice != 0) {
            cars = carMapper.getAllPriceCars((current - 1) * limit, limit, minprice, maxprice);
            total = carMapper.getAllPriceCarsTotal((current - 1) * limit, limit, minprice, maxprice);
        }
        modelMap.put("data", cars);
        modelMap.put("total", total);
        return modelMap;
    }

    @Override
    public ModelMap getCarInfo(long carId) {
        Car car = carMapper.getCarInfoById(carId);
        if (car == null) {
            throw new OperationFailException();
        }
        ModelMap modelMap = new ModelMap();
        modelMap.put("car",car);
        User user = userMapper.getUserByuserId(car.getOwnerId());
        if (user == null) {
            throw new OperationFailException();
        }
        user.setSalt("");
        user.setPassword("");
        modelMap.put("user",user);
        return modelMap;
    }
}
