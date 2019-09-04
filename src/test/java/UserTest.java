import dao.UserMapper;
import entity.Course;
import entity.User;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import util.MyBatisUtil;

import java.util.List;

/**
 * Create by czq
 * time on 2019/8/21  10:48
 */
@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml"})
public class UserTest {

    @Autowired
    private SqlSessionFactory factory;
//    @Autowired
//    private ApplicationContext context;


//    @Before
//    public void init() {
//        context.getBean("dataSource");
//        factory = (SqlSessionFactory) context.getBean("sessionFactory");
//    }


    //登录测试
    @Test
    public void testGetUserByUsername() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User tom = mapper.selectByUsername("tom");
        System.out.println(tom);
    }


    //注册测试
    @Test
    public void testRegUser() {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User("蔡泽球", "980114zae", "男", "运动", "13767324034", "1219266990@qq.com");
        int i = mapper.saveUser(user);
        System.out.println(i);
    }

    @Test
    public void testUserName() {
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User tom456 = mapper.selectUsernameIsOrNotExit("tom456", 2);
        System.out.println(tom456);
    }


    @Test
    public void testGetUserByPrimaryKey() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User tom = mapper.selectByPrimaryKey(1);
        System.out.println(tom);
    }

    @Test
    public void testselectIsOrNotCollect() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int tom = mapper.selectIsOrNotCollect(2, 5);
        System.out.println(tom);
    }


    @Test
    public void testUpdateUser() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
//        user.setId(8);
//        user.setUsername("123");
//        user.setPassword("456");
//        user.setAge(20);
//        user.setAddress("家里蹲");
//        user.setPhone("19980114");
//        user.setEmail("8908098");
//        user.setMotto("嘿嘿嘿");
//        user.setAccount(9999);
        user.setId(8);
        user.setAvatar("test.jpg");
        int i = mapper.updateUser(user);
        System.out.println(i);
    }

    @Test
    public void testRegcharge() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int recharge = mapper.recharge(100d, 1);
    }

}