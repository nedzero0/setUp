package com.setup.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Album {
      private int aid;//相册id
      private int uid;//相册所属用户id
      private String a_name;//相册名称
      private String a_describe;//相册描述
      private String a_coverPath;//相册封面地址
      private String a_createTime;//相册创建时间
      private int a_authority;//相册权限  1私有  2 公开  3好友可见
      private String a_theme;//相册主题  如：情侣，风景等
      private int a_count;//相册内照片的数量
      private int a_views;//相册观看数
      private int a_likes;//相册点赞数
      private int a_stars;//相册收藏数
      private int a_assess;//相册评价数
      private BigDecimal coordinate;//相册 经纬度
      private String location;//相册的地理位置
      private String a_path;//相册路径
      private int a_status;//相册状态


    private Integer id;//如果被收藏，收藏id

    public Album() {

    }

    public Album(int uid, String a_name, String a_createTime, String a_path) {
        this.uid = uid;
        this.a_name = a_name;
        this.a_createTime = a_createTime;
        this.a_path = a_path;
    }

    public Album(int aid,String a_name, String a_describe, String a_coverPath, int a_authority, String a_theme,String location) {
        this.aid = aid;
        this.a_name = a_name;
        this.a_describe = a_describe;
        this.a_coverPath = a_coverPath;
        this.a_authority = a_authority;
        this.a_theme = a_theme;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Album{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", a_name='" + a_name + '\'' +
                ", a_describe='" + a_describe + '\'' +
                ", a_coverPath='" + a_coverPath + '\'' +
                ", a_createTime='" + a_createTime + '\'' +
                ", a_authority=" + a_authority +
                ", a_theme='" + a_theme + '\'' +
                ", a_count=" + a_count +
                ", a_views=" + a_views +
                ", a_likes=" + a_likes +
                ", a_stars=" + a_stars +
                ", a_assess=" + a_assess +
                ", coordinate=" + coordinate +
                ", location='" + location + '\'' +
                ", a_path='" + a_path + '\'' +
                ", a_status=" + a_status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getA_status() {
        return a_status;
    }

    public void setA_status(int a_status) {
        this.a_status = a_status;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_describe() {
        return a_describe;
    }

    public void setA_describe(String a_describe) {
        this.a_describe = a_describe;
    }

    public String getA_coverPath() {
        return a_coverPath;
    }

    public void setA_coverPath(String a_coverPath) {
        this.a_coverPath = a_coverPath;
    }

    public String getA_createTime() {
        return a_createTime;
    }

    public void setA_createTime(String a_createTime) {
        this.a_createTime = a_createTime;
    }

    public int getA_authority() {
        return a_authority;
    }

    public void setA_authority(int a_authority) {
        this.a_authority = a_authority;
    }

    public String getA_theme() {
        return a_theme;
    }

    public void setA_theme(String a_theme) {
        this.a_theme = a_theme;
    }

    public int getA_count() {
        return a_count;
    }

    public void setA_count(int a_count) {
        this.a_count = a_count;
    }

    public int getA_views() {
        return a_views;
    }

    public void setA_views(int a_views) {
        this.a_views = a_views;
    }

    public int getA_likes() {
        return a_likes;
    }

    public void setA_likes(int a_likes) {
        this.a_likes = a_likes;
    }

    public int getA_stars() {
        return a_stars;
    }

    public void setA_stars(int a_stars) {
        this.a_stars = a_stars;
    }

    public int getA_assess() {
        return a_assess;
    }

    public void setA_assess(int a_assess) {
        this.a_assess = a_assess;
    }

    public BigDecimal getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(BigDecimal coordinate) {
        this.coordinate = coordinate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getA_path() {
        return a_path;
    }

    public void setA_path(String a_path) {
        this.a_path = a_path;
    }
}
