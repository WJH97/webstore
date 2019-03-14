package com.service.categoryService;

import com.dao.userDao.UserDaoImp;

public class AjaxServices {
    UserDaoImp userDaoImp = new UserDaoImp();
    public boolean checkUsername(String username){
        return userDaoImp.checkUsername(username);
    }
}
