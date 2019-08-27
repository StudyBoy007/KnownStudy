package controller;

import dao.CourseClassMapper;
import entity.Chapter;
import entity.Course;
import entity.CourseClass;
import entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CourseClassService;
import service.CourseService;
import service.TeacherService;

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
        List<Course> courses = courseService.selectCourseByDirectionAndTidService(course);
        List<CourseClass> courseClasses = courseClassService.selectCourseClassService();
        List<Teacher> teachers = teacherService.selectTeacherByDirectionService(new Teacher(new CourseClass(direction_id)));
        ModelAndView mv = new ModelAndView();
        mv.addObject("courseClasses", courseClasses);
        mv.addObject("teachers", teachers);
        mv.addObject("courses", courses);
        mv.addObject("index", direction_id);
        mv.addObject("index1", tid);
        mv.addObject("index2", condition);
        mv.setViewName("course");
        return mv;
    }


    @RequestMapping("/displayCourse")
    public ModelAndView displayCourse(int id) {
        //获取展示的课程信息
        Course course = courseService.selectByPrimaryKeyService(id);

        //获取展示课程学习方向一致的推荐课程信息
        List<Course> recommends = courseService.selectCourseByDirectionRecommendService(course.getId(), course.getCourseDirection().getId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("course", course);
        mv.addObject("recommends", recommends);
        mv.setViewName("radioDisplay");
        return mv;
    }


    @RequestMapping("/showVideo")
    public ModelAndView showVideo(int courseId, int chapter, int video) {
        ModelAndView mv = new ModelAndView();
        //获取展示的课程信息
        Course course = courseService.selectByPrimaryKeyService(courseId);

//        获取视频信息
        Chapter chapter1 = course.getChapters().get(chapter);
        String chapterName = chapter1.getChapterName();
        String coursePath = course.getCourse_path();
        String chapterPath = chapter1.getPath();
        String videoPath = chapter1.getVideos().get(video).getPath();


        //推荐课程
        List<Course> recommends = courseService.selectCourseByDirectionRecommendService(course.getId(), course.getCourseDirection().getId());
        mv.addObject("recommends", recommends);
        mv.addObject("chapter", chapterName);
        mv.addObject("videoName", course.getCourseDirection().getCourseDirection() + "/" + coursePath + "/" + chapterPath + "/" + videoPath);
        mv.setViewName("video");
        return mv;
    }
}
