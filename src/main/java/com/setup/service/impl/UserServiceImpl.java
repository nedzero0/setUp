package com.setup.service.impl;

import com.setup.entity.User;
import com.setup.mapper.UserMapper;
import com.setup.service.UserService;
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
        int i = userMapper.insertOne(user);
        return i;
    }

    @Override
    public int updateUser(User user) {
        int i = userMapper.updateUser(user);
        return i;
    }
}
