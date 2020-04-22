package com.etoak.service.impl;

import com.etoak.bean.Area;
import com.etoak.bean.House;
import com.etoak.bean.HouseVo;
import com.etoak.bean.Page;
import com.etoak.mapper.AreaMapper;
import com.etoak.mapper.HouseMapper;
import com.etoak.service.HouseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class HouseServiceImpl  implements HouseService {
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

    @Override
    public Page<HouseVo> queryList(int pageNum, int pageSize, HouseVo houseVo) {
        PageHelper.startPage(pageNum,pageSize);
        List<HouseVo> houseVoList =houseMapper.queryList(houseVo);
        PageInfo<HouseVo> info =new PageInfo<>(houseVoList);
        return new Page<HouseVo>(info.getPageNum(),info.getPageSize(),info.getTotal(),houseVoList,info.getPages());
    }
}
