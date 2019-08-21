package dao;

import entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    //用户注册
    int saveUser(User user);

    User selectByPrimaryKey(Integer id);


    //用户登录
    User selectByUsername(String username);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}