package service.impl;

import dao.CartMapper;
import dao.CourseMapper;
import dao.OrderMapper;
import dao.UserMapper;
import entity.Course;
import entity.CourseClass;
import entity.Teacher;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CourseService;
import util.Msg;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/22  15:04
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    OrderMapper orderMapper;


    @Autowired
    UserMapper userMapper;

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

    @Override
    public Msg juifyCourseIsOrNotBuyService(int uid, int cid) {

        //判断是否购买了课程
        int i = courseMapper.juifyCourseIsOrNotBuy(uid, cid);
        if (i != 0) {
            return Msg.result(101, "尊贵的用户，该课程您已经购买，是否前往观看", null);
        }
        //判断该课程是否存在购物车
        int i1 = cartMapper.selectCartByUserIdAndCourseId(uid, cid);
        if (i1 != 0) {
            return Msg.result(102, "尊贵的客户，该课程已经存在购物车中,是否查看购物车", null);
        }
        ///判断该课程是否存在订单中
        int i2 = orderMapper.selectCourseInOrder(uid, cid);
        if (i2 != 0) {
            return Msg.result(103, "尊贵的客户，该课程已经存在订单中,是否查看订单", null);
        }

        return Msg.result(100, "添加购物车成功，是否查看购物车", null);
    }

    @Override
    public Course juifyCondition(int tid, int direction_id, int condition) {
        Course course = new Course(new CourseClass(direction_id), new Teacher(tid));
        if (condition == 1) {
            course.setFocus(1);
        } else if (condition == 2) {
            course.setStart_time("1");
        } else if (condition == 3) {
            course.setPrice(1d);
        } else if (condition == 4) {
            course.setBuy_num(1);
        } else if (condition == 5) {
            course.setIsfress(1);
        }
        return course;
    }

    @Override
    public void courseCollect(User user, int courseId) {
        int i = userMapper.selectCollectConnectionInUserAndCourse(user.getId(), courseId);
        if (i == 0) {
            userMapper.createConnectionInUserAndCourseCollect(user.getId(), courseId);
        } else {
            userMapper.changeCollectCourseState(user.getId(), courseId, true);
        }

    }

    @Override
    public void deleteCollect(User user, int courseId) {
        if (userMapper.deleteConnectionJuify(user.getId(), courseId) == 0) {
            userMapper.deleteConnection(user.getId(), courseId);
        } else {
            userMapper.changeCollectCourseState(user.getId(), courseId, false);
        }

    }


}
