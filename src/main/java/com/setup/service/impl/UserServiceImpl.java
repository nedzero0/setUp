package com.setup.service.impl;

import com.setup.entity.User;
import com.setup.mapper.UserMapper;
import com.setup.service.UserService;
import com.setup.utils.addAlbum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User signIn(String phone, String password) {
        User user = userMapper.queryOne(phone, password);
        return user;
    }

    @Override
    public int register(User user) {
        //看用户名和电话是否被注册
        Integer i = userMapper.queryPhone(user.getPhone());
        Integer j = userMapper.queryUserName(user.getUsername());
        System.out.println(i+" "+j);
        if (i==null && j==null){
            userMapper.insertOne(user);
            addAlbum.addOriginal(user.getPhone());
            return 1;

        }
        else {
            return 0;
        }

    }

    @Override
    public int updateUser(User user) {
        int i = userMapper.updateUser(user);
        return i;
    }
}
