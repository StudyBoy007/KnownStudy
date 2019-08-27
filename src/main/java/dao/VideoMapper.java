package dao;

import entity.Video;

import java.util.List;

public interface VideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    Video selectByPrimaryKey(Integer id);

    List<Video> selectAll();

    int updateByPrimaryKey(Video record);

    List<Video> selectByChapterID(Integer id);
}