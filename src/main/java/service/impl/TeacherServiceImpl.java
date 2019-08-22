package service.impl;

import dao.TeacherMapper;
import entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TeacherService;

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
        return 0;
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
        return null;
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
}
