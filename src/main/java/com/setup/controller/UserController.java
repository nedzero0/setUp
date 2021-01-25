package com.setup.controller;

import com.setup.entity.User;
import com.setup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //默认页面
    @RequestMapping("/index")
    public String success(){
        System.out.println("1111111");
        return "index";
    }

    //到登录页面
    @RequestMapping("/getLogin")
    public String getLogin(){
        return "login";
    }

    //进行登录
    @RequestMapping("/login")
    public String login(String UserPhone, String Password, HttpSession session){
        //System.out.println(UserPhone+""+Password);
        System.out.println("登录中");
        User user = userService.signIn(UserPhone, Password);
        System.out.println(user);
        if (user!=null){
            session.setAttribute("user",user);
            System.out.println(session.getAttribute("str"));
            System.out.println("登录成功");
            return "redirect:/own/personal.html";
        }else {
            System.out.println("登录失败");
            return "redirect:/login.html";
        }
    }




}






