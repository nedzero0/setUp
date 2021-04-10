package com.setup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer c_id;//评论id
    private Integer uid;//发表用户id
    private Integer aid;//评论的相册id
    private Integer c_likes;//点赞数
    private String c_time;//评论日期
    private String c_content;//评论内容
    private Integer parent_id;//父评论id
    //用户信息
    private String username;
    private String profile_photo;//用户名头像地址
    //子回复信息
    private List<Comment> child;
    //回复的人的名称
    private String targetName;

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getC_likes() {
        return c_likes;
    }

    public void setC_likes(Integer c_likes) {
        this.c_likes = c_likes;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    public String getC_content() {
        return c_content;
    }

    public void setC_content(String c_content) {
        this.c_content = c_content;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public List<Comment> getChild() {
        return child;
    }

    public void setChild(List<Comment> child) {
        this.child = child;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
