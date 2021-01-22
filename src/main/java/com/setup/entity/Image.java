package com.setup.entity;


import java.sql.Date;

public class Image {
    private int i_id;//图片id
    private int aid;//所属相册id
    private String i_name;// 图片名称加后缀
    private int i_size;//图片大小
    private String i_describe;//图片描述
    private String i_contentType;//jpg还是png等格式，如果不是图片类型则不能上传
    private String i_time;//图片拍摄/创建时间
    private String i_uploadTime;//图片上传时间
    private String i_beforePath;//图片以前在相册的地址，可以不用
    private String i_path;//图片的相对路径:即图片在服务器上的相对路径
    private String md5;//判断是否是同一张图片
    private int status;//状态码  判断当前图片是在相册还是在回收站  0表示在回收站   1表示在相册

    public Image() {

    }

    public Image(int aid, String i_name, int i_size, String i_describe, String i_contentType, String i_time, String i_uploadTime, String i_beforePath, String i_path, String md5) {
        this.aid = aid;
        this.i_name = i_name;
        this.i_size = i_size;
        this.i_describe = i_describe;
        this.i_contentType = i_contentType;
        this.i_time = i_time;
        this.i_uploadTime = i_uploadTime;
        this.i_beforePath = i_beforePath;
        this.i_path = i_path;
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "Image{" +
                "i_id=" + i_id +
                ", aid=" + aid +
                ", i_name='" + i_name + '\'' +
                ", i_size=" + i_size +
                ", i_describe='" + i_describe + '\'' +
                ", i_contentType='" + i_contentType + '\'' +
                ", i_time=" + i_time +
                ", i_uploadTime=" + i_uploadTime +
                ", i_beforePath='" + i_beforePath + '\'' +
                ", i_path='" + i_path + '\'' +
                ", md5='" + md5 + '\'' +
                ", status=" + status +
                '}';
    }

    public String getI_contentType() {
        return i_contentType;
    }

    public void setI_contentType(String i_contentType) {
        this.i_contentType = i_contentType;
    }

    public int getI_id() {
        return i_id;
    }

    public void setI_id(int i_id) {
        this.i_id = i_id;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getI_name() {
        return i_name;
    }

    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public int getI_size() {
        return i_size;
    }

    public void setI_size(int i_size) {
        this.i_size = i_size;
    }

    public String getI_describe() {
        return i_describe;
    }

    public void setI_describe(String i_describe) {
        this.i_describe = i_describe;
    }

    public String getI_time() {
        return i_time;
    }

    public void setI_time(String i_time) {
        this.i_time = i_time;
    }

    public String getI_uploadTime() {
        return i_uploadTime;
    }

    public void setI_uploadTime(String i_uploadTime) {
        this.i_uploadTime = i_uploadTime;
    }

    public String getI_beforePath() {
        return i_beforePath;
    }

    public void setI_beforePath(String i_beforePath) {
        this.i_beforePath = i_beforePath;
    }

    public String getI_path() {
        return i_path;
    }

    public void setI_path(String i_path) {
        this.i_path = i_path;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
