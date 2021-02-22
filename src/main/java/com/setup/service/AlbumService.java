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
    //删除相册，假删除
    public int deAlbum(int aid);
    //删除相册
    public int delAlbum(int aid);
    //重命名相册
    public int renameAlbum(String a_name,int aid);
    //更新相册信息
    public int updateAlbum(Album album);
    //查看原相册的状态是否为1,根据图片的i_id
    public Album queryOldAlbum(String i_id);
    //还原相册状态为1
    public int updateStatus(int aid);

}
