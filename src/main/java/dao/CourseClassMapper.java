package dao;

import entity.CourseClass;

import java.util.List;

public interface CourseClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseClass record);

    CourseClass selectByPrimaryKey(Integer id);

    List<CourseClass> selectAll();

    int updateByPrimaryKey(CourseClass record);

    List<CourseClass> selectCourseClass();
}