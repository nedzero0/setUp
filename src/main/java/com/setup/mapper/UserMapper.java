package com.setup.mapper;

import com.setup.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper{
    //查看全部用户
    public List<User> queryUserList();
    //根据电话和密码查找用户，即登录
    public User queryOne(String phone,String password);
    //插入一个用户，即注册  注册时只需要用户名，电话和密码，   暂时还没做密码加密，密码加密用MD5
    public int insertOne(User user);
    //更新用户资料
    public int updateUser(User user);
    //查看该电话是否已经被注册
    public Integer queryPhone(String phone);
    //查看该用户名是否可用
    public Integer queryUserName(String username);

    //更新密码，暂时不写

    //



}
