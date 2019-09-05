package dao;

import entity.Replay;

import java.util.List;

public interface ReplayMapper {
    int insert(Replay record);

    List<Replay> selectAll();

    Replay selectAllByCommentId(int comment_Id);


    //插入回复评论
    int insertReplayComment(Replay replay);
}