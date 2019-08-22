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
    public void testGetUserByPrimaryKey() {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User tom = mapper.selectByPrimaryKey(1);
        System.out.println(tom);
    }



}