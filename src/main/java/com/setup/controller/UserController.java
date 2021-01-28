package com.setup.controller;

import com.setup.entity.Album;
import com.setup.entity.User;
import com.setup.service.AlbumService;
import com.setup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;

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
            List<Album> albums = albumService.queryAlbum(user.getUid());
            session.setAttribute("user",user);
            session.setAttribute("albums",albums);
            System.out.println(albums);

            System.out.println("登录成功");
            return "redirect:/own/personal.html";
        }else {
            System.out.println("登录失败");
            return "redirect:/login.html";
        }
    }
    //到注册页面
    @RequestMapping("/getRegister")
    public String getRegister(){
        return "register";
    }

    //注册
    @RequestMapping("/register")
    public  String register(String UserName,String UserPhone, String Password){
        System.out.println("注册中");
        User user = new User(UserName,UserPhone,Password);
        System.out.println(user);
        int i = userService.register(user);
        if(i>0){
            return "redirect:/login.html";
        }else {
            System.out.println("用户名或电话已被注册");
            return "redirect:/register.html";
        }


    }


/*
    //到个人信息页面
    @RequestMapping("/getPersonInfo")
    public String getPersonInfo(){
        return "own/personInfo";
    }

*/




}






