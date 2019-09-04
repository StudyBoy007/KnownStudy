package dao;

import entity.Course;
import entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);

    List<Order> selectUserAllOrder(@Param("id") Integer uid, @Param("status") boolean status);

    List<Order> selectAllOrderAdminByTime();

    List<Order> selectAllOrderAdminByTime2();


    int insertMiddle(@Param("courses") List<Integer> list, @Param("oid") Integer oid);

    int selectCourseInOrder(@Param("uid") Integer uid, @Param("cid") Integer cid);


    int deleteOrderBatch(@Param("orderIds") List<Integer> orderIds);

    int changeOrderRightByAdmin(Integer oid);

    int changeOrderStatus(@Param("orders") List<Integer> orders);

    int createConnectionInUserAndCourseBatch(@Param("courses") List<Integer> list, @Param("uid") Integer uid);

    int createConnectionInUserAndCourse(@Param("uid") Integer uid, @Param("cid") Integer cid);

    int changeBuyCourseState(@Param("uid") Integer uid, @Param("cid") Integer cid);

    List<Integer> selectOrderCourse(Integer oid);

    int updateOrder(Order order);

    int insertOrder(Order order);



    //根据订单id查找订单中的所有商品
    List<Course> selectMiddleInOrderWithCourse(int oid);


    //修改订单中的商品
    int updateOrderContentByOidAndCid(@Param("oid") Integer oid, @Param("cid") Integer cid,@Param("oldCid") Integer oldCid);

    //订单商品修改后订单中的价格和商品数量都要改变
    //计算订单所有商品一起的总价格
    Optional<Double> selectMiddleInOrderWithCourseTotalPrice(int oid);

    //订单商品总量
    Optional<Integer> selectMiddleInOrderWithCourseNum(int oid);

    //增加订单中的商品
    int insertMiddleInOrderWithCourse(@Param("oid") Integer oid, @Param("cid") Integer cid);


    //判断该课程是否存在与该订单中
    int selectCourseInOrder2(@Param("oid") Integer oid, @Param("cid") Integer cid);

    int deleteMiddleByOidAndCid(@Param("oid") Integer oid, @Param("cid") Integer cid);

}