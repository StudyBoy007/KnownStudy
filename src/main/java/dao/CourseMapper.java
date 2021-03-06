package dao;

import entity.Course;
import entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    Course selectByPrimaryKey(Integer id);


    //tid查找课程
    Course selectByTid(Integer tid);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    int updateByPrimaryKey2(Course record);


    //推荐课程
    List<Course> selectCourseIndex();


    //用户收藏的课程
    List<Course> selectUerCourseCollect(Integer id);


    //用户购买的课程
    List<Course> selectUserCourseBuy(Integer id);


    //对应方向的课程
    List<Course> selectCourseByDirection(Course course);


    List<Course> selectCourseByDirectionAndTid(Course course);


    //mid是展示课程的id oid是课程推荐的方向的id
    List<Course> selectCourseByDirectionRecommend(@Param("mid") Integer mid, @Param("oid") Integer oid);


    int juifyCourseIsOrNotBuy(@Param("uid") Integer uid, @Param("cid") Integer cid);

    int coursefocusDel(Integer cid);

    int coursefocusAdd(Integer cid);

    int insertCourse(Course course);

    int selectVideoHistoryIsOrNotExist(@Param("uid") Integer uid, @Param("vid") Integer vid);

    Optional<Double> selectVideoHistoryTime(@Param("uid") Integer uid, @Param("vid") Integer vid);

    int insertVideoHistory(@Param("uid") Integer uid, @Param("vid") Integer vid,@Param("time") Double time);

    int updateVideoHistory(@Param("uid") Integer uid, @Param("vid") Integer vid,@Param("time") Double time);
}