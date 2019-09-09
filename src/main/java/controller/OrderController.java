package controller;

import entity.Order;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.CartService;
import service.CourseService;
import service.OrderService;
import util.DateDefine;
import util.Msg;
import util.auth.RequireRole;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by czq
 * time on 2019/8/28  10:42
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @Autowired
    CourseService courseService;

    @ResponseBody
    @RequestMapping("/createOrder")
    @RequireRole("guest")
    public Msg createOrder(double totalMoney, String strid, String cartIds, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        Order order = new Order(strid.split(",").length, false, totalMoney, user, DateDefine.getStringDate2());

        //删除添加入订单的购物车商品
        cartService.deleteCart(cartIds);

        //生成订单
        orderService.insertService(order);


        //订单商品中间表
        orderService.insertMiddleService(strid, order.getId());
        return new Msg(100, "订单生成成功", order.getId());
    }

    @ResponseBody
    @RequestMapping("/createOrder2")
    @RequireRole("guest")
    public Msg createOrder2(int courseId, double price, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Msg msg = courseService.juifyCourseIsOrNotBuyOrInOrder(user.getId(), courseId);
        if (msg.getCode() != 100) {
            return msg;
        }
        Order order = new Order(1, false, price, user, DateDefine.getStringDate2());

        orderService.insertService(order);

        orderService.insertMiddleService2(courseId, order.getId());

        msg.setO(order.getId());
        return msg;
    }


    @ResponseBody
    @RequestMapping("/payOrder")
    @RequireRole("guest")
    public Msg payOrder(double totalMoney, String orderIds, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Msg msg = orderService.payOrder(user, orderIds, totalMoney);
        return msg;
    }


    @RequestMapping("/deleteOrder")
    @RequireRole("guest")
    public void deleteOrder(String orderIds) {
        orderService.deleteOrderBatchService(orderIds);
    }

    @RequestMapping("/deleteHistoryOrder")
    @RequireRole("guest")
    public void deleteHistoryOrder(int orderId) {
        orderService.changeOrderRightByAdmin(orderId);
    }


    @RequestMapping("/displayOrder")
    @RequireRole("guest")
    public ModelAndView displayOrder(HttpServletRequest request, int id) {
        User user = (User) request.getSession().getAttribute("user");

        //得到为付款的订单
        List<Order> orders = orderService.selectUserAllOrderService(user.getId(), false);

        //得到已经付款过的订单历史记录
        List<Order> ordersHistory = orderService.selectUserAllOrderService(user.getId(), true);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", orders);
        mv.addObject("historyOrders", ordersHistory);
        if (id != 0) {
            mv.addObject("activeOrder", id);
        }
        mv.setViewName("order");
        return mv;
    }
}
