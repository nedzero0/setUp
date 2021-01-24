package com.setup.service;

import com.setup.entity.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {
    //增：批量上传图片，即插入图片
    public int insertImages(List<Image> imageList);
    //查看该相册下的所有图片,状态码为1的
    public List<Image> queryImageByAid(int aid);
    //更新图片原信息,即图片名称和描述
    public int updateImage(String name,String describe,int i_id);

    //回收图片，没有删除，只是放到了回收站（假的，不做这一步），只改变了状态码
    public int recoveryImage(int i_id);
    //从回收站还原图片,即更改状态码为 1
    public int updateRecovery(int i_id);

    //彻底删除图片，即删除状态码为0的
    public int delImage(int i_id);

}