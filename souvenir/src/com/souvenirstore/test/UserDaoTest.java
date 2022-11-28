package com.souvenirstore.test;

import com.souvenirstore.dao.UserDao;
import com.souvenirstore.dao.impl.UserDaoImpl;
import com.souvenirstore.bean.User;
import com.souvenirstore.util.JdbcUtils;
import org.junit.Test;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {

        if (userDao.queryUserByUsername("admin1234") == null ){
            System.out.println("This username is valid.");
        } else {
            System.out.println("This username already exists.");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if ( userDao.queryUserByUsernameAndPassword("admin","admin123456") == null) {
            System.out.println("The username or password is wrong.");
        } else {
            System.out.println("Login successfully.");
        }
    }

    @Test
    public void saveUser() {
        System.out.println( userDao.saveUser(new User(null,"hello_world", "123456", "hello_world@gmail.com")) );
        JdbcUtils.commitAndClose();
    }
}