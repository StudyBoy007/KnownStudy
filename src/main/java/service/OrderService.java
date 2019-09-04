package service;

import entity.Course;
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

    int insertOrderService(Order record);

    Order selectByPrimaryKeyService(Integer id);

    List<Order> selectAllService();

    int updateByPrimaryKeyService(Order record);

    List<Order> selectUserAllOrderService(Integer uid, boolean status);

    int insertMiddleService(String courseIds, Integer oid);

    int insertMiddleService2(Integer cid, Integer oid);

    int deleteOrderBatchService(String ordersId);


    int changeOrderRightByAdmin(Integer oid);


    Msg payOrder(User user, String orderIds, double totalMoney);

    List<Order> selectAllOrderAdminByTime();


    int updateOrder(Order order);

    int insertOrder(Order order);


    //根据订单id查找订单中的所有商品
    List<Course> selectMiddleInOrderWithCourseService(int oid);


    //修改订单中的商品
    int updateOrderContentByOidAndCidService(Integer oid, Integer cid, Integer oldCid);

    //订单商品修改后订单中的价格和商品数量都要改变
    //计算订单所有商品一起的总价格
    double selectMiddleInOrderWithCourseTotalPriceService(int oid);

    //订单商品总量
    int selectMiddleInOrderWithCourseNumService(int oid);

    //增加订单中的商品
    int insertMiddleInOrderWithCourseService(Integer oid, Integer cid);


    //判断该课程是否存在与该订单中
    int selectCourseInOrder2Service(Integer oid, Integer cid);

    int deleteMiddleByOidAndCid(Integer oid, Integer cid);


}
