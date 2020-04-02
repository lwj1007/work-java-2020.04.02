package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.Arguments;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import com.lagou.utill.Utills;
import org.junit.Test;

import java.io.InputStream;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam, Arguments.SELECT);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("小四");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

     /*   List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }*/
        User u = userDao.findByCondition(user);
        System.out.println(u);
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setId(4);
        user.setUsername("张三");
        SqlSession sqlSession=Utills.getSqlSession(Arguments.ADD);
        IUserDao userDao =sqlSession.getMapper(IUserDao.class);
        userDao.addUser(user);

    }

    @Test
    public void testDelUser() throws Exception {
        User user = new User();
        user.setId(4);
        user.setUsername("张三");
        SqlSession sqlSession=Utills.getSqlSession(Arguments.DELETE);
        IUserDao userDao =sqlSession.getMapper(IUserDao.class);
        userDao.delUser(user);

    }
    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(2);
        user.setUsername("张三");
        SqlSession sqlSession=Utills.getSqlSession(Arguments.UPDATE);
        IUserDao userDao =sqlSession.getMapper(IUserDao.class);
        userDao.updateUser(user);

    }
}

