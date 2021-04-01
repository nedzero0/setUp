package com.setup.service;

import com.setup.entity.Album;

import java.util.List;

public interface RecommendService {

    // 1.推荐界面推荐的相册
    public List<Album>  queryComAlbum();
    //2.根据主题的分页查询
    public List<Album> queryByTag();

}
