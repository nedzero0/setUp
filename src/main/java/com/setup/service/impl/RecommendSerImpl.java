package com.setup.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.setup.entity.Album;
import com.setup.entity.AlbumQuery;
import com.setup.entity.PageVue;
import com.setup.entity.User;
import com.setup.mapper.AlbumMapper;
import com.setup.mapper.RecommendMapper;
import com.setup.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecommendSerImpl implements RecommendService {
    @Autowired
    private RecommendMapper recommendMapper;
    @Autowired
    private AlbumMapper albumMapper;

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

    @Override
    public PageVue<Album> query(AlbumQuery albumQuery, Integer pageSize, Integer pageNum) {
        com.github.pagehelper.Page<Album> page = PageHelper.startPage(pageNum,pageSize);//分页插件类的加载，它自动为下面的查询分页
        List<Album> albums = albumMapper.query(albumQuery);//从数据库中查询满足条件的
        PageVue<Album> res = new PageVue<>(albums);//将我需要的数据封装到另一个自定义类中

        System.out.println("总数为:"+page.getTotal());
        res.setTotal(page.getTotal());//设置总数
        return res;
    }


}
