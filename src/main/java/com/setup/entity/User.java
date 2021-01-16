package com.setup.entity;


import javax.persistence.*;
import java.sql.Date;

//@Entity
public class User {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int uid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String profile_photo;//用户名头像地址
    private Date birthday;
    private int age;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return uid;
    }

    public void setId(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
