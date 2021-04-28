package com.setup.mapper;

import com.setup.entity.Album;
import com.setup.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectMapper {
    //添加收藏
    public Integer addCollect(Collect collect);

    //删除收藏
    public void deleteCollect(Integer id);

    //查看收藏,根据用户id
    public List<Album> query(Integer uid,String albumName);
}
