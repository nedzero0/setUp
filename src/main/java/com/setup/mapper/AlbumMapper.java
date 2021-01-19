package com.setup.mapper;

import com.setup.entity.Album;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AlbumMapper {
     //创建相册
     public int insertAlbum(Album album);
     //查找该用户的所有相册
     public List<Album> queryAlbum(int uid);
     //删除相册
     public int delAlbum(int aid);
     //重命名相册
     public int renameAlbum(String a_name,int aid);
     //更新相册信息
     public int updateAlbum(Album album);

}
