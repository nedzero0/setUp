package com.setup.mapper;

import com.setup.entity.Album;
import com.setup.entity.AlbumQuery;
import com.setup.entity.Comment;
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
     //删除相册，假删除
     public int deAlbum(int aid);
     //彻底删除相册
     public int delAlbum(int aid);
     //重命名相册
     public int renameAlbum(String a_name,int aid);
     //更新相册信息
     public int updateAlbum(Album album);
     //查看原相册的状态是否为1,根据图片的i_id
     public Album queryOldAlbum(String i_id);
     //还原相册状态为1
     public int updateStatus(int aid);


     //Vue  分页查询
     public List<Album> query(AlbumQuery albumQuery);


}
