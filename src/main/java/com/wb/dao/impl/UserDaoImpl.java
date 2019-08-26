package com.wb.dao.impl;

import com.wb.dao.UserDao;
import com.wb.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory ssf;

    public UserDaoImpl(SqlSessionFactory ssf) {
        this.ssf = ssf;
    }

    public UserDaoImpl() {
    }

    @Override
    public void save(User user) {
        //获取session
        SqlSession session = ssf.openSession();
        session.insert("addUser", user);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        //获取session
        SqlSession session = ssf.openSession();
        int row = session.delete("deleteUser", 27);
    }

    @Override
    public List<User> findAll() {
        //获取session
        SqlSession session = ssf.openSession();
        return null;
    }

    @Override
    public User findOne(int id) {
        //获取session
        SqlSession session = ssf.openSession();
        return session.selectOne("findUserById", 10);
    }

    @Override
    public void update(User user) {
        //获取session
        SqlSession session = ssf.openSession();
    }
}
