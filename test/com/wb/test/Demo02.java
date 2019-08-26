package com.wb.test;

import com.wb.dao.UserDao;
import com.wb.dao.impl.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取回话工厂生成session
 */
public class Demo02 {
    SqlSessionFactory sessionFactoryBuilder;

    @Before
    public void before() throws IOException {
        System.out.println("before获取session");
        //1.读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.通过SqlSessionFactoryBuilder创建SqlSessionFactory会话工厂
        sessionFactoryBuilder = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void test01() {
        UserDao userDao = new UserDaoImpl(sessionFactoryBuilder);
        System.out.println(userDao.findOne(1));
    }

}
