package com.setup.service.impl;

import com.setup.entity.Image;
import com.setup.mapper.ImageMapper;
import com.setup.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public int insertImages(List<Image> imageList) {
        return imageMapper.insertImages(imageList);
    }

    @Override
    public int insertImage(Image image) {
        return imageMapper.insertImage(image);
    }

    @Override
    public List<Image> queryImageByAid(int aid) {
        return imageMapper.queryImageByAid(aid);
    }

    @Override
    public List<Image> queryReImage(int uid) {
        return imageMapper.queryReImage(uid);
    }

    @Override
    public List<String> queryAddress(String[] str) {
        return imageMapper.queryAddress(str);
    }


    @Override
    public int updateImage(String name, String describe, int i_id) {
        return imageMapper.updateImage(name,describe,i_id);
    }

   /* @Override
    public int recoverImages(List<String> list) {
        return imageMapper.recoverImages(list);
    }*/

    @Override
    public int recoveryImage(int i_id) {
        return imageMapper.recoveryImage(i_id);
    }

    @Override
    public int recoveryImages(String[] str) {
        return imageMapper.recoveryImages(str);
    }

    @Override
    public int updateRecovery(int i_id) {
        return imageMapper.updateRecovery(i_id);
    }

    @Override
    public int updateRecoverys(String[] str) {
        return imageMapper.updateRecoverys(str);
    }

    @Override
    public int delImage(int i_id) {
        return imageMapper.delImage(i_id);
    }

    @Override
    public int delImages(String[] str) {
        return imageMapper.delImages(str);
    }
}
