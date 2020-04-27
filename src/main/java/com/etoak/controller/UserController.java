package com.etoak.controller;

import com.etoak.bean.User;
import com.etoak.comons.CommonResult;
import com.etoak.exception.ParamException;
import com.etoak.exception.UserLoginException;
import com.etoak.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    public static final Integer ACTIVE_STSTE=1;

    @Autowired
    UserService userService;

    @GetMapping("/toReg")
    public String toReg(){
        return "house/reg";
    }

    @PostMapping("/reg")
    public String reg(@RequestParam String confirmPassword, User user){
        if(!StringUtils.equals(confirmPassword,user.getPassword())){
            throw new ParamException("两次密码不一致");
        }
        userService.addUser(user);
        return "redirect:/user/toReg";
    }

    @GetMapping("/validateName")
    @ResponseBody
    public String validateName(@RequestParam String name){
        log.info("name--->{}",name);
        User user=userService.queryByName(name);
        return user==null?"true":"false";

    }

    @GetMapping("/toLogin")
    public String toLogin(){
        return "/house/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@RequestParam String name, @RequestParam String password, @RequestParam String code, HttpServletRequest request){


        //1.验证验证码得结果是否是计算得结果  里面是int类型转成String  进行比对
        String result = String.valueOf(request.getSession().getAttribute("code"));
        if(!StringUtils.equals(code,result)){
            throw new UserLoginException("验证码错误");
        }

        //2.根据用户名那个查询用户 如果用户存在  进入核对密码  如果不存在 就直接返回
        User user = userService.queryByName(name);
        if(ObjectUtils.isEmpty(user)){
            log.error("未查询到用户");
            throw new UserLoginException("用户名密码错误");
        }
        //2.1判断用户状态
        if(user.getState()!=ACTIVE_STSTE){
            throw new UserLoginException("用户状态异常");
        }
        //3.比对密码 如果密码正确 直接进入第四步
       password= DigestUtils.md5Hex(password);
        if(!StringUtils.equals(password,user.getPassword())){
            throw new UserLoginException("用户名或密码错误");
        }
        //4.将用户放在session里面
          //4.1 销毁之后存储验证码得session
        request.getSession().invalidate();
          //4.2  将用户密码设置为null 防止有人可以拿取你的password
        HttpSession session = request.getSession();
        user.setPassword(null);
          //4.3 将创建新的session保存用户
        session.setAttribute("user",user);
        return new CommonResult(CommonResult.SUCCESS_CODE,CommonResult.SUCCESS_MSG);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/user/toLogin";
    }
}
