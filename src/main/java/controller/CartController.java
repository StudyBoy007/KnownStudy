package controller;

import dao.CartMapper;
import entity.Cart;
import entity.Course;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.CartService;
import service.CourseService;
import service.UserService;
import util.Msg;
import util.auth.RequireRole;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.List;

/**
 * Create by czq
 * time on 2019/8/27  16:44
 */
@Controller
public class CartController {

    @Autowired
    CourseService courseService;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;


    @RequestMapping("/shopCart")
    public ModelAndView cartDisplay(int courseId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int i = 0;
        if (user != null) {
            i = userService.selectIsOrNotCollect(user.getId(), courseId);
        }

        Course course = courseService.selectByPrimaryKeyService(courseId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("collect", i);
        mv.addObject("course", course);
        mv.setViewName("card");
        return mv;
    }


    @ResponseBody
    @RequestMapping("/addCart")
    @RequireRole("guest")
    public Msg addCart(int courseId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Msg msg = courseService.juifyCourseIsOrNotBuyService(user.getId(), courseId);
        if (msg.getCode() != 100) {
            return msg;
        }
        int i = cartService.insertService(new Cart(user, new Course(courseId)));
        if (i == 1) {
            return msg;
        } else {
            return Msg.result(200, "添加购物车失败", null);
        }
    }


    @RequestMapping("/delCart")
    @RequireRole("guest")
    public void delCart(int cartId) {
        int i = cartService.deleteByPrimaryKeyService(cartId);
    }


    @RequestMapping("/displayCart")
    @RequireRole("guest")
    public ModelAndView displayCart(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        List<Cart> carts = cartService.selectAllCartByUserIdService(user.getId());
        mv.addObject("carts", carts);
        mv.setViewName("cart");
        return mv;
    }
}
