package com.setup.entity;

import java.sql.Date;

public class Comment {
    private int c_id;//评论id
    private int uid;//发表用户id
    private int aid;//评论的相册id
    private int c_likes;//点赞数
    private Date c_time;//评论日期
    private String c_content;//评论内容
    private int parent_id;//父评论id


    public Comment(){

    }

    @Override
    public String toString() {
        return "Comment{" +
                "c_id=" + c_id +
                ", uid=" + uid +
                ", aid=" + aid +
                ", c_likes=" + c_likes +
                ", c_time=" + c_time +
                ", c_content='" + c_content + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getC_likes() {
        return c_likes;
    }

    public void setC_likes(int c_likes) {
        this.c_likes = c_likes;
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }

    public String getC_content() {
        return c_content;
    }

    public void setC_content(String c_content) {
        this.c_content = c_content;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
