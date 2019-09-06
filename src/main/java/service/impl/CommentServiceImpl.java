package service.impl;

import dao.CommentMapper;
import dao.ReplayMapper;
import entity.Comment;
import entity.Replay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CommentService;
import util.Msg;

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
    public Msg insertCommentService(Comment comment) {
        int i = commentMapper.insertComment(comment);
        if (i > 0) {
            return Msg.result(100, "评论添加成功", null);
        } else {
            return Msg.result(405, "评论添加失败", null);
        }
    }

    //插入回复评论

    @Override
    public Msg insertReplayComment(Replay replay) {
        int i = replayMapper.insertReplayComment(replay);
        if (i > 0) {
            return Msg.result(100, "评论添加成功", null);
        } else {
            return Msg.result(405, "评论添加失败", null);
        }
    }

    @Override
    public List<Comment> selectCommentByVideoId(Integer vid) {
        List<Comment> comments = commentMapper.selectCommentByVideoId(vid);
        return comments;
    }

}
