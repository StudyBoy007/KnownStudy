package service.impl;

import dao.OrderMapper;
import dao.UserMapper;
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
        return 0;
    }

    @Override
    public int insertService(Order record) {
        int insert = mapper.insert(record);
        return insert;
    }

    @Override
    public Order selectByPrimaryKeyService(Integer id) {
        return null;
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

        //将
        List<Integer> courses = new ArrayList<>();
        for (String courseId : coursesId) {
            courses.add(Integer.parseInt(courseId));
        }
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
}
