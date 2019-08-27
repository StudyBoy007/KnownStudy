package dao;

import entity.Chapter;

import java.util.List;

public interface ChapterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chapter record);

    Chapter selectByPrimaryKey(Integer id);

    List<Chapter> selectAll();

    int updateByPrimaryKey(Chapter record);

    List<Chapter> selectByCourseId(Integer id);
}