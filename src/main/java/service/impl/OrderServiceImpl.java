package service.impl;

import dao.OrderMapper;
import dao.UserMapper;
import entity.Course;
import entity.Order;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.OrderService;
import util.Msg;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by czq
 * time on 2019/8/28  9:00
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper mapper;


    @Autowired
    UserMapper userMapper;

    @Override
    public int deleteByPrimaryKeyService(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int insertService(Order record) {
        int insert = mapper.insert(record);
        return insert;
    }

    @Override
    public int insertOrderService(Order record) {
        int insert = mapper.insertOrder(record);
        return insert;
    }

    @Override
    public Order selectByPrimaryKeyService(Integer id) {
        Order order = mapper.selectByPrimaryKey(id);
        return order;
    }

    @Override
    public List<Order> selectAllService() {
        return null;
    }

    @Override
    public int updateByPrimaryKeyService(Order record) {
        return 0;
    }

    @Override
    public List<Order> selectUserAllOrderService(Integer uid, boolean status) {
        List<Order> orders = mapper.selectUserAllOrder(uid, status);
        return orders;
    }

    @Override
    public int insertMiddleService(String courseIds, Integer oid) {

        String[] coursesId = courseIds.split(",");

        List<Integer> courses = new ArrayList<>();
        for (String courseId : coursesId) {
            courses.add(Integer.parseInt(courseId));
        }
        int i = mapper.insertMiddle(courses, oid);
        return i;
    }

    @Override
    public int insertMiddleService2(Integer cid, Integer oid) {
        List<Integer> courses = new ArrayList<>();
        courses.add(cid);
        int i = mapper.insertMiddle(courses, oid);
        return i;
    }


    @Override
    public int deleteOrderBatchService(String ordersId) {
        String[] split = ordersId.split(",");
        List<Integer> orderIds = new ArrayList<>();
        for (String s : split) {
            orderIds.add(Integer.parseInt(s));
        }
        int i = mapper.deleteOrderBatch(orderIds);
        return i;
    }

    @Override
    public int changeOrderRightByAdmin(Integer oid) {
        int i = mapper.changeOrderRightByAdmin(oid);
        return i;
    }

    @Override
    public Msg payOrder(User user, String orderIds, double totalMoney) {
        //判断账户余额是否足够
        if (totalMoney > user.getAccount()) {
            return new Msg(405, "账户余额不够，是否前往充值", null);
        } else {
            String[] split = orderIds.split(",");
            List<Integer> ordersId = new ArrayList<>();
            for (String s : split) {
                ordersId.add(Integer.parseInt(s));
            }

            //增加用户和课程的关系
            for (Integer id : ordersId) {
                List<Integer> coursesId = mapper.selectOrderCourse(id);
                for (Integer integer : coursesId) {
                    int i = userMapper.selectCollectConnectionInUserAndCourse(user.getId(), integer);
                    if (i == 0) {
                        mapper.createConnectionInUserAndCourse(user.getId(), integer);
                    } else {
                        mapper.changeBuyCourseState(user.getId(), integer);
                    }
                }
            }

            //扣账户金额
            userMapper.payAccount(totalMoney, user.getId());

            //改变订单的状态
            mapper.changeOrderStatus(ordersId);

            Msg msg = Msg.result(100, "付款成功", null);
            List<Order> orders = mapper.selectUserAllOrder(user.getId(), true);
            msg.getExtend().put("orders", orders);
            return msg;
        }
    }

    @Override
    public List<Order> selectAllOrderAdminByTime() {
        List<Order> orders = mapper.selectAllOrderAdminByTime2();
        return orders;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int updateOrder(Order order) {
        int i = mapper.updateOrder(order);
        return i;
    }

    @Override
    public int insertOrder(Order order) {
        int i = mapper.insertOrder(order);
        return i;
    }

    @Override
    public List<Course> selectMiddleInOrderWithCourseService(int oid) {
        List<Course> courses = mapper.selectMiddleInOrderWithCourse(oid);
        return courses;
    }

    @Override
    public int updateOrderContentByOidAndCidService(Integer oid, Integer cid, Integer oldCid) {
        int i = mapper.updateOrderContentByOidAndCid(oid, cid, oldCid);
        return i;
    }

    @Override
    public double selectMiddleInOrderWithCourseTotalPriceService(int oid) {
        double v = mapper.selectMiddleInOrderWithCourseTotalPrice(oid).orElse(0.0);
        return v;
    }

    @Override
    public int selectMiddleInOrderWithCourseNumService(int oid) {
        int i = mapper.selectMiddleInOrderWithCourseNum(oid).orElse(0);
        return i;
    }

    @Override
    public int insertMiddleInOrderWithCourseService(Integer oid, Integer cid) {
        int i = mapper.insertMiddleInOrderWithCourse(oid, cid);
        return i;
    }

    @Override
    public int selectCourseInOrder2Service(Integer oid, Integer cid) {
        int i = mapper.selectCourseInOrder2(oid, cid);
        return i;
    }

    @Override
    public int deleteMiddleByOidAndCid(Integer oid, Integer cid) {
        int i = mapper.deleteMiddleByOidAndCid(oid, cid);
        return i;
    }
}
