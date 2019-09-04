package service.impl;

import dao.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CourseService;
import util.DateDefine;
import util.Msg;

import java.util.List;
import java.util.Optional;

/**
 * Create by czq
 * time on 2019/8/22  15:04
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    OrderMapper orderMapper;


    @Autowired
    UserMapper userMapper;

    @Override
    public int deleteByPrimaryKeyService(Integer id) {
        int i = courseMapper.deleteByPrimaryKey(id);
        return i;
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
        int i = courseMapper.updateByPrimaryKey(record);
        return i;
    }

    @Override
    public int updateByPrimaryKey2Service(Course record) {
        int i = courseMapper.updateByPrimaryKey2(record);
        return i;
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
    public Msg juifyCourseIsOrNotBuyOrInOrder(int uid, int cid) {
        //判断是否购买了课程
        int i = courseMapper.juifyCourseIsOrNotBuy(uid, cid);
        if (i != 0) {
            return Msg.result(101, "尊贵的用户，该课程您已经购买，是否前往观看", null);
        }

        ///判断该课程是否存在订单中
        int i2 = orderMapper.selectCourseInOrder(uid, cid);
        if (i2 != 0) {
            return Msg.result(103, "尊贵的客户，该课程已经存在订单中,是否查看订单", null);
        }

        //该课程在购物车中则删除，去生成订单
        Cart cart = cartMapper.selectCartByUserIdAndCourseId2(uid, cid);
        if (cart != null) {
            cartMapper.deleteByPrimaryKey(cart.getId());
        }

        return Msg.result(100, "添加订单成功，是否查看订单", null);
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
        courseMapper.coursefocusAdd(courseId);
    }

    @Override
    public void deleteCollect(User user, int courseId) {
        if (userMapper.deleteConnectionJuify(user.getId(), courseId) == 0) {
            userMapper.deleteConnection(user.getId(), courseId);
        } else {
            userMapper.changeCollectCourseState(user.getId(), courseId, false);
        }
        courseMapper.coursefocusDel(courseId);
    }

    @Override
    public int insertCourseService(Course course) {
        course.setStart_time(DateDefine.getStringDate());
        int i = courseMapper.insertCourse(course);
        return i;
    }

    @Override
    public List<Chapter> selectChapterByCourseId(Integer courseId) {
        List<Chapter> chapters = chapterMapper.selectByCourseId(courseId);
        return chapters;
    }

    @Override
    public List<Video> selectVideoByChapterId(Integer chapterId) {
        List<Video> videos = videoMapper.selectByChapterID(chapterId);
        return videos;
    }

    @Override
    public Chapter selectChapterById(Integer chapterId) {
        Chapter chapter = chapterMapper.selectByPrimaryKey(chapterId);
        return chapter;
    }

    @Override
    public Video selectVideoById(Integer videoId) {
        Video video = videoMapper.selectByPrimaryKey(videoId);
        return video;
    }

    @Override
    public int updateChapter(Chapter chapter) {
        int i = chapterMapper.updateChapter(chapter);
        return i;
    }

    @Override
    public int addChapter(Chapter chapter) {
        int i = chapterMapper.insertChapter(chapter);
        return i;
    }

    @Override
    public int deleteChapter(int chapterId) {
        int i = chapterMapper.deleteByPrimaryKey(chapterId);
        return i;
    }

    @Override
    public int updateVideo(Video video) {
        int i = videoMapper.updateVideo(video);
        return i;
    }

    @Override
    public int insertVideo(Video video) {
        int i = videoMapper.insertVideo(video);
        return i;
    }

    @Override
    public int deleteVideo(int videoId) {
        int i = videoMapper.deleteByPrimaryKey(videoId);
        return i;
    }

    @Override
    public void updateVideoHistory(int uid, int vid, double time) {
        int i = courseMapper.selectVideoHistoryIsOrNotExist(uid, vid);
        if(i>0){
            int i1 = courseMapper.updateVideoHistory(uid, vid, time);
        }else {
            courseMapper.insertVideoHistory(uid, vid, time);
        }
    }

    @Override
    public double selectVideoHistory(int uid, int vid) {
        Double videoTime = courseMapper.selectVideoHistoryTime(uid, vid).orElse(0d);
        return videoTime;
    }
}
