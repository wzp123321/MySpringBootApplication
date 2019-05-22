package com.austshop.austshop.service.impl;

import com.austshop.austshop.entity.Dictionary;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * zpwan
 * 2019/3/19
 */
public interface IDictionaryService {

    /**
     * 获取管理员列表
     *
     * @param limit
     * @param currentpage
     * @param typeCode
     * @return
     */
    ModelMap getdiclist(Integer limit, Integer currentpage, Integer typeCode);

    /**
     * 获取管理员列表
     *
     * @param parentId
     * @return
     */
    List<Dictionary> getdiclistbytypecode(Integer typeCode,Integer parentId);

    /**
     * 新增字典
     * @param typeCode
     * @param key
     * @param value
     * @param parentId
     * @param creator
     * @return
     */
    void insertDictionary(Integer typeCode,String key,String value,Double price,Integer parentId,String creator);

    /**
     * 更新字典
     * @param id
     * @param key
     * @param value
     * @return
     */
    void updateDictionary(Integer id, String key,String value);

    /**
     * 字典查重
     * @param key
     * @return
     */
    Dictionary checkdictionarykey( String key);

    /**
     * 查询typeCode全部数据
     * @param typeCode
     * @return
     */
    List<Dictionary> getAlltypeCode(Integer typeCode);

    /**
     * 汽车估价
     * @param key
     * @param lisencedate
     * @param drivelength
     * @return
     */
    double MakeCarAssess(String key,String lisencedate,double drivelength);
}
