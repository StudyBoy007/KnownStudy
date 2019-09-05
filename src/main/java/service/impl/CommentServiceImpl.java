package service.impl;

import dao.CommentMapper;
import dao.ReplayMapper;
import entity.Comment;
import entity.Replay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CommentService;

import java.util.List;

/**
 * Create by czq
 * time on 2019/9/5  19:35
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ReplayMapper replayMapper;


    //插入评论

    @Override
    public int insertCommentService(Comment comment) {
        int i = commentMapper.insertComment(comment);
        return i;
    }

    //插入回复评论

    @Override
    public int insertReplayComment(Replay replay) {
        int i = replayMapper.insertReplayComment(replay);
        return i;
    }

    @Override
    public List<Comment> selectCommentByVideoId(Integer vid) {
        List<Comment> comments = commentMapper.selectCommentByVideoId(vid);
        return comments;
    }

}
