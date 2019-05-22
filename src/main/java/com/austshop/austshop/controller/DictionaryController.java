package com.austshop.austshop.controller;

import com.austshop.austshop.entity.Dictionary;
import com.austshop.austshop.entity.ResultResponse;
import com.austshop.austshop.service.impl.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="/dicmodule")
public class DictionaryController {

    @Autowired
    private IDictionaryService iDictionaryService;

    /**
     * 分页查询字典列表
     *
     * @param typeCode    类别
     * @param currentpage 当前页
     * @param limit       每页限制条数
     * @return 字典列表
     */
    @GetMapping(value = "/dic/list")
    public ResultResponse getDiclist(
            @RequestParam(value = "typeCode", defaultValue = "0") Integer typeCode,
            @RequestParam(value = "currentpage", defaultValue = "1") Integer currentpage,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            ModelMap modelMap
    ) {
        modelMap = iDictionaryService.getdiclist(limit, currentpage, typeCode);
        return new ResultResponse(200, "SUCCESS", modelMap);
    }

    /**
     * 查询字典某种类别
     *  @param typeCode    类别
     * @param parentId   父级id(非必填)
     * @return 字典列表
     */
    @GetMapping(value = "/dic/typecodelist")
    public ResultResponse gettypecodelist(
            @RequestParam(value = "typeCode", required = true) Integer typeCode,
            @RequestParam(value = "parentId", defaultValue = "0") Integer parentId
    ) {
        List<Dictionary> dictionaries = iDictionaryService.getdiclistbytypecode(typeCode,parentId);
        return new ResultResponse(200, "SUCCESS", dictionaries);
    }

    /**
     * 新增字典
     *
     * @param typeCode 字典类别
     * @param key      key值
     * @param value    value值
     * @param parentId 父级id
     * @param creator  创建者
     * @return
     */
    @PostMapping(value = "/dic/add")
    public ResultResponse handleDictionaryAdd(
            @RequestParam(value = "typeCode", required = true) Integer typeCode,
            @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "value", required = true) String value,
            @RequestParam(value = "price", defaultValue = "0.00") Double price,
            @RequestParam(value = "parentId", required = true) Integer parentId,
            @RequestParam(value = "creator", required = true) String creator
    ) {
        iDictionaryService.insertDictionary(typeCode, key, value, price,parentId, creator);
        return new ResultResponse(200, "新增成功", "SUCCESS");
    }

    /**
     * 更新字典
     *
     * @param id    id
     * @param key   key值
     * @param value value值
     * @return
     */
    @PostMapping(value = "/dic/update")
    public ResultResponse handleDictionaryUpdate(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "value", required = true) String value
    ) {
        iDictionaryService.updateDictionary(id, key, value);
        return new ResultResponse(200, "修改成功", "SUCCESS");
    }


    /**
     * 字典查重字典
     *
     * @param key key值
     * @return
     */
    @GetMapping(value = "/dic/keycheck")
    public ResultResponse handleDictionaryCheckByKey(
            @RequestParam(value = "key", required = true) String key
    ) {
        Dictionary dictionary = iDictionaryService.checkdictionarykey(key);
        return new ResultResponse(200, "SUCCESS", "false");
    }

    /**
     * 管理后台
     * 查询某种typeCode
     * @param typeCode
     * @return
     */
    @GetMapping(value="/dic/typecode")
    public ResultResponse handletypeCode(
            @RequestParam(value = "typeCode", required = true) Integer typeCode
    ){
        List<Dictionary> dictionaries = iDictionaryService.getAlltypeCode(typeCode);
        return new ResultResponse(200,"SUCCESS",dictionaries);
    }


    @PostMapping(value="/car/assess")
    public ResultResponse AssessCarprice(
            @RequestParam(value="key",required = true) String key,
            @RequestParam(value="lisencedate",required = true) String lisencedate,
            @RequestParam(value="drivelength",required = true) double drivelength
    ){
        double price = iDictionaryService.MakeCarAssess(key,lisencedate,drivelength);
        return new ResultResponse(200,"SUCCESS",price);
    }
}
