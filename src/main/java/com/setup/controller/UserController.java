package com.setup.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.setup.entity.Album;
import com.setup.entity.Image;
import com.setup.entity.User;
import com.setup.service.AlbumService;
import com.setup.service.ImageService;
import com.setup.service.UserService;
import com.setup.utils.addAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ImageService imageService;
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
       // System.out.println(user);
        if (user!=null){
            List<Album> albums = albumService.queryAlbum(user.getUid());
            //登录的时候加载 用户，相册，回收站信息   图片信息是查看相册的时候才加载
            session.setAttribute("user",user);
            session.setAttribute("albums",albums);
            //设置回收站的图片数据
            List<Image> images = imageService.queryReImage(user.getUid());
            session.setAttribute("reImages",images);

          //添加头像路径  /  是浏览器默认路径
            session.setAttribute("headName","/"+user.getProfile_photo());

            System.out.println("登录成功");
            return "redirect:/own/personal.html";
        }else {
            System.out.println("登录失败,电话或密码错误");
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
    public String register(String UserName,String UserPhone, String Password){
        System.out.println("注册中");
        User user = new User(UserName,UserPhone,Password);
        //System.out.println(user);

        int i = userService.register(user);

        if(i>0){
            User user2 = userService.signIn(UserPhone, Password);
            String str = String.valueOf(user2.getUid());
            addAlbum.addOriginal(str);
            return "redirect:/login.html";
        }else {
            System.out.println("用户名或电话已被注册");
            return "redirect:/register.html";
        }


    }


    //修改个人信息
    @RequestMapping("/updatePersonDate")
    @ResponseBody
    public String updatePersonDate(HttpServletRequest req, HttpSession session, @RequestBody User user){
      /*  JsonObject asJsonObject = new JsonParser().parse(obj).getAsJsonObject();
        String data = asJsonObject.toString();
        //System.out.println(data);
        Gson gson = new Gson();
        User user = gson.fromJson(data,User.class);*/

        //System.out.println("User是："+user);
        userService.updateUser(user);

        User user1 = (User)session.getAttribute("user");
        user1.setEmail(user.getEmail());
        user1.setBirthday(user.getBirthday());
        user1.setSex(user.getSex());
        user1.setAddress(user.getAddress());
        user1.setAutograph(user.getAutograph());

        session.setAttribute("user",user1);


        return "{\"id\":0"+"}";
    }

    //更改头像
    @RequestMapping("/changeHead")
    @ResponseBody
    public String changeHead(MultipartFile file,HttpSession session) {
        //System.out.println("111111111111111");
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        //1.获得当前user对象
        User user = (User)session.getAttribute("user");
        //1.2上传后的路径
        String filePath = "setUp//"+user.getUid()+"//head//";
        //2.文件上传后的本地路径
        File dest = new File("D://"+filePath + fileName);
        //System.out.println(dest.toString());

        //3.判断当前路径是否存在，没有就创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        //4.删除旧的头像
        if (user.getProfile_photo()!=null){
            //System.out.println("删除旧的");
            //获取旧的头像路径
            File oldHead = new File("D://"+user.getProfile_photo());
           // System.out.println(user.getProfile_photo());
            oldHead.delete();
        }
        try {
            //5.存储新的头像到文件夹中
            file.transferTo(dest);
            //6.存储路径到数据库中
            userService.updateHead(filePath+fileName,user.getPhone());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //7.更新user
        user.setProfile_photo(filePath+fileName);
        session.setAttribute("user",user);
        //8.设置新的头像值  //  和 /  都一样，没区别
       // String headName = "/setUp/" + user.getPhone() +"/head/" + fileName;
        //或   String headName = "/"+ filePath+fileName;或
        session.setAttribute("headName","/"+ filePath+fileName);

        //设置json格式的返回值，前端要求的
        return "{\"id\":0"+"}";

    }




}






