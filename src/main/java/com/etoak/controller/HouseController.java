package com.etoak.controller;

import com.etoak.bean.House;
import com.etoak.bean.HouseVo;
import com.etoak.bean.Page;
import com.etoak.exception.ParamException;
import com.etoak.service.HouseService;
import com.etoak.utils.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
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
    public String addHouse(@RequestParam("file") MultipartFile file, @Valid House house, BindingResult bingResult) throws IOException {
       //hirbernate 得验证
        List<ObjectError> allErrors = bingResult.getAllErrors();
        if(CollectionUtils.isNotEmpty(allErrors)){
            StringBuffer  msgBuffer=new StringBuffer();
            for(ObjectError objectError: allErrors){
                String message = objectError.getDefaultMessage();
                msgBuffer.append(message);
            }
            throw new ParamException("参数校验失败：" + msgBuffer.toString());
        }

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
    @PostMapping("/add2")
    public String addHouse(@RequestParam("file") MultipartFile file, House house) throws IOException {
        //hirbernate 得验证
        ValidateUtils.validate(house);
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

    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Page<HouseVo> queryList(
            @RequestParam(required = false, defaultValue = "1") int pageNum,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            HouseVo houseVo) {
        log.info("pageNum - {}, pageSize - {}, houseVo - {}", pageNum, pageSize, houseVo);
        return houseService.queryList(pageNum, pageSize, houseVo);
    }
}
