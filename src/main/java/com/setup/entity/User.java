package com.setup.entity;


import javax.persistence.*;
import java.sql.Date;

//@Entity
public class User {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private int uid;
    private String username;
    private String phone;
    private String password;
    private String email;
    private String profile_photo;//用户名头像地址
    private String birthday;
    private int age;
    private String sex;
    private String address;
    private String autograph;//个性签名

    public User() {

    }

    public User(int uid,String username, String email, String profile_photo, String birthday, int age, String sex, String address, String autograph) {
        this.uid=uid;
        this.username = username;
        this.email = email;
        this.profile_photo = profile_photo;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.autograph = autograph;
    }

    public User(String username, String phone, String password) {
        this.username = username;
        this.phone = phone;
        this.password = password;
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
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", autograph='" + autograph + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
