package com.setup.service;

import com.setup.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //登录
    public User signIn(String phone,String password);
    //注册，注册的时候 检查电话和用户名
    public int register(User user);
    //更新个人信息
    public int updateUser(User user);
    //更新用户头像
    public int updateHead(String path,String phone);
    //看是否电话已经被注册


}
