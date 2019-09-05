package service;

import entity.Comment;
import entity.Replay;

import java.util.List;

/**
 * Create by czq
 * time on 2019/9/5  19:34
 */
public interface CommentService {
    int insertCommentService(Comment comment);

    int insertReplayComment(Replay replay);

    List<Comment> selectCommentByVideoId(Integer vid);
}
