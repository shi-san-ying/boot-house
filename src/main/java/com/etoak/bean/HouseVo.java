package com.etoak.bean;

<<<<<<< HEAD
public class HouseVo {
=======
import lombok.Data;

@Data
public class HouseVo extends  House {
    //租赁name
    private  String rentModeName;
    //户型name
    private  String houseTypeName;
    //朝向name
    private  String orientationName;


>>>>>>> dev
}
