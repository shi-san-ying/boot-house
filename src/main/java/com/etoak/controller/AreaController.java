package com.etoak.controller;

import com.etoak.bean.Area;
import com.etoak.service.AreaService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area")
@Slf4j
@Api(tags="地区查询服务")
public class AreaController {
    @Autowired
    AreaService areaService;

    /**
     * 地区查询接口
     * @param：pid 父id 0包=表示查询省列表
     */

    @GetMapping("/queryByPid")
    @ApiOperation(value="根据父id查询地区列表",notes = "根据父id查询地区列表")
    @ApiResponses(value = {
            @ApiResponse(code=404,message = "接口不存在"),
            @ApiResponse(code=401,message = "无权限访问"),
            @ApiResponse(code=403,message = "禁止访问"),
            @ApiResponse(code=200,message = "成功"),
            @ApiResponse(code=500,message="服务端错误")
    })
    @ApiImplicitParam(value = "父id",name="pid",required = false,defaultValue = "0",paramType = "query")
    public List<Area> queryByPid(@RequestParam(required = false,defaultValue = "0") int pid){
        log.info("pdi->{}",pid);
        return areaService.queryByPid(pid);
    }
}
