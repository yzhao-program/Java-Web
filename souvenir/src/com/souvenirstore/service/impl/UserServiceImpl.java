package com.souvenirstore.service.impl;

import com.souvenirstore.dao.UserDao;
import com.souvenirstore.dao.impl.UserDaoImpl;
import com.souvenirstore.bean.User;
import com.souvenirstore.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User loginUser(User user) {

        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {

        if (userDao.queryUserByUsername(username) == null) {
           return false;
        }

        return true;
    }
}
