package service;

import entity.Teacher;
import util.Msg;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/22  14:56
 */
public interface TeacherService {
    int deleteByPrimaryKeyService(Integer id);

    int insertService(Teacher record);

    Teacher selectByPrimaryKeyService(Integer id);

    List<Teacher> selectAllService();

    int updateByPrimaryKeyService(Teacher record);


    //展示在首页的老师信息
    List<Teacher> selectTeacherIndexService();

    List<Teacher> selectTeacherIndex2Service();


    List<Teacher> selectTeacherByDirectionService(Teacher teacher);

    Teacher selectById(Integer id);


    Msg addFocusTeacherService(Integer uid, Integer tid);

    Msg delFocusTeacherService(Integer uid, Integer tid);

    int selectConnectionInUserAndTeachaer(Integer uid, Integer tid);

    int updateTeacher(Teacher teacher);

    int insertTeacher(Teacher teacher);
}
