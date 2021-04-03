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
    public Integer queryCountAlbum();
    //2.根据主题的分页查询
    public List<Album> queryByTag(String tag,int start,int count);
    //查询总数
    public Integer queryCount(String tag);

    //3.根据搜索框的字段查询 相应的相册名称有关的
    public List<Album> queryByName(String name,int start,int count);
    //4.根据搜索框的字段查询  用户
    public List<User> queryByUserName(String userName,int start,int count);


    //5.根据推荐界面传来的相册 id 查询相册的所有信息
    public Album queryByAmID(int albumId);
    //6.查出相册信息后根据相册的uid 查找主人的信息
    public User queryOwnUser(int uId);

}
