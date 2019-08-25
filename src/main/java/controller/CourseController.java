package controller;

import dao.CourseClassMapper;
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

import java.util.List;

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
        mv.setViewName("course");
        return mv;
    }


    @RequestMapping("teacherCourse")
    public ModelAndView teachert(int tid, int direction_id) {
        Course course = new Course(new CourseClass(direction_id), new Teacher(tid));
        List<Course> courses = courseService.selectCourseByDirectionAndTidService(course);
        List<CourseClass> courseClasses = courseClassService.selectCourseClassService();
        List<Teacher> teachers = teacherService.selectTeacherByDirectionService(new Teacher(new CourseClass(direction_id)));
        ModelAndView mv = new ModelAndView();
        mv.addObject("courseClasses", courseClasses);
        mv.addObject("teachers", teachers);
        mv.addObject("courses", courses);
        mv.addObject("index", direction_id);
        mv.addObject("index1", tid);
        mv.setViewName("course");
        return mv;
    }


    @RequestMapping("conditionCourse")
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
}
