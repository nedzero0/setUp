package com.setup.service;

import com.setup.entity.Album;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlbumService {
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
