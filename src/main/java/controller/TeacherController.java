package controller;

import entity.Course;
import entity.Teacher;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.TeacherService;
import util.Msg;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by czq
 * time on 2019/8/24  11:26
 */
@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @ResponseBody
    @RequestMapping("/doFocusTeacher")
    public Msg doFocusTeacher(int teacherId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Msg msg = teacherService.addFocusTeacherService(user.getId(), teacherId);
        return msg;
    }

    @ResponseBody
    @RequestMapping("/deleteFocusTeacher")
    public Msg deleteFocusTeacher(int teacherId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Msg msg = teacherService.delFocusTeacherService(user.getId(), teacherId);
        return msg;
    }


    @RequestMapping("teacherInfo")
    public ModelAndView displayTeacherInfo(int id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int i = 0;
        if (user != null) {
            i = teacherService.selectConnectionInUserAndTeachaer(user.getId(), id);
        }
        Teacher teacher = teacherService.selectById(id);
        ModelAndView mv = new ModelAndView();
        List<Course> courses = teacher.getCourses();
        List<Course> pay = new ArrayList<>();
        List<Course> free = new ArrayList<>();
        for (Course course : courses) {
            if (course.getIsfress() == 0) {
                free.add(course);
            } else {
                pay.add(course);
            }
        }
        mv.addObject("focus", i);
        mv.addObject("teacher", teacher);
        mv.addObject("pays", pay);
        mv.addObject("frees", free);
        mv.setViewName("teacherInfo");
        return mv;
    }


}
