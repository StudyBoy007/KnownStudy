package util.auth;

import entity.Course;
import entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.CourseService;
import service.TeacherService;
import util.Msg;

import java.util.List;
import java.util.Map;

/**
 * Create by czq
 * time on 2019/8/30  23:20
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;


    @ExceptionHandler
//    @ResponseBody
    public ModelAndView authHandler(AuthException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Msg.result(401, e.getMessage(), null));
        List<Teacher> teachers = teacherService.selectTeacherIndexService();
        List<Course> courses = courseService.selectCourseIndexService();
        ModelAndView mv = new ModelAndView();
        mv.addObject("teachers", teachers);
        mv.addObject("courses", courses);
        mv.addObject("msg", "login");
        mv.setViewName("index");
        return mv;
    }


}
