package service;

import entity.Chapter;
import entity.Course;
import entity.User;
import entity.Video;
import util.Msg;

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

    int updateByPrimaryKey2Service(Course record);


    //推荐课程
    List<Course> selectCourseIndexService();


    //用户收藏的课程
    List<Course> selectUerCourseCollectService(Integer id);


    //用户购买的课程
    List<Course> selectUserCourseBuyService(Integer id);


    List<Course> selectCourseByDirectionService(Course course);

    List<Course> selectCourseByDirectionAndTidService(Course course);


    List<Course> selectCourseByDirectionRecommendService(Integer mid, Integer oid);


    Msg juifyCourseIsOrNotBuyService(int uid, int cid);


    Msg juifyCourseIsOrNotBuyOrInOrder(int uid, int cid);


    Course juifyCondition(int tid, int direction_id, int condition);


    void courseCollect(User user, int courseId);

    void deleteCollect(User user, int courseId);

    int insertCourseService(Course course);

    List<Chapter> selectChapterByCourseId(Integer courseId);

    List<Video> selectVideoByChapterId(Integer chapterId);

    Chapter selectChapterById(Integer chapterId);

    Video selectVideoById(Integer videoId);

    int updateChapter(Chapter chapter);

    int addChapter(Chapter chapter);

    int deleteChapter(int chapterId);

    int updateVideo(Video video);

    int insertVideo(Video video);

    int deleteVideo(int videoId);

    void updateVideoHistory(int uid,int vid,double time);

    double selectVideoHistory(int uid,int vid);
}
