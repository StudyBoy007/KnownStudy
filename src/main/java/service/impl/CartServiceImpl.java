package service.impl;

import dao.CartMapper;
import entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CartService;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/27  18:57
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;

    @Override
    public int deleteByPrimaryKeyService(Integer id) {
        int i = cartMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public int insertService(Cart record) {
        int insert = cartMapper.insert(record);
        return insert;
    }

    @Override
    public Cart selectByPrimaryKeyService(Integer id) {
        return null;
    }

    @Override
    public List<Cart> selectAllService() {
        return null;
    }

    @Override
    public int updateByPrimaryKeyService(Cart record) {
        return 0;
    }

    @Override
    public List<Cart> selectAllCartByUserIdService(Integer id) {
        List<Cart> carts = cartMapper.selectAllCartByUserId(id);
        return carts;
    }
}
