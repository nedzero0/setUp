package com.setup.service.impl;

import com.setup.entity.Album;
import com.setup.entity.User;
import com.setup.mapper.RecommendMapper;
import com.setup.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecommendSerImpl implements RecommendService {
    @Autowired
    private RecommendMapper recommendMapper;

    @Override
    public List<Album> queryCommAlbum(int start, int count) {
        return null;
    }

    @Override
    public Integer queryCountAlbum() {
        return null;
    }

    @Override
    public List<Album> queryByTag(String tag, int start, int count) {
        return null;
    }

    @Override
    public Integer queryCount(String tag) {
        return null;
    }

    @Override
    public List<Album> queryByName(String name, int start, int count) {
        return null;
    }

    @Override
    public Integer queryCountByName(String name) {
        return null;
    }

    @Override
    public List<User> queryByUserName(String userName, int start, int count) {
        return null;
    }

    @Override
    public Album queryByAmID(int albumId) {
        return null;
    }

    @Override
    public User queryOwnUser(int uId) {
        return null;
    }
}
