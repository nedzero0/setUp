package com.setup.service;

import com.setup.entity.Album;
import com.setup.entity.Collect;
import com.setup.entity.PageVue;
import org.springframework.stereotype.Service;

@Service
public interface CollectService {
    //添加收藏
    public Integer addCollect(Collect collect);

    //取消/删除收藏
    public void deleteCollect(Integer id);

    //查看收藏,根据用户id
    public PageVue<Album> query(Integer uid, Integer pageSize, Integer pageNum,String albumName);

    //判断相册是否被收藏
    public Collect ifCollect(Integer uid, Integer aid);
}
