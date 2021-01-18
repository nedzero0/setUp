package com.setup.mapper;

import com.setup.entity.Album;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AlbumMapper {
     //创建相册
     public Album createAlbum();
     //查找所有相册
     public List<Album> queryAlbum();
     //删除相册
     public boolean delAlbum(int aid);
     //啊啊啊啊



}
