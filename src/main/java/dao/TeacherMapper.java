package dao;

import entity.Teacher;
import org.apache.ibatis.annotations.Param;

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


    Teacher selectById(Integer id);

    int addFocusTeacher(@Param("uid") Integer uid, @Param("tid") Integer tid);

    int delFocusTeacher(@Param("uid") Integer uid, @Param("tid") Integer tid);

    int selectConnectionInUserAndTeachaer(@Param("uid") Integer uid, @Param("tid") Integer tid);

    int addTeacherFocus(Integer id);

    int delTeacherFocus(Integer id);
}