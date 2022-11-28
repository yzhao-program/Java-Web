package com.souvenirstore.service;

import com.souvenirstore.bean.User;

public interface UserService {

    void registerUser(User user);

    User loginUser(User user);

    boolean existsUsername(String username);
}
