package com.setup;

import com.setup.entity.User;
import com.setup.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        List<User> users = userMapper.queryUserList();
        System.out.println(users);
    }


}
