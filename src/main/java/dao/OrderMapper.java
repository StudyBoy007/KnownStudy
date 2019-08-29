package dao;

import entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);

    List<Order> selectUserAllOrder(@Param("id") Integer uid, @Param("status") boolean status);


    int insertMiddle(@Param("courses") List<Integer> list, @Param("oid") Integer oid);

    int selectCourseInOrder(@Param("uid") Integer uid, @Param("cid") Integer cid);


    int deleteOrderBatch(@Param("orderIds") List<Integer> orderIds);

    int changeOrderRightByAdmin(Integer oid);

    int changeOrderStatus(@Param("orders") List<Integer> orders);

    int createConnectionInUserAndCourseBatch(@Param("courses") List<Integer> list, @Param("uid") Integer uid);

    int createConnectionInUserAndCourse(@Param("uid") Integer uid, @Param("cid") Integer cid);

    int changeBuyCourseState(@Param("uid") Integer uid, @Param("cid") Integer cid);

    List<Integer> selectOrderCourse(Integer oid);
}