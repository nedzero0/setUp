package com.setup.mapper;

import com.setup.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
    //查询该相册下的评论
    public List<Comment> queryComment(int aid);
    //添加评论
    public int insertComment(Comment comment);


}
