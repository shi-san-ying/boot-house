package com.etoak.service.impl;

import com.etoak.bean.Area;
import com.etoak.bean.House;
import com.etoak.mapper.AreaMapper;
import com.etoak.mapper.HouseMapper;
import com.etoak.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HousServiceImpl  implements HouseService {
    @Autowired
    HouseMapper houseMapper;

    @Autowired
    AreaMapper areaMapper;

    @Override
    public int addHouse(House house) {
        //根据区的id查询area
        Area area=areaMapper.queryById(house.getArea());
         if(area==null){
             log.error("未查询到所在区，查询所在区的id-{}",house.getArea());
             throw  new RuntimeException("地区异常");
         }
         //将area中区id对应名字赋予house
         house.setAreaName(area.getName());
        return houseMapper.addHouse(house);

    }
}
