package controller;

import dao.UserMapper;
import entity.Course;
import entity.Teacher;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.CourseService;
import service.TeacherService;
import service.UserService;
import util.MD5Util;
import util.Msg;
import util.RandomNum;
import util.auth.AuthUtils;
import util.auth.RequireRole;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Create by czq
 * time on 2019/8/21  9:31
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;


    //加载首页信息
    @RequestMapping("/")
    public ModelAndView indexDisplay() {
        List<Teacher> teachers = teacherService.selectTeacherIndexService();
        List<Course> courses = courseService.selectCourseIndexService();
        ModelAndView mv = new ModelAndView();
        mv.addObject("teachers", teachers);
        mv.addObject("courses", courses);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/exit")
    public String exitIndexDisplay(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }


    //获取登陆验证码
    @RequestMapping("/getJuifyCode")
    public void getJuifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);


        BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Random random = new Random();
        Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        g.setColor(color);
        g.fillRect(0, 0, 68, 22);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char c = RandomNum.getString();
            g.setColor(new Color(random.nextInt(88), random.nextInt(188), random.nextInt(255)));
            g.drawString(String.valueOf(c), (i * 15 + 3), 16);
            stringBuilder.append(c);
        }

        for (int i = 0; i < 4; i++) {
            Color color1 = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            g.setColor(color1);
            final int x = random.nextInt(68);
            final int y = random.nextInt(22);
            final int w = random.nextInt(20);
            final int h = random.nextInt(20);
            final int signA = random.nextBoolean() ? 1 : -1;
            final int signB = random.nextBoolean() ? 1 : -1;
            g.drawLine(x, y, x + w * signA, y + h * signB);
        }
        //发布服务器生成的数字等会与前台传来的code进行对比
        request.getSession().setAttribute("picCode", stringBuilder.toString());
        //输出
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }

    //校验登录用户
    @ResponseBody
    @RequestMapping("/juifyUser")
    public Msg juifyUser(String loginName, String loginPwd, String code, HttpServletRequest request) {

        String picCode = (String) request.getSession().getAttribute("picCode");
        if (picCode == null || code == null) {
            return Msg.result(400, "验证码错误", null);
        }
        if (picCode.toLowerCase().equals(code.toLowerCase())) {
            Msg msg = userService.selectByUsernameAndPwdService(loginName, MD5Util.md5(loginPwd));
            User user = (User) msg.getO();
            request.getSession().setAttribute("user", user);
            AuthUtils.setRole("guest");
            return msg;
        } else {
            return Msg.result(400, "验证码错误", null);
        }
    }


    //用户注册
    @ResponseBody
    @RequestMapping("/regUser")
    public Msg reg(User user) {
        user.setPassword(MD5Util.md5(user.getPassword()));
        Msg msg = userService.selectByUsernameService(user.getUsername());
        if (msg.getCode() == 100) {
            Msg msg1 = userService.saveUserService(user);
            return msg1;
        } else {
            return msg;
        }

    }


    //展示用户信息
    @RequestMapping("/userInfo")
    @RequireRole("guest")
    public ModelAndView displayUserInfo(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Course> coursesCollect = courseService.selectUerCourseCollectService(user.getId());
        List<Course> coursesBuy = courseService.selectUserCourseBuyService(user.getId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("collects", coursesCollect);
        mv.addObject("buys", coursesBuy);
        mv.setViewName("info");
        return mv;
    }

    //展示用户信息
    @RequestMapping("/otherInfo")
    @RequireRole("guest")
    public ModelAndView displayOtherInfo(HttpServletRequest request, int uid) {
        List<Course> coursesCollect = courseService.selectUerCourseCollectService(uid);
        List<Course> coursesBuy = courseService.selectUserCourseBuyService(uid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("collects", coursesCollect);
        mv.addObject("buys", coursesBuy);
        mv.setViewName("otherInfo");
        return mv;
    }

    @RequestMapping("/userAccountCharge")
    @RequireRole("guest")
    public String userAccountRecharge() {
        return "recharge";
    }


    //用户充值
    @ResponseBody
    @RequestMapping("/recharge")
    @RequireRole("guest")
    public Msg rechargeAccount(HttpServletRequest request, double accountNumber) {
        User user = (User) request.getSession().getAttribute("user");

        //充值
        userService.rechargeService(accountNumber, user.getId());

        //直接更新数据放回给前台
        user.setAccount(user.getAccount() + accountNumber);
        return Msg.result(100, "充值" + accountNumber + "元成功", user);
    }


    @ResponseBody
    @RequestMapping("/changAvatar")
    public Msg updateAvatar(HttpServletRequest request, @RequestParam("avatar") MultipartFile multipartFile) {
        //使用HttpServletRequest接收普通类型的参数，用MultiPart接收文件类型的参数
        //用MultiPart接收文件类型的参数
        String fileName = multipartFile.getOriginalFilename(); //得到文件的名字
        //获取文件原名的后缀
        String substring = fileName.substring(fileName.lastIndexOf("."));
        //获取一个随机的32位的字符串
        String newFileName = UUID.randomUUID().toString();
//        文件的内容写到目标的地址
        File dest = new File("F:\\eplayimg\\avatar\\" + newFileName + substring);
        try {
            //写入到地址
            multipartFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute("user");
        String oldName = user.getAvatar();
        user.setAvatar(newFileName + substring);
        request.getSession().setAttribute("user", user);
        int i = userService.updateUserService(user);
        if (i == 1) {
            if (!fileName.equals("default.jpg")) {
                File file = new File("F:\\eplayimg\\avatar\\" + oldName);
                file.delete();
            }
            return Msg.result(100, "头像修改成功", user);
        } else {
            return Msg.result(400, "头像修改失败", user);
        }
    }

    @ResponseBody
    @RequestMapping("/updateUserInfo")
    public Msg updateUserInfo(User user, HttpServletRequest request) {
        User user1 = (User) request.getSession().getAttribute("user");
        if (user1.getUsername().equals(user.getUsername())) {
            int i = userService.updateUserService(user);
            if (i == 1) {
                User user2 = userService.selectByPrimaryKeyService(user.getId());
                request.getSession().setAttribute("user", user2);
                return Msg.result(100, "修改成功", user2);
            } else {
                return Msg.result(400, "修改失败", null);
            }
        } else {
            Msg msg = userService.selectByUsernameService(user.getUsername());
            if (msg.getCode() == 100) {
                int i = userService.updateUserService(user);
                if (i == 1) {
                    User user2 = userService.selectByPrimaryKeyService(user.getId());
                    request.getSession().setAttribute("user", user2);
                    return Msg.result(100, "修改成功", user2);
                } else {
                    return Msg.result(400, "修改失败", null);
                }
            } else {
                return msg;
            }
        }
    }
}
