package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.CommentMapper;
import dao.CourseClassMapper;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.*;
import util.Msg;
import util.auth.RequireRole;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by czq
 * time on 2019/8/24  11:24
 */
@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseClassService courseClassService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;


    //二级菜单显示
    @RequestMapping("/meant")
    public ModelAndView meant(int id) {
        ModelAndView mv = new ModelAndView();
        List<Teacher> teachers = teacherService.selectTeacherByDirectionService(new Teacher(new CourseClass(id)));
        List<Course> courses = courseService.selectCourseByDirectionService(new Course(new CourseClass(id)));
        List<CourseClass> courseClasses = courseClassService.selectCourseClassService();
        mv.addObject("courseClasses", courseClasses);
        mv.addObject("teachers", teachers);
        mv.addObject("courses", courses);
        mv.addObject("index", id);
        mv.addObject("index1", 0);
        mv.addObject("index2", 0);
        mv.setViewName("course");
        return mv;
    }

    @RequestMapping("/conditionCourse")
    public ModelAndView conditionCourse(int tid, int direction_id, int condition) {
        List<CourseClass> courseClasses = courseClassService.selectCourseClassService();
        int flag = 0;
        if (tid != 0) {
            Teacher teacher = teacherService.selectById(tid);
            String courseDirection = teacher.getMajor().getCourseDirection();
            for (CourseClass courseClass : courseClasses) {
                if (courseClass.getCourseDirection().equals(courseDirection)) {
                    flag = courseClass.getId();
                }
            }
        }
        Course course = courseService.juifyCondition(tid, direction_id, condition);
        List<Course> courses = courseService.selectCourseByDirectionAndTidService(course);
        List<Teacher> teachers = teacherService.selectTeacherByDirectionService(new Teacher(new CourseClass(flag)));
        ModelAndView mv = new ModelAndView();
        mv.addObject("courseClasses", courseClasses);
        mv.addObject("teachers", teachers);
        mv.addObject("courses", courses);
        mv.addObject("index", flag);
        mv.addObject("index1", tid);
        mv.addObject("index2", condition);
        mv.setViewName("course");
        return mv;
    }


    @RequestMapping("/displayCourse")
    public ModelAndView displayCourse(int id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int i = 0;
        if (user != null) {
            i = userService.selectIsOrNotCollect(user.getId(), id);
        }
        //获取展示的课程信息
        Course course = courseService.selectByPrimaryKeyService(id);
        //获取展示课程学习方向一致的推荐课程信息
        List<Course> recommends = courseService.selectCourseByDirectionRecommendService(course.getId(), course.getCourseDirection().getId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("collect", i);
        mv.addObject("course", course);
        mv.addObject("recommends", recommends);
        mv.setViewName("radioDisplay");
        return mv;
    }

    @RequestMapping("/deleteCollect")
    @RequireRole("guest")
    public void collectCourse(HttpServletRequest request, int courseId) {
        User user = (User) request.getSession().getAttribute("user");
        courseService.deleteCollect(user, courseId);
    }

    @RequestMapping("/doCollect")
    @RequireRole("guest")
    public void doCourse(HttpServletRequest request, int courseId) {
        User user = (User) request.getSession().getAttribute("user");
        courseService.courseCollect(user, courseId);
    }


    @RequestMapping("/showVideo")
    @RequireRole("guest")
    public ModelAndView showVideo(int courseId, int chapter, int video, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        ModelAndView mv = new ModelAndView();
        //获取展示的课程信息
        Course course = courseService.selectByPrimaryKeyService(courseId);

//        获取视频信息
        Chapter chapter1 = course.getChapters().get(chapter);
        String chapterName = chapter1.getChapterName();
        String coursePath = course.getCourse_path();
        String chapterPath = chapter1.getPath();
        String videoPath = chapter1.getVideos().get(video).getPath();
        int videoId = chapter1.getVideos().get(video).getId();
        request.getSession().setAttribute("videoId", videoId);

        //查看视频是否有历史记录
        double v = courseService.selectVideoHistory(user.getId(), videoId);
        if (v > 0) {
            mv.addObject("currentTime", v);
        }
        //推荐课程
        List<Course> recommends = courseService.selectCourseByDirectionRecommendService(course.getId(), course.getCourseDirection().getId());
        mv.addObject("recommends", recommends);
        mv.addObject("chapter", (chapter + 1) + "-" + (video + 1) + " " + chapterName);
        mv.addObject("videoName", course.getCourseDirection().getCourseDirection() + "/" + coursePath + "/" + chapterPath + "/" + videoPath);
        mv.setViewName("video");
        return mv;
    }


    @ResponseBody
    @RequestMapping("/recordVideoTime")
    public void recordVideoTime(double time, HttpServletRequest request) {
        int videoId = (int) request.getSession().getAttribute("videoId");
        User user = (User) request.getSession().getAttribute("user");
        courseService.updateVideoHistory(user.getId(), videoId, time);
    }


    @ResponseBody
    @RequestMapping("/displayVideoComment")
    public Msg displayComment(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        int videoId = (int) request.getSession().getAttribute("videoId");
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        List<Comment> comments = commentService.selectCommentByVideoId(videoId);
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(comments, 3);
        return Msg.result(100, "老师信息", comments).add("pageInfo", page);
    }


}
