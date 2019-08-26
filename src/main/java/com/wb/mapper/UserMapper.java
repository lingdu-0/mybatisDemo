package com.wb.mapper;

import com.wb.entity.User;
import com.wb.entity.User2;

import java.util.List;

public interface UserMapper {
    /**
     * 保存用户
     *
     * @param user
     * @return 受影响的行数
     */
    int save(User user);

    int delete(int id);

    List<User> findAll();

    User findOne(int id);

    int update(User user);

    User2 findById(int id);

}
