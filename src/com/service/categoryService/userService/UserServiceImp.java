package com.service.categoryService.userService;

import com.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServiceImp implements UserService {
    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public User userLogin(String username, String password) {
        return null;
    }

    @Override
    public void userRegister(HttpServletRequest request, HttpServletResponse response) {

    }
}
