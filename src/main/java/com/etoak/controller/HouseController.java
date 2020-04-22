package com.etoak.controller;

import com.etoak.bean.House;
import com.etoak.service.HouseService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/house")
public class HouseController {

    //跳转到添加页面
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "house/add";
    }

    @Autowired
    HouseService houseService;

    //读取配文件 获取本地文件上传目录
    @Value("${upload.dir}")
    private String uploadDirectory;

    //获取访问路径得前缀
    @Value("${upload.savePathPrefix}")
    private String SavaPathPrefix;

    //添加用户
    @PostMapping("/add")
    public String addHouse(@RequestParam("file") MultipartFile file,House house) throws IOException {
        //文件的全名称  xxx.jpg
        String OriginalFilename=file.getOriginalFilename();
        //生成文件的新得前缀
        String prefix= UUID.randomUUID().toString().replaceAll("-","");
        //新的文件名字 uuid.文件后缀-原始文件名称
        String newFileName=prefix+"-"+OriginalFilename;

        //上传路径  1.本地上传文件目录   3.访问得路径
        //<mvc:resources location="file:d:/upload" mapping="/pics/**"
        File dastFile=new File(this.uploadDirectory,newFileName);
        //执行上传
        file.transferTo(dastFile);

        //给house设置访问路径
        house.setPic(this.SavaPathPrefix+newFileName);

        houseService.addHouse(house);
        return "redirect:/house/toAdd";
    }
}
