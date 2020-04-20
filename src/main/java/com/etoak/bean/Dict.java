package com.etoak.bean;

import lombok.Data;
//查询字典表
@Data
public class Dict {
    private  Integer id;
    private String groupId;
    private String name;
    private String value;
    private Integer sort;
}
