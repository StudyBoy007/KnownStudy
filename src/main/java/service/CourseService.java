package service;

import entity.Course;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/22  15:03
 */
public interface CourseService {

    int deleteByPrimaryKeyService(Integer id);

    int insertService(Course record);

    Course selectByPrimaryKeyService(Integer id);


    //tid查找课程
    Course selectByTidService(Integer tid);

    List<Course> selectAllService();

    int updateByPrimaryKeyService(Course record);


    //推荐课程
    List<Course> selectCourseIndexService();


    //用户收藏的课程
    List<Course> selectUerCourseCollectService(Integer id);


    //用户购买的课程
    List<Course> selectUserCourseBuyService(Integer id);


    List<Course> selectCourseByDirectionService(Course course);

    List<Course> selectCourseByDirectionAndTidService(Course course);
}
