package com.souvenirstore.dao;

import com.souvenirstore.bean.User;

public interface UserDao {

    User queryUserByUsername(String username);

    User queryUserByUsernameAndPassword(String username, String password);

    int saveUser(User user);
}
