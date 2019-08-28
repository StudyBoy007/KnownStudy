package service;

import entity.Order;

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
}
