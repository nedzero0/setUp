package com.setup.service;

import com.setup.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //登录
    public User signIn(String phone,String password);
    //注册
    public int register(User user);
    //更新个人信息
    public int updateUser(User user);

}
