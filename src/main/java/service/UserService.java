package service;

import entity.User;
import org.springframework.stereotype.Service;
import util.Msg;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/21  10:36
 */

public interface UserService {

    int deleteByPrimaryKeyService(Integer id);

    Msg saveUserService(User user);

    int insertService(User record);

    User selectByPrimaryKeyService(Integer id);

    Msg selectByUsernameService(String username);


    List<User> selectAllService();

    int updateByPrimaryKeyService(User record);

    public Msg selectByUsernameAndPwdService(String username, String password);


    int updateUserService(User user);

    int rechargeService(Double number, Integer uid);

    int selectIsOrNotCollect(Integer uid, Integer cid);

    int insertUser(User user);

    int updateUser(User user);

}
