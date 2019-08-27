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
import util.Msg;

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


    @RequestMapping("/shopCart")
    public ModelAndView cartDisplay(int courseId) {

        Course course = courseService.selectByPrimaryKeyService(courseId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("course", course);
        mv.setViewName("card");
        return mv;
    }


    @ResponseBody
    @RequestMapping("/addCart")
    public Msg addCart(int courseId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int i1 = courseService.juifyCourseIsOrNotBuyService(user.getId(), courseId);
        if (i1 == 1) {
            return Msg.result(101, "尊贵的用户，该课程您已经购买，是否前往观看", null);
        }
        int i = cartService.insertService(new Cart(user, new Course(courseId)));
        if (i == 1) {
            return Msg.result(100, "添加购物车成功，是否查看购物车", null);
        } else {
            return Msg.result(200, "添加购物车失败", null);
        }
    }


    @RequestMapping("/delCart")
    public void delCart(int cartId) {
        int i = cartService.deleteByPrimaryKeyService(cartId);
    }


    @RequestMapping("/displayCart")
    public ModelAndView displayCart(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        List<Cart> carts = cartService.selectAllCartByUserIdService(user.getId());
        mv.addObject("carts", carts);
        mv.setViewName("cart");
        return mv;
    }
}
