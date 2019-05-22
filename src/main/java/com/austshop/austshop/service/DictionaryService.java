package com.austshop.austshop.service;

import com.austshop.austshop.entity.Dictionary;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.exception.DictionaryCheckKeyException;
import com.austshop.austshop.exception.OperationFailException;
import com.austshop.austshop.mapper.DicMapper;
import com.austshop.austshop.service.impl.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * zpwan
 * 2019/3/19
 */
@Service(value = "dictionarySerice")
public class DictionaryService implements IDictionaryService {

    @Autowired
    private DicMapper dicMapper;

    //    当前时间戳
    SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式


    @Override
    public ModelMap getdiclist(Integer limit, Integer currentpage, Integer typeCode) {
        ModelMap modelMap = new ModelMap();
        List<Dictionary> dictionaries = new ArrayList<>();
        Integer total = 0;
        if (typeCode == 0) {
            dictionaries = dicMapper.getDicList((currentpage - 1) * limit, limit);
            total = dicMapper.getAllTotal();
        } else {
            dictionaries = dicMapper.getDicListBytypeCode((currentpage - 1) * limit, limit, typeCode);
            total = dicMapper.getAllTotalBytypeCode(typeCode);
        }
        if (dictionaries == null) {
            throw new OperationFailException();
        } else {
            modelMap.put("data", dictionaries);
            modelMap.put("total", total);
            return modelMap;
        }
    }

    @Override
    public List<Dictionary> getdiclistbytypecode(Integer typeCode, Integer parentId) {
        List<Dictionary> dictionaries = dicMapper.getDicBytypeCode(typeCode, parentId);
        if (dictionaries == null) {
            throw new OperationFailException();
        } else {
            return dictionaries;
        }
    }

    @Override
    public void insertDictionary(Integer typeCode, String key, String value, Double price, Integer parentId, String creator) {
        String description = dicMapper.getDescriptionBytypeCode(typeCode);
//        switch (typeCode) {
//            case 1000:
//                description = "汽车品牌";
//                break;
//            case 1001:
//                description = "汽车车系";
//                break;
//            case 1002:
//                description = "汽车车型";
//                break;
//            case 1003:
//                description = "汽车标签";
//                break;
//            case 1004:
//                description = "汽车座次";
//                break;
//            case 1005:
//                description = "燃油类型";
//                break;
//        }
        String parentValue = dicMapper.getValueById(parentId);
        Integer num = dicMapper.getDicAdd(typeCode, key, value, description, price,parentId, parentValue, creator, dformat.format(new Date()));
        if (num != 1) {
            throw new OperationFailException();
        }
        ;
    }

    @Override
    public void updateDictionary(Integer id, String key, String value) {
        Integer num = dicMapper.getDicUpdate(id, key, value, dformat.format(new Date()));
        if (num != 1) {
            throw new OperationFailException();
        }
    }

    @Override
    public Dictionary checkdictionarykey(String key) {
        Dictionary dictionary = dicMapper.getDicByKey(key);
        // true：key被占用   false：key未被占用
        if (dictionary != null) {
            throw new DictionaryCheckKeyException();
        }
        return dictionary;
    }

    @Override
    public List<Dictionary> getAlltypeCode(Integer typeCode) {
        List<Dictionary> dictionaries = dicMapper.getAlltypeCode(typeCode);
        if (dictionaries != null) {
            return dictionaries;
        } else {
            throw new OperationFailException();
        }
    }

    @Override
    public double MakeCarAssess(String key, String lisencedate, double drivelength) {
//        获取价格
        double price = dicMapper.getpricebykey(key);
//      行驶里程取整
        int result = 0;
        for(int i=1;i<=(int)Math.ceil(drivelength/6);i++){
            result+=i;
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);

        return  Double.parseDouble(nf.format((price-(double) result*price/15)));
    }
}
