package com.etoak.service;

import com.etoak.bean.House;
import com.etoak.bean.HouseVo;
import com.etoak.bean.Page;

public interface HouseService {
    int addHouse(House house);

    //房屋列表查询                 页码      每页记录      条件
    Page<HouseVo> queryList(int pageNum, int pageSize, HouseVo houseVo, String[] rentalList);
}
