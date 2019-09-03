package service;

import entity.Order;
import entity.User;
import util.Msg;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/28  8:59
 */
public interface OrderService {
    int deleteByPrimaryKeyService(Integer id);

    int insertService(Order record);

    Order selectByPrimaryKeyService(Integer id);

    List<Order> selectAllService();

    int updateByPrimaryKeyService(Order record);

    List<Order> selectUserAllOrderService(Integer uid, boolean status);

    int insertMiddleService(String courseIds, Integer oid);


    int deleteOrderBatchService(String ordersId);


    int changeOrderRightByAdmin(Integer oid);


    Msg payOrder(User user, String orderIds, double totalMoney);

    List<Order> selectAllOrderAdminByTime();


    int updateOrder(Order order);

    int insertOrder(Order order);



}
