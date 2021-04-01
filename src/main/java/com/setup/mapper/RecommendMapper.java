package com.setup.mapper;

import com.setup.entity.Album;
import com.setup.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecommendMapper {
    // 1.推荐界面推荐的相册,这是分页查询
    public List<Album> queryCommAlbum(int start,int count);
    //2.根据主题的分页查询
    public List<Album> queryByTag();
    //3.根据推荐界面传来的相册 id 查询相册的所有信息
    public Album queryByAmID(int albumId);
    //4.根据相册id 查找主人的信息
    public User queryUser(int albumId);

}
