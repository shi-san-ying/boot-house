package com.etoak.controller;

import com.etoak.comons.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CodeController {
    //验证码接口
    @GetMapping("/code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //创建验证码对象   获取图片写到前端页面
        VerifyCode verifyCode =new VerifyCode();
        BufferedImage image =verifyCode.getImgs();
        //图片表達式结果  存在本次的session中
        int result = verifyCode.getResult();
        //将结果保存再session中
        request.getSession().setAttribute("code",result);
        //向前端写图片  禁止图像缓存
        response.setHeader("Pragma","No-Cache");
        response.setHeader("Cache-Control","No-Cache");
        response.setDateHeader("Expires",0L);
        //设置响应头
        response.setContentType("image/jpeg");
        //将图片以JPEG得格式被IO写出去
        ImageIO.write(image,"JPEG",response.getOutputStream());

    }
}
