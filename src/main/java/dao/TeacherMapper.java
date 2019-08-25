package dao;

import entity.Teacher;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);


    //展示在首页的老师信息
    List<Teacher> selectTeacherIndex();

    //优秀老师分类选择
    List<Teacher> selectTeacherIndex2();


    //对应方向的老师
    List<Teacher> selectTeacherByDirection(Teacher teacher);
}