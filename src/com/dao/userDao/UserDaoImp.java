package com.dao.userDao;

import com.domain.User;

public class UserDaoImp implements UserDao{
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    public boolean checkUsername(String username) {
        return false;
    }
}
