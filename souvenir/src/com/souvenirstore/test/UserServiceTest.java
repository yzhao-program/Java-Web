package com.souvenirstore.test;

import com.souvenirstore.bean.User;
import com.souvenirstore.service.UserService;
import com.souvenirstore.service.impl.UserServiceImpl;
import com.souvenirstore.util.JdbcUtils;
import org.junit.Test;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "abc123456", "123456", "abc123456@gmail.com"));
        userService.registerUser(new User(null, "cba654321", "654321", "cba654321@gmail.com"));
        JdbcUtils.commitAndClose();
    }

    @Test
    public void loginUser() {
        System.out.println( userService.loginUser(new User(null, "hello_world", "123456", null)) );
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("hello_world")) {
            System.out.println("This username already exists.");
        } else {
            System.out.println("This username is valid.");
        }
    }
}