package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.TeacherService;

/**
 * Create by czq
 * time on 2019/8/24  11:26
 */
@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    
}
