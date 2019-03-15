package com.service.categoryService.userService;

import com.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    public boolean addUser(User user);
    User userLogin(String username, String password);

    void userRegister(HttpServletRequest request, HttpServletResponse response);
}
