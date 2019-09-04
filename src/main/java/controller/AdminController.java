package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.*;
import util.DateDefine;
import util.Msg;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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


    @Autowired
    OrderService orderService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseClassService courseClassService;


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


    @ResponseBody
    @RequestMapping("/deleteCourseAdmin")
    public Msg displayCoursePage(int courseId) {
        int i = courseService.deleteByPrimaryKeyService(courseId);
        if (i > 0) {
            return Msg.result(100, "删除成功", null);
        } else {
            return Msg.result(400, "删除失败", null);
        }
    }

    @RequestMapping("/addCourseAdmin")
    public String addCourseAdmin() {
        return "admin/course-add";
    }

    @RequestMapping("/addCourseFunctionAdmin")
    public String addCourseFunctionAdmin(Course course, @RequestParam("file-2") MultipartFile multipartFile) {
        System.out.println(course);
        CourseClass courseClass = courseClassService.selectByPrimaryKeyService(course.getCourseDirection().getId());
        course.setCourseDirection(courseClass);

        //封面进行操作
        String fileName = multipartFile.getOriginalFilename(); //得到文件的名字
        //获取文件原名的后缀
        String substring = fileName.substring(fileName.lastIndexOf("."));
        //获取一个随机的32位的字符串
        String newFileName = UUID.randomUUID().toString();
//        文件的内容写到目标的地址
        File dest = new File("F:\\eplayimg\\course\\" + course.getCourseDirection().getCourseDirection() + "/" + newFileName + substring);
        try {
            //写入到地址
            multipartFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }


        course.setPic(newFileName + substring);
        int i = courseService.insertCourseService(course);
        if (i > 0) {
            File file = new File("F:\\eplayimg\\video\\" + course.getCourseDirection().getCourseDirection() + "/" + course.getCourse_path());
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return "admin/course-list";
    }


    @RequestMapping("/displayChapterAdmin")
    public String displayChapterAdmin(HttpServletRequest request, int courseId) {
        request.getSession().setAttribute("courseId", courseId);
        return "admin/chapter-list";
    }

    @ResponseBody
    @RequestMapping("/displayChapterPage")
    public Msg displayChapterAdmin(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 3);
        int courseId = (int) request.getSession().getAttribute("courseId");
        List<Chapter> chapters = courseService.selectChapterByCourseId(courseId);
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(chapters, 3);
        return Msg.result(100, "课程章节信息", null).add("pageInfo", page);
    }

    @ResponseBody
    @RequestMapping("/editChapterAdmin")
    public Msg editChapterAdmin(int id) {
        Chapter chapter = courseService.selectChapterById(id);
        return new Msg(100, "展示该章节信息", chapter);
    }

    @ResponseBody
    @RequestMapping("/modifyChapterAdmin")
    public Msg modifyChapterAdmin(Chapter chapter) {
        int i = courseService.updateChapter(chapter);
        if (i > 0) {
            return new Msg(100, "更新章节成功", null);
        } else {
            return new Msg(405, "更新失败", null);
        }
    }


    @ResponseBody
    @RequestMapping("/addChapterAdmin")
    public Msg addChapterAdmin(Chapter chapter, HttpServletRequest request) {
        int courseId = (int) request.getSession().getAttribute("courseId");
        chapter.setCourseid(courseId);
        Course course = courseService.selectByPrimaryKeyService(courseId);
        int i = courseService.addChapter(chapter);
        if (i > 0) {
            File file = new File("F:\\eplayimg\\video\\" + course.getCourseDirection().getCourseDirection() + "/" + course.getCourse_path() + "/" + chapter.getPath());
            if (!file.exists()) {
                file.mkdir();
            }
            return new Msg(100, "添加章节成功", null);
        } else {
            return new Msg(100, "添加章节失败", null);
        }
    }


    @ResponseBody
    @RequestMapping("/deleteChapterAdmin")
    public Msg deleteChapterAdmin(int chapterId) {
        int i = courseService.deleteChapter(chapterId);
        if (i > 0) {
            return new Msg(100, "删除章节成功", null);
        } else {
            return new Msg(405, "删除章节失败", null);
        }
    }


    @RequestMapping("/displayVideoAdmin")
    public String displayVideoAdmin(HttpServletRequest request, int chapterId) {
        request.getSession().setAttribute("chapterId", chapterId);
        return "admin/video-list";
    }


    @ResponseBody
    @RequestMapping("/displayVideoPage")
    public Msg displayVideoPage(HttpServletRequest request, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 3);
        int courseId = (int) request.getSession().getAttribute("courseId");
        int chapterId = (int) request.getSession().getAttribute("chapterId");
        List<Video> videos = courseService.selectVideoByChapterId(chapterId);
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(videos, 3);
        return Msg.result(100, "课程章节信息", courseId).add("pageInfo", page);
    }


    @ResponseBody
    @RequestMapping("/editVideoAdmin")
    public Msg editVideoAdmin(int videoId) {
        Video video = courseService.selectVideoById(videoId);
        return new Msg(100, "展示该视频信息", video);
    }


    @ResponseBody
    @RequestMapping("/modifyVideoAdmin")
    public Msg modifyVideoAdmin(Video video, @RequestParam("file-2") MultipartFile multipartFile, HttpServletRequest request) {
        Video video1 = courseService.selectVideoById(video.getId());
        String oldName = video1.getPath();
        long size = multipartFile.getSize();
        if (size > 0) {
            int courseId = (int) request.getSession().getAttribute("courseId");
            int chapterId = (int) request.getSession().getAttribute("chapterId");
            Chapter chapter = courseService.selectChapterById(chapterId);
            Course course = courseService.selectByPrimaryKeyService(courseId);
            //封面进行操作
            String fileName = multipartFile.getOriginalFilename(); //得到文件的名字
            //获取文件原名的后缀
            String substring = fileName.substring(fileName.lastIndexOf("."));
            //获取一个随机的32位的字符串
            String newFileName = UUID.randomUUID().toString();
//        文件的内容写到目标的地址
            File file = new File("F:\\eplayimg\\video\\" + course.getCourseDirection().getCourseDirection() + "/" + course.getCourse_path() + "/" + chapter.getPath() + "/" + newFileName + substring);
            try {
                //写入到地址
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            File oldFile = new File("F:\\eplayimg\\video\\" + course.getCourseDirection().getCourseDirection() + "/" + course.getCourse_path() + "/" + chapter.getPath() + "/" + oldName);
            if (oldFile.exists()) {
                oldFile.delete();
                video.setPath(newFileName + substring);
            }
        }

        int i = courseService.updateVideo(video);
        System.out.println(video);
        if (i > 0) {
            return new Msg(100, "更新视频", null);
        } else {
            return new Msg(405, "更新视频失败", null);
        }
    }


    @ResponseBody
    @RequestMapping("/addVideoAdmin")
    public Msg addVideoAdmin(Video video, @RequestParam("file-2") MultipartFile multipartFile, HttpServletRequest request) {

        int courseId = (int) request.getSession().getAttribute("courseId");
        int chapterId = (int) request.getSession().getAttribute("chapterId");

        Chapter chapter = courseService.selectChapterById(chapterId);
        Course course = courseService.selectByPrimaryKeyService(courseId);

        String fileName = multipartFile.getOriginalFilename(); //得到文件的名字
        //获取文件原名的后缀
        String substring = fileName.substring(fileName.lastIndexOf("."));
        //获取一个随机的32位的字符串
        String newFileName = UUID.randomUUID().toString();
//        文件的内容写到目标的地址
        File file = new File("F:\\eplayimg\\video\\" + course.getCourseDirection().getCourseDirection() + "/" + course.getCourse_path() + "/" + chapter.getPath() + "/" + newFileName + substring);
        try {
            //写入到地址
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        video.setPath(newFileName + substring);
        video.setChapterid(chapterId);
        int i = courseService.insertVideo(video);
        if (i > 0) {
            return new Msg(100, "添加视频成功", null);
        } else {
            return new Msg(405, "添加视频失败", null);
        }
    }


    @ResponseBody
    @RequestMapping("/deleteVideoAdmin")
    public Msg deleteVideoAdmin(int videoId, HttpServletRequest request) {

        int courseId = (int) request.getSession().getAttribute("courseId");
        int chapterId = (int) request.getSession().getAttribute("chapterId");
        Video video = courseService.selectVideoById(videoId);
        String oldName = video.getPath();
        Chapter chapter = courseService.selectChapterById(chapterId);
        Course course = courseService.selectByPrimaryKeyService(courseId);
        int i = courseService.deleteVideo(videoId);
        if (i > 0) {
            File oldFile = new File("F:\\eplayimg\\video\\" + course.getCourseDirection().getCourseDirection() + "/" + course.getCourse_path() + "/" + chapter.getPath() + "/" + oldName);
            if (oldFile.exists()) {
                oldFile.delete();
            }
            return new Msg(100, "删除视频成功", null);
        } else {
            return new Msg(405, "删除视频失败", null);
        }
    }

    @RequestMapping("/displayTeacherAdmin")
    public String displayTeacherAdmin() {
        return "admin/teacher-list";
    }


    @ResponseBody
    @RequestMapping("/displayTeacherPage")
    public Msg displayTeacherPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        List<Teacher> teachers = teacherService.selectAllService();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(teachers, 3);
        return Msg.result(100, "老师信息", null).add("pageInfo", page);
    }


    @ResponseBody
    @RequestMapping("/editTeacherAdmin")
    public Msg editTeacherAdmin(int id) {
        Teacher teacher = teacherService.selectById(id);
        return new Msg(100, "展示该老师信息", teacher);
    }

    @ResponseBody
    @RequestMapping("/modifyTeacherAdmin")
    public Msg modifyTeacherAdmin(Teacher teacher, @RequestParam("file-2") MultipartFile multipartFile) {

        if (multipartFile.getSize() > 0) {
            Teacher teacher1 = teacherService.selectById(teacher.getId());
            String oldFileName = teacher1.getAvatar();

            String fileName = multipartFile.getOriginalFilename(); //得到文件的名字
            //获取文件原名的后缀
            String substring = fileName.substring(fileName.lastIndexOf("."));
            //获取一个随机的32位的字符串
            String newFileName = UUID.randomUUID().toString();
//        文件的内容写到目标的地址
            File file = new File("F:\\eplayimg\\avatar\\" + newFileName + substring);
            try {
                //写入到地址
                multipartFile.transferTo(file);
                File oldFile = new File("F:\\eplayimg\\avatar\\" + oldFileName);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            teacher.setAvatar(newFileName + substring);
        }
        int i = teacherService.updateTeacher(teacher);
        if (i > 0) {
            return new Msg(100, "更新老师信息成功", null);
        } else {
            return new Msg(405, "更新老师信息失败", null);
        }
    }


    @ResponseBody
    @RequestMapping("/addTeacherAdmin")
    public Msg addTeacherAdmin(Teacher teacher, @RequestParam("file-2") MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename(); //得到文件的名字
        //获取文件原名的后缀
        String substring = fileName.substring(fileName.lastIndexOf("."));
        //获取一个随机的32位的字符串
        String newFileName = UUID.randomUUID().toString();
//        文件的内容写到目标的地址
        File file = new File("F:\\eplayimg\\avatar\\" + newFileName + substring);
        try {
            //写入到地址
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        teacher.setAvatar(newFileName + substring);
        int i = teacherService.insertTeacher(teacher);
        if (i > 0) {
            return new Msg(100, "添加老师信息成功", null);
        } else {
            return new Msg(405, "添加老师信息失败", null);
        }
    }


    @ResponseBody
    @RequestMapping("/deleteTeacherAdmin")
    public Msg deleteTeacherAdmin(int teacherId) {
        int i = teacherService.deleteByPrimaryKeyService(teacherId);
        if (i > 0) {
            return new Msg(100, "删除章节成功", null);
        } else {
            return new Msg(405, "删除章节失败", null);
        }
    }


    @RequestMapping("/displayUserAdmin")
    public String displayUserAdmin() {
        return "admin/user-list";
    }


    @ResponseBody
    @RequestMapping("/displayUserPage")
    public Msg displayUserPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        List<User> users = userService.selectAllService();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(users, 3);
        return Msg.result(100, "老师信息", null).add("pageInfo", page);
    }


    @ResponseBody
    @RequestMapping("/editUserAdmin")
    public Msg editUserAdmin(int id) {
        User user = userService.selectByPrimaryKeyService(id);
        return new Msg(100, "展示该用户信息", user);
    }

    @ResponseBody
    @RequestMapping("/modifyUserAdmin")
    public Msg modifyUserAdmin(User user, @RequestParam("file-2") MultipartFile multipartFile) {
        User user2 = userService.selectUsernameIsOrNotExitService(user.getUsername(), user.getId());
        if (user2 != null) {
            return new Msg(405, "该用户名已经存在，修改失败", null);
        }
        if (multipartFile.getSize() > 0) {
            User user1 = userService.selectByPrimaryKeyService(user.getId());
            String oldFileName = user1.getAvatar();

            String fileName = multipartFile.getOriginalFilename(); //得到文件的名字
            //获取文件原名的后缀
            String substring = fileName.substring(fileName.lastIndexOf("."));
            //获取一个随机的32位的字符串
            String newFileName = UUID.randomUUID().toString();
//        文件的内容写到目标的地址
            File file = new File("F:\\eplayimg\\avatar\\" + newFileName + substring);
            try {
                //写入到地址
                multipartFile.transferTo(file);
                File oldFile = new File("F:\\eplayimg\\avatar\\" + oldFileName);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setAvatar(newFileName + substring);
        }
        int i = userService.updateUserService(user);
        if (i > 0) {
            return new Msg(100, "更新老师信息成功", null);
        } else {
            return new Msg(405, "更新老师信息失败", null);
        }
    }


    @ResponseBody
    @RequestMapping("/addUserAdmin")
    public Msg addUserAdmin(User user, @RequestParam("file-2") MultipartFile multipartFile) {
        Msg msg = userService.selectByUsernameService(user.getUsername());
        if (msg.getCode() == 200) {
            return msg;
        }
        String fileName = multipartFile.getOriginalFilename(); //得到文件的名字
        //获取文件原名的后缀
        String substring = fileName.substring(fileName.lastIndexOf("."));
        //获取一个随机的32位的字符串
        String newFileName = UUID.randomUUID().toString();
//        文件的内容写到目标的地址
        File file = new File("F:\\eplayimg\\avatar\\" + newFileName + substring);
        try {
            //写入到地址
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setAvatar(newFileName + substring);
        int i = userService.insertUser(user);
        if (i > 0) {
            return new Msg(100, "添加用户信息成功", null);
        } else {
            return new Msg(405, "添加用户信息失败", null);
        }
    }


    @ResponseBody
    @RequestMapping("/deleteUserAdmin")
    public Msg deleteUserAdmin(int userId) {
        int i = userService.deleteByPrimaryKeyService(userId);
        if (i > 0) {
            return new Msg(100, "删除章节成功", null);
        } else {
            return new Msg(405, "删除章节失败", null);
        }
    }


    @RequestMapping("/displayOrderAdmin")
    public String displayOrderAdmin() {
        return "admin/order-list";
    }


    @ResponseBody
    @RequestMapping("/displayOrderPage")
    public Msg displayOrderPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        List<Order> orders = orderService.selectAllOrderAdminByTime();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(orders, 3);
        return Msg.result(100, "老师信息", null).add("pageInfo", page);
    }


    @ResponseBody
    @RequestMapping("/editOrderAdmin")
    public Msg editOrderAdmin(int id) {
        Order order = orderService.selectByPrimaryKeyService(id);
        return new Msg(100, "展示该订单信息", order);
    }

    @ResponseBody
    @RequestMapping("/modifyOrderAdmin")
    public Msg modifyOrderAdmin(Order order) {
        int i = orderService.updateOrder(order);
        if (i > 0) {
            return new Msg(100, "更新订单成功", null);
        } else {
            return new Msg(405, "更新订单失败", null);
        }
    }


    @ResponseBody
    @RequestMapping("/addOrderAdmin")
    public Msg addOrderAdmin(Order order) {
        User user = userService.selectByPrimaryKeyService(order.getUser().getId());
        if (user == null) {
            return Msg.result(405, "该用户编号不存在", null);
        } else {
            order.setCreateTime(DateDefine.getStringDate2());
            int i = orderService.insertOrderService(order);
            if (i > 0) {
                return new Msg(100, "添加订单成功", null);
            } else {
                return new Msg(405, "添加订单失败", null);
            }
        }
    }


    @ResponseBody
    @RequestMapping("/deleteOrderAdmin")
    public Msg deleteOrderAdmin(int orderId) {
        int i = orderService.deleteByPrimaryKeyService(orderId);
        if (i > 0) {
            return new Msg(100, "删除订单成功", null);
        } else {
            return new Msg(405, "删除订单失败", null);
        }
    }


    @RequestMapping("/displayContentAdmin")
    public String displayContentAdmin(HttpServletRequest request, int orderId) {
        request.getSession().setAttribute("orderId", orderId);
        return "admin/content-list";
    }


    @ResponseBody
    @RequestMapping("/displayContentPage")
    public Msg displayContentPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn, HttpServletRequest request) {
        int orderId = (int) request.getSession().getAttribute("orderId");
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 3);
        List<Course> courses = orderService.selectMiddleInOrderWithCourseService(orderId);
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(courses, 3);
        return Msg.result(100, "老师信息", orderId).add("pageInfo", page);
    }


    @ResponseBody
    @RequestMapping("/editContentAdmin")
    public Msg editContentAdmin(int id, HttpServletRequest request) {
        request.getSession().setAttribute("oldCid", id);
        return new Msg(100, "展示该订单商品信息", id);
    }

    @ResponseBody
    @RequestMapping("/modifyContentAdmin")
    public Msg modifyContentAdmin(int courseId, HttpServletRequest request) {
        int orderId = (int) request.getSession().getAttribute("orderId");
        Course course = courseService.selectByPrimaryKeyService(courseId);
        if (course == null) {
            return new Msg(405, "该课程不存在，修改订单内容失败", null);
        } else {
            int i = orderService.selectCourseInOrder2Service(orderId, courseId);
            if (i > 0) {
                return new Msg(405, "该课程已经存在该订单中，修改订单失败", null);
            }
            int oldCid = (int) request.getSession().getAttribute("oldCid");
            int i1 = orderService.updateOrderContentByOidAndCidService(orderId, courseId, oldCid);
            if (i1 > 0) {
                int courseNum = orderService.selectMiddleInOrderWithCourseNumService(orderId);
                double totalPrice = orderService.selectMiddleInOrderWithCourseTotalPriceService(orderId);
                Order order = new Order(orderId, courseNum, totalPrice);
                int i2 = orderService.updateOrder(order);
               if(i2>0){
                   return new Msg(100, "更新订单商品成功", null);
               }else {
                   return new Msg(405, "更新订单商品失败", null);
               }
            } else {
                return new Msg(405, "更新订单商品失败", null);
            }
        }
    }


    @ResponseBody
    @RequestMapping("/addContentAdmin")
    public Msg addContentAdmin(int courseId, HttpServletRequest request) {
        int orderId = (int) request.getSession().getAttribute("orderId");
        Course course = courseService.selectByPrimaryKeyService(courseId);
        if (course == null) {
            return new Msg(405, "该课程不存在，添加订单内容失败", null);
        } else {
            int i = orderService.selectCourseInOrder2Service(orderId, courseId);
            if (i > 0) {
                return new Msg(405, "该课程已经存在该订单中，添加订单失败", null);
            }
            int i1 = orderService.insertMiddleInOrderWithCourseService(orderId, courseId);
            if (i1 > 0) {
                int courseNum = orderService.selectMiddleInOrderWithCourseNumService(orderId);
                double totalPrice = orderService.selectMiddleInOrderWithCourseTotalPriceService(orderId);
                Order order = new Order(orderId, courseNum, totalPrice);
                int i2 = orderService.updateOrder(order);
                if(i2>0){
                    return new Msg(100, "添加订单商品成功", null);
                }else {
                    return new Msg(405, "添加订单商品失败", null);
                }
            } else {
                return new Msg(405, "添加更新订单商品失败", null);
            }
        }
    }


    @ResponseBody
    @RequestMapping("/deleteContentAdmin")
    public Msg deleteContentAdmin(int contentId,HttpServletRequest request) {
        int orderId = (int) request.getSession().getAttribute("orderId");
        int i = orderService.deleteMiddleByOidAndCid(orderId, contentId);
        if (i > 0) {
            int courseNum = orderService.selectMiddleInOrderWithCourseNumService(orderId);
            double totalPrice = orderService.selectMiddleInOrderWithCourseTotalPriceService(orderId);
            Order order = new Order(orderId, courseNum, totalPrice);
            int i2 = orderService.updateOrder(order);
            if(i2>0){
                return new Msg(100, "删除订单商品成功", null);
            }else {
                return new Msg(405, "删除订单商品失败", null);
            }
        } else {
            return new Msg(405, "删除订单失败", null);
        }
    }
}

