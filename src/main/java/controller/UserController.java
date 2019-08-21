package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import util.Msg;
import util.RandomNum;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Create by czq
 * time on 2019/8/21  9:31
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

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
            Msg msg = userService.selectByUsernameService(loginName, loginPwd);
            User user = (User) msg.getO();
            request.getSession().setAttribute("user", user);
            return msg;
        } else {
            return Msg.result(400, "验证码错误", null);
        }
    }


    //用户注册
    @ResponseBody
    @RequestMapping("/regUser")
    public Msg reg(User user) {
        System.out.println(user);
        int i = userService.saveUserService(user);
        if (i == 1) {
            return Msg.result(100, "注册成功", null);
        } else {
            return Msg.result(400, "注册失败", null);
        }
    }


    //展示用户信息
    @RequestMapping("/userInfo")
    public ModelAndView displayUserInfo(int id) {
        System.out.println(id);
        return null;
    }
}
