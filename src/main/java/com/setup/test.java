package com.setup;

import com.setup.entity.Album;
import com.setup.entity.Image;
import com.setup.entity.User;
import com.setup.mapper.AlbumMapper;
import com.setup.mapper.ImageMapper;
import com.setup.mapper.UserMapper;
import com.setup.service.ImageService;
import com.setup.service.UserService;
import com.setup.service.impl.AlbumServiceImpl;
import com.setup.service.impl.UserServiceImpl;
import com.setup.utils.addAlbum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
@Controller
public class test {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/queryUser")
    @ResponseBody
    public List<User> queryUser(){
        List<User> users = userMapper.queryUserList();
        //System.out.println(users);
        return users;
    }
}*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class test {

    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;




    @Test
    public void test() {




        //image测试

       /* int images = imageMapper.delImage(22);
        System.out.println(images);*/

       /* Image image1 = new Image(2,"333",12,"3333","jpg","2020-1-2","2020-2-3","eeee","eeeee","ddd");
        Image image2 = new Image(1,"444",3201,"3333","jpg","2020-1-2","2020-2-3","eeee","eeeee","ddd");

        List<Image> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);

      System.out.println(imageMapper.insertImages(images));*/


        //相册测试
       /* Album album = new Album(2,"李四的相册1","2021-11-23","4445");
        System.out.println(albumMapper.insertAlbum(album));*/

     /*   List<Album> albums = albumMapper.queryAlbum(1);
        System.out.println(albums);*/

        //System.out.println(albumMapper.delAlbum(2));

     /*   Album album = new Album(3,"3333","啊啊啊啊啊","1212121",3,"情侣","成都市成都东软学院");
        System.out.println(albumMapper.updateAlbum(album));
*/

        //用户测试

        //User user1 = new User(6,"嗯嗯","12313@qq.com","sfsdfsf123","2020-3-12",12,"男","123","四川省");
       // User user1 = new User("二星","1223546","456789");
        //System.out.println(userMapper.updateUser(user1));
        //System.out.println(userMapper.queryUserName("李四"));

        // User users = userMapper.queryOne("123","123456");
        //System.out.println(users);
    }


}
