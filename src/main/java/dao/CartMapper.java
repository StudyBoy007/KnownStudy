package dao;

import entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    Cart selectByPrimaryKey(Integer id);

    List<Cart> selectAll();

    int updateByPrimaryKey(Cart record);

    List<Cart> selectAllCartByUserId(Integer id);

    int deleteCart(@Param("cartIds") List<Integer> carts);

    int selectCartByUserIdAndCourseId(@Param("uid") Integer uid, @Param("cid") Integer cid);
}