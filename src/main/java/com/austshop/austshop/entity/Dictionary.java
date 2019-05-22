package com.austshop.austshop.entity;

import lombok.Data;

/**
 * 字典 实体类
 */
@Data
public class Dictionary {
    private Integer id;
    private Integer typeCode;
    private String description;
    private String key;
    private String value;
    private Integer parentId;
    private Double price;
    private String parentValue;
    private String creator;
    private String createTime;

    public Dictionary() {
    }

    public Dictionary(Integer id, Integer typeCode, String description, String key, String value, Integer parentId, Double price,
                      String parentValue, String creator, String createTime) {
        this.id = id;
        this.typeCode = typeCode;
        this.description = description;
        this.key = key;
        this.value = value;
        this.parentId = parentId;
        this.price = price;
        this.parentValue = parentValue;
        this.creator = creator;
        this.createTime = createTime;
    }

}
