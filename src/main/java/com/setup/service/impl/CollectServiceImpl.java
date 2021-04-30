package com.setup.service.impl;

import com.github.pagehelper.PageHelper;
import com.setup.entity.Album;
import com.setup.entity.Collect;
import com.setup.entity.PageVue;
import com.setup.mapper.CollectMapper;
import com.setup.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Integer addCollect(Collect collect) {
        return collectMapper.addCollect(collect);
    }

    @Override
    public void deleteCollect(Integer id) {
        collectMapper.deleteCollect(id);
    }

    @Override
    public PageVue<Album> query(Integer uid, Integer pageSize, Integer pageNum,String albumName) {
        com.github.pagehelper.Page<Album> page = PageHelper.startPage(pageNum,pageSize);
        //查询出  收藏的相册信息
        List<Album> list = collectMapper.query(uid,albumName);
        PageVue<Album> res = new PageVue<>(list);
        res.setTotal(page.getTotal());
        return res;
    }

    @Override
    public Collect ifCollect(Integer uid, Integer aid) {
        Collect collect = collectMapper.ifCollect(uid, aid);
        System.out.println(collect);
        if (collect == null){
            return new Collect();
        }
        return collect;
    }
}
