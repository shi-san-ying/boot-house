package com.etoak.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HouseVo extends  House {
    //租赁name
    private  String rentModeName;
    //户型name
    private  String houseTypeName;
    //朝向name
    private  String orientationName;


    @JsonIgnore //不将他返回到结果中
    private String[] houseTypeList;
    @JsonIgnore
    private List<String> orientationList;
    @JsonIgnore  //不是用来接受参数得  是为了转化参数 传入mybatis中得
    List<Map<String, Integer>> rentalMapList;

}
