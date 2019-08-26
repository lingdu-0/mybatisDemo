package com.wb.test;

import com.wb.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Demo01 {

    private SqlSession session;

    @Before
    public void before() throws IOException {
        System.out.println("before获取session");
        //1.读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.通过SqlSessionFactoryBuilder创建SqlSessionFactory会话工厂
        SqlSessionFactory sessionFactoryBuilder = new SqlSessionFactoryBuilder().build(is);
        //3.通过SqlSessionFactory创建SqlSession
        session = sessionFactoryBuilder.openSession();
    }

    @After
    public void after() {
        System.out.println("after关闭session");
        //提交事务
        session.commit();
        //5.关闭SqlSession
        session.close();
    }

    /**
     * 查询一条记录和多条记录
     */
    @Test
    public void test1() {

        //4.调用SqlSession操作数据库的方法
        User user = session.selectOne("findUserById", 10);
        System.out.println(user);
        //模糊查询
        List<User> list = session.selectList("findUserByName", "张");
        for (User u : list)
            System.out.println(u);

    }

    /**
     * 插入数据
     */
    @Test
    public void test2() {
        User user = new User("卢本伟", "1", new Date(), "赛博坦");
        session.insert("addUser", user);
    }

    /**
     * 删除用户
     */
    @Test
    public void test3() {
        int row = session.delete("deleteUser", 27);
        System.out.println("受影响的行数:" + row);
    }

    /**
     * 更新用户
     */
    @Test
    public void test4() {
        User user = new User();
        user.setId(28);
        user.setUsername("蔡徐坤");
        user.setAddress("火星");
        int row = session.update("updateUser", user);
        System.out.println("受影响的行数:" + row);
    }

    /**
     * 插入数据并获取主键ID
     */
    @Test
    public void test5() {
        User user = new User("卢本伟", "1", new Date(), "赛博坦");
        session.insert("addUser2", user);
        System.out.println("插入之后数据库返回的id：" + user.getId());
    }
}
