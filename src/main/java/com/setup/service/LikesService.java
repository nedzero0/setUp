package com.setup.service;

import com.setup.entity.Collect;
import com.setup.entity.Likes;
import org.springframework.stereotype.Service;

@Service
public interface LikesService {
    //添加点赞
    public Integer addLikes(Likes likes);

    //删除点赞
    public void deleteLikes(Integer id);

    //判断相册是否被点赞,返回点赞类
    public Likes ifLikes(Integer uid, Integer aid);
}
