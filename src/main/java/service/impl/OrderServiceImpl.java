package service.impl;

import dao.OrderMapper;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.OrderService;

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
}
