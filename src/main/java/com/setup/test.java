package com.setup;

import com.setup.entity.User;
import com.setup.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private UserMapper userMapper;



    @Test
    public void test() {
       /* User user1 = new User(3,"小法","12313@qq.com","sfsdfsf123","2020-3-12",12,"男","123","四川省");
        System.out.println(user1);
        System.out.println(userMapper.updateUser(user1));*/
        System.out.println(userMapper.queryUserName("李四"));

        // User users = userMapper.queryOne("123","123456");
        //System.out.println(users);
    }


}
