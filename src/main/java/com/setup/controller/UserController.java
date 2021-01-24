package com.setup.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    //登录页面
    @RequestMapping("/login")
    public String success(){
        System.out.println("1111111");
        return "index";
    }


}






