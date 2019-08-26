package com.wb.test;

import com.wb.entity.User;
import com.wb.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Demo03 {

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
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findOne(1);
        System.out.println(user);
    }

    /**
     * 模型嵌套模型
     */
    @Test
    public void test2() {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        System.out.println(userMapper.findById(1));
    }

}
