package com.etoak.bean;


import lombok.Data;

@Data
public class House {
    private Integer id;
    private Integer province;
    private Integer city;
    private Integer area;
    //所在区县名称 可以从前端传入 也可以在后端根据area查询
    private String areaName;
    private String rentMode;
    private String orientation;
    private String houseType;
    private String rental;
    private String address;
    private String pic;
    private String publishTime;
}
