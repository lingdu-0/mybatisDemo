package com.wb.test;

import com.wb.entity.Menu;
import com.wb.entity.Orderdetail;
import com.wb.entity.Orders;
import com.wb.mapper.MenuMapper;
import com.wb.mapper.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Demo04 {

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
     * 多表联查
     * 根据用户id查所有订单和订单对应的订单详情
     * 一对多,一端模型嵌套集合(订单嵌套订单详情集合)
     */
    @Test
    public void test1() {
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findAll(1);
        for (Orders orders : list) {
            for (Orderdetail od : orders.getOrderdetails()) {
                System.out.println(od);
            }
        }
    }

    /**
     * 根据用户id查所有订单
     * 一对多,多端模型嵌套模型(订单嵌套用户模型)
     */
    @Test
    public void test2() {
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findAll2(1);
        for (Orders orders : list) {
            System.out.println(orders);
        }
    }

    /**
     * 查询一级子菜单
     */
    @Test
    public void test3() {
        MenuMapper mapper = session.getMapper(MenuMapper.class);
        List<Menu> byOneChilds = mapper.findByOneChilds(2);
        for (Menu menu : byOneChilds)
            System.out.println(menu);
    }

    /**
     * 查询一级父菜单
     */
    @Test
    public void test4() {
        MenuMapper mapper = session.getMapper(MenuMapper.class);
        System.out.println(mapper.findByOneParent(7));
    }
}
