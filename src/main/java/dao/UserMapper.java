package dao;

import entity.Course;
import entity.User;
import org.apache.ibatis.annotations.Param;

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


    //更新用户
    int updateUser(User user);


    int recharge(@Param("number") Double number, @Param("uid") Integer uid);

    int payAccount(@Param("number") Double number, @Param("uid") Integer uid);

    int selectIsOrNotCollect(@Param("uid") Integer uid, @Param("cid") Integer cid);

    int selectCollectConnectionInUserAndCourse(@Param("uid") Integer uid, @Param("cid") Integer cid);

    int changeCollectCourseState(@Param("uid") Integer uid, @Param("cid") Integer cid, @Param("state") boolean state);

    int createConnectionInUserAndCourseCollect(@Param("uid") Integer uid, @Param("cid") Integer cid);

    int deleteConnectionJuify(@Param("uid") Integer uid, @Param("cid") Integer cid);

    int deleteConnection(@Param("uid") Integer uid, @Param("cid") Integer cid);

}