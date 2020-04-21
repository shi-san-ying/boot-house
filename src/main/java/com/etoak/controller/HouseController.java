package com.etoak.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/house")
public class HouseController {
    /*
    * 跳转到添加页面
    * */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "house/add";
    }
}
