package com.setup.mapper;

import com.setup.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ImageMapper {
    //增：上传图片，即插入图片
    public int insertImage(Image image);
    //查看该相册下的所有图片
    public List<Image> queryImageByAid(int aid);
    //更新图片原信息,即图片名称和描述
    public int updateImage(String name,String describe,int i_id);

    //回收图片，没有删除，只是放到了回收站，改变了状态码
    public int recoveryImage(int i_id);
    //彻底删除图片，即回收站里的都删除了
    public int delImage(int i_id);
    //从回收站还原图片
    public int updateRecovery(int i_id);

}
