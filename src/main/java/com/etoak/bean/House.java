package com.etoak.bean;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class House {
    private Integer id;
    @NotNull(message = "省必填")
    private Integer province;
    private Integer city;
    private Integer area;
    //所在区县名称 可以从前端传入 也可以在后端根据area查询
    private String areaName;
    private String rentMode;
    @NotEmpty(message="朝向必填")
    private String orientation;
    private String houseType;
    private String rental;
    private String address;
    private String pic;
    private String publishTime;
}
