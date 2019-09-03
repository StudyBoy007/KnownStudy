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
        int i = userMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public Msg saveUserService(User user) {
        int i = userMapper.saveUser(user);
        if (i == 1) {
            return Msg.result(100, "注册成功", null);
        } else {
            return Msg.result(400, "注册失败", null);
        }
    }

    @Override
    public int insertService(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKeyService(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public Msg selectByUsernameAndPwdService(String username, String password) {
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
    public int updateUserService(User user) {
        int i = userMapper.updateUser(user);
        return i;
    }

    @Override
    public int rechargeService(Double number, Integer uid) {
        int recharge = userMapper.recharge(number, uid);
        return recharge;
    }

    @Override
    public int selectIsOrNotCollect(Integer uid, Integer cid) {
        int i = userMapper.selectIsOrNotCollect(uid, cid);
        return i;
    }


    @Override
    public Msg selectByUsernameService(String username) {
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            return Msg.result(200, "用户名已存在", null);
        }
        return Msg.result(100, "用户名不存在", null);
    }


    @Override
    public List<User> selectAllService() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public int updateByPrimaryKeyService(User record) {
        return 0;
    }

    @Override
    public int insertUser(User user) {
        int i = userMapper.insertUser(user);
        return i;
    }

    @Override
    public int updateUser(User user) {
        int i = userMapper.updateUser(user);
        return i;
    }
}
