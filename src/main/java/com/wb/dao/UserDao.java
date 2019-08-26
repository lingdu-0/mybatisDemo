package com.wb.dao;

import com.wb.entity.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    void delete(int id);

    List<User> findAll();

    User findOne(int id);

    void update(User user);
}
