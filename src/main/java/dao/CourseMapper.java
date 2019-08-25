package dao;

import entity.Course;
import entity.User;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    Course selectByPrimaryKey(Integer id);


    //tid查找课程
    Course selectByTid(Integer tid);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);


    //推荐课程
    List<Course> selectCourseIndex();


    //用户收藏的课程
    List<Course> selectUerCourseCollect(Integer id);


    //用户购买的课程
    List<Course> selectUserCourseBuy(Integer id);


    //对应方向的课程
    List<Course> selectCourseByDirection(Course course);


    List<Course> selectCourseByDirectionAndTid(Course course);
}