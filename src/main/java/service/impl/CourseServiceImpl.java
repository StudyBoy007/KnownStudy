package service.impl;

import dao.CourseMapper;
import entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CourseService;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/22  15:04
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public int deleteByPrimaryKeyService(Integer id) {
        return 0;
    }

    @Override
    public int insertService(Course record) {
        return 0;
    }

    @Override
    public Course selectByPrimaryKeyService(Integer id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        return course;
    }

    @Override
    public Course selectByTidService(Integer tid) {
        return null;
    }

    @Override
    public List<Course> selectAllService() {
        List<Course> courses = courseMapper.selectAll();
        return courses;
    }

    @Override
    public int updateByPrimaryKeyService(Course record) {
        return 0;
    }

    @Override
    public List<Course> selectCourseIndexService() {
        List<Course> courses = courseMapper.selectCourseIndex();
        return courses;
    }

    @Override
    public List<Course> selectUerCourseCollectService(Integer id) {
        List<Course> courses = courseMapper.selectUerCourseCollect(id);
        return courses;
    }

    @Override
    public List<Course> selectUserCourseBuyService(Integer id) {
        List<Course> courses = courseMapper.selectUserCourseBuy(id);
        return courses;
    }

    @Override
    public List<Course> selectCourseByDirectionService(Course course) {
        List<Course> courses = courseMapper.selectCourseByDirection(course);
        return courses;
    }

    @Override
    public List<Course> selectCourseByDirectionAndTidService(Course course) {
        List<Course> courses = courseMapper.selectCourseByDirectionAndTid(course);
        return courses;
    }

    @Override
    public List<Course> selectCourseByDirectionRecommendService(Integer mid, Integer oid) {
        List<Course> courses = courseMapper.selectCourseByDirectionRecommend(mid, oid);
        return courses;
    }
}
