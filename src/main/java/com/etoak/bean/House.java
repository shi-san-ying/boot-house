package com.etoak.bean;
<<<<<<< HEAD
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
=======

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
>>>>>>> dev

@Data
public class House {
    private Integer id;
<<<<<<< HEAD
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
=======

    @NotNull(message = "省份必填")
    private Integer province;

    @NotNull(message = "市必填")
    private Integer city;

    @NotNull(message = "所在区必填")
    private Integer area;

    // 所在区县名称，可以从前端传入，也可以在后端根据area查询
    private String areaName;

    // 租赁方式
    @NotBlank(message = "租赁方式必填")
    private String rentMode;

    // 朝向
    private String orientation;
    // 户型
    private String houseType;

    // 租金
    @NotNull(message = "租金必填")
    @Max(value = 100000, message = "租金不能超过10万")
    @Min(value = 100, message = "租金不能少于100")
    private Integer rental;

    // 地址
    @Size(min = 1, max = 10, message = "地址长度介于1-10个字符")
    @NotBlank(message = "地址必填")
    private String address;

    // 房屋照片地址
    private String pic;

    // 发布时间
    private String publishTime;
}
>>>>>>> dev
