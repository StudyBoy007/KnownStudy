package dao;

import entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    //获取某个课程的全部评论
    List<Comment> selectCommentByVideoId(Integer vid);

    //插入评论
    int insertComment(Comment comment);
}