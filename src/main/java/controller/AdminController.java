package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.CourseService;
import service.UserService;
import util.Msg;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Create by czq
 * time on 2019/9/1  12:47
 */
@Controller
public class AdminController {
    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;


    @RequestMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    @RequestMapping("/displayCourseAdmin")
    public String displayCourse() {
        return "admin/course-list";
    }


    @ResponseBody
    @RequestMapping("/displayCoursePage")
    public Msg displayCoursePage(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        List<Course> courses = courseService.selectAllService();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(courses, 3);
        return Msg.result(100, "课程信息", null).add("pageInfo", page);
    }


    @RequestMapping("/editCourseAdmin")
    public ModelAndView editCourse(int courseId) {
        Course course = courseService.selectByPrimaryKeyService(courseId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("course", course);
        mv.setViewName("admin/course-edit");
        return mv;
    }

    @RequestMapping("/updateCourseAdmin")
    public String updateCourse(Course course, @RequestParam("file-2") MultipartFile multipartFile) {
        System.out.println(course);
        //使用HttpServletRequest接收普通类型的参数，用MultiPart接收文件类型的参数
        //用MultiPart接收文件类型的参数
        if (multipartFile.getSize() > 0) {
            String fileName = multipartFile.getOriginalFilename(); //得到文件的名字
            //获取文件原名的后缀
            String substring = fileName.substring(fileName.lastIndexOf("."));
            //获取一个随机的32位的字符串
            String newFileName = UUID.randomUUID().toString();
            //旧名称
            Course course1 = courseService.selectByPrimaryKeyService(course.getId());
            String oldName = course1.getPic();
//        文件的内容写到目标的地址
            File dest = new File("F:\\eplayimg\\course\\" + course.getCourseDirection().getCourseDirection() + "/" + newFileName + substring);
            try {
                //写入到地址
                multipartFile.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            course.setPic(newFileName + substring);
            File file = new File("F:\\eplayimg\\course\\" + course.getCourseDirection().getCourseDirection() + "/" + oldName);
            file.delete();
        }
        courseService.updateByPrimaryKey2Service(course);
        return "admin/course-list";
    }
}

