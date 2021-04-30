package com.setup.service.impl;

import com.setup.entity.Album;
import com.setup.entity.AlbumQuery;
import com.setup.mapper.AlbumMapper;
import com.setup.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public int insertAlbum(Album album) {
        int i = albumMapper.insertAlbum(album);
        return i;
    }

    @Override
    public List<Album> queryAlbum(int uid) {
        return albumMapper.queryAlbum(uid);
    }

    @Override
    public int deAlbum(int aid) {
        return albumMapper.deAlbum(aid);
    }

    @Override
    public int delAlbum(int aid) {
        return albumMapper.delAlbum(aid);
    }

    @Override
    public int renameAlbum(String a_name, int aid) {
        return albumMapper.renameAlbum(a_name, aid);
    }

    @Override
    public int updateAlbum(Album album) {
        return albumMapper.updateAlbum(album);
    }

    @Override
    public Album queryOldAlbum(String i_id) {
        return albumMapper.queryOldAlbum(i_id);
    }

    @Override
    public int updateStatus(int aid) {
        return albumMapper.updateStatus(aid);
    }

    @Override
    public List<Album> query(AlbumQuery albumQuery) {
        return null;
    }

    @Override
    public Album queryAlbumByAid(Integer aid) {
        return albumMapper.queryAlbumByAid(aid);
    }
}
