package com.setup.mapper;

import com.setup.entity.Album;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AlbumMapper {
     //创建相册
     public Album createAlbum();





}
