package com.etoak.bean;

import lombok.Data;

import java.io.Serializable;
/*
* 地区表对应实体类
* */


@Data                //加了二级缓存要序列化这个类
public class Area implements Serializable {
    //主键
    private Integer id;
    //父id（省级父id为0）
    private Integer pid;
    //地区名称
    private String name;
    //排序字段
    private Integer sort;
}
