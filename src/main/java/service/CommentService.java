package service;

import entity.Comment;
import entity.Replay;
import util.Msg;

import java.util.List;

/**
 * Create by czq
 * time on 2019/9/5  19:34
 */
public interface CommentService {
    Msg insertCommentService(Comment comment);

    Msg insertReplayComment(Replay replay);

    List<Comment> selectCommentByVideoId(Integer vid);
}
