package com.setup.service.impl;

import com.setup.entity.Collect;
import com.setup.entity.Likes;
import com.setup.mapper.CollectMapper;
import com.setup.mapper.LikesMapper;
import com.setup.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesSerImpl implements LikesService {
    @Autowired
    private LikesMapper likesMapper;

    @Override
    public Integer addLikes(Likes likes) {
        return likesMapper.addLikes(likes);
    }

    @Override
    public void deleteLikes(Integer id) {
        likesMapper.deleteLikes(id);
    }

    @Override
    public Likes ifLikes(Integer uid, Integer aid) {
        Likes likes = likesMapper.ifLikes(uid, aid);
        if (likes == null){
            return new Likes();
        }
        return likes;
    }
}
