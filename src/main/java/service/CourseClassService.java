package service;

import entity.CourseClass;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/24  12:07
 */
public interface CourseClassService {
    int deleteByPrimaryKeyService(Integer id);

    int insertService(CourseClass record);

    CourseClass selectByPrimaryKeyService(Integer id);

    List<CourseClass> selectAllService();

    int updateByPrimaryKeyService(CourseClass record);

    List<CourseClass> selectCourseClassService();
}
