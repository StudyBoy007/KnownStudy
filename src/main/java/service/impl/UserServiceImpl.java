package service.impl;

import dao.UserMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;
import util.Msg;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/21  10:38
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public int deleteByPrimaryKeyService(Integer id) {
        return 0;
    }

    @Override
    public int saveUserService(User user) {
        int i = userMapper.saveUser(user);
        return i;
    }

    @Override
    public int insertService(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKeyService(Integer id) {
        return null;
    }

    @Override
    public Msg selectByUsernameService(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return Msg.result(200, "用户名或密码错误", null);
        } else {
            if (user.getPassword().equals(password)) {
                return Msg.result(100, "登陆成功", user);
            } else {
                return Msg.result(200, "用户名或密码错误", null);
            }
        }
    }

    @Override
    public List<User> selectAllService() {
        return null;
    }

    @Override
    public int updateByPrimaryKeyService(User record) {
        return 0;
    }
}
