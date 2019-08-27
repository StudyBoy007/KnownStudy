package service;

import entity.Cart;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/27  18:56
 */
public interface CartService {
    int deleteByPrimaryKeyService(Integer id);

    int insertService(Cart record);

    Cart selectByPrimaryKeyService(Integer id);

    List<Cart> selectAllService();

    int updateByPrimaryKeyService(Cart record);

    List<Cart> selectAllCartByUserIdService(Integer id);

}
