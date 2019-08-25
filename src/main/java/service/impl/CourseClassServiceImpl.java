package service.impl;

import dao.CourseClassMapper;
import entity.CourseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CourseClassService;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/24  12:09
 */
@Service
public class CourseClassServiceImpl implements CourseClassService {

    @Autowired
    CourseClassMapper courseClassMapper;

    @Override
    public int deleteByPrimaryKeyService(Integer id) {
        return 0;
    }

    @Override
    public int insertService(CourseClass record) {
        return 0;
    }

    @Override
    public CourseClass selectByPrimaryKeyService(Integer id) {
        return null;
    }

    @Override
    public List<CourseClass> selectAllService() {
        return null;
    }

    @Override
    public int updateByPrimaryKeyService(CourseClass record) {
        return 0;
    }

    @Override
    public List<CourseClass> selectCourseClassService() {
        List<CourseClass> courseClasses = courseClassMapper.selectCourseClass();
        return courseClasses;
    }
}
