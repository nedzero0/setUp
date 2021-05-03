package com.setup.mapper;

import com.setup.entity.Album;
import com.setup.entity.Collect;
import com.setup.entity.Likes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LikesMapper {
    //添加点赞
    public Integer addLikes(Likes likes);

    //删除点赞
    public void deleteLikes(Integer id);

    //判断相册是否被点赞,返回点赞类
    public Likes ifLikes(Integer uid, Integer aid);
}
