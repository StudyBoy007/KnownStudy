package service.impl;

import dao.TeacherMapper;
import entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TeacherService;
import util.Msg;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/22  14:57
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public int deleteByPrimaryKeyService(Integer id) {
        int i = teacherMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int insertService(Teacher record) {
        return 0;
    }

    @Override
    public Teacher selectByPrimaryKeyService(Integer id) {
        return null;
    }

    @Override
    public List<Teacher> selectAllService() {
        List<Teacher> teachers = teacherMapper.selectAll();
        return teachers;
    }

    @Override
    public int updateByPrimaryKeyService(Teacher record) {
        return 0;
    }

    @Override
    public List<Teacher> selectTeacherIndexService() {
        List<Teacher> teachers = teacherMapper.selectTeacherIndex();
        return teachers;
    }

    @Override
    public List<Teacher> selectTeacherIndex2Service() {
        List<Teacher> teachers = teacherMapper.selectTeacherIndex2();
        return teachers;
    }

    @Override
    public List<Teacher> selectTeacherByDirectionService(Teacher teacher) {
        List<Teacher> teachers = teacherMapper.selectTeacherByDirection(teacher);
        return teachers;
    }

    @Override
    public Teacher selectById(Integer id) {
        Teacher teacher = teacherMapper.selectById(id);
        return teacher;
    }

    @Override
    public Msg addFocusTeacherService(Integer uid, Integer tid) {
        int i = teacherMapper.addFocusTeacher(uid, tid);
        if (i == 0) {
            return new Msg(200, "关注失败", null);
        } else {
            teacherMapper.addTeacherFocus(tid);
            Teacher teacher = teacherMapper.selectById(tid);
            return new Msg(100, "关注成功", teacher);
        }
    }

    @Override
    public Msg delFocusTeacherService(Integer uid, Integer tid) {
        int i = teacherMapper.delFocusTeacher(uid, tid);
        if (i == 0) {
            return new Msg(200, "取关失败", null);
        } else {
            teacherMapper.delTeacherFocus(tid);
            Teacher teacher = teacherMapper.selectById(tid);
            return new Msg(100, "取关成功", teacher);
        }
    }

    @Override
    public int selectConnectionInUserAndTeachaer(Integer uid, Integer tid) {
        int i = teacherMapper.selectConnectionInUserAndTeachaer(uid, tid);
        return i;
    }

    public TeacherServiceImpl() {
        super();
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        int i = teacherMapper.updateTeacher(teacher);
        return i;
    }

    @Override
    public int insertTeacher(Teacher teacher) {
        int i = teacherMapper.insertTeacher(teacher);
        return i;
    }
}
