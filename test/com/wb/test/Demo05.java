package com.wb.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wb.entity.Items;
import com.wb.mapper.ItemsMapper;
import com.wb.vo.QueryItems;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Demo05 {

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
     * 动态SQL模糊查询
     */
    @Test
    public void test1() {
        ItemsMapper mapper = session.getMapper(ItemsMapper.class);
        Items items = new Items();
        //items.setName("台");
        items.setDetail("");
        QueryItems query = new QueryItems();
        query.setItems(items);
        PageHelper.startPage(1,2);
        List<Items> list = mapper.search(query);
        for (Items i : list)
            System.out.println(i);
        PageInfo page = new PageInfo(list);
        System.out.println("总条数:"+page.getTotal());
        System.out.println("总页数:"+page.getPages());
        System.out.println("当前页:"+page.getPageNum());
        System.out.println("每页大小:"+page.getPageSize());
    }

    /**
     * 动态SQL多变量查询
     */
    @Test
    public void test2() {
        ItemsMapper mapper = session.getMapper(ItemsMapper.class);
        QueryItems query = new QueryItems();
        query.setIds(Arrays.asList(new Integer[]{1, 2, 3}));
        List<Items> list = mapper.findByIds(query);
        for (Items items : list)
            System.out.println(items);
    }

}
