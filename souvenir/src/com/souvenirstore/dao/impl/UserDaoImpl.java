package com.souvenirstore.dao.impl;

import com.souvenirstore.dao.UserDao;
import com.souvenirstore.bean.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from user_table where username = ?";
        return queryForOne(sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from user_table where username = ? and password = ?";
        return queryForOne(sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user_table(`username`,`password`,`email`) values(?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(),user.getEmail());
    }
}
