package com.dao.userDao;

import com.domain.User;
import com.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

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
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int i = 0;
        try {
             i = ((Long) queryRunner.query("select count(*) from user where username=?", new ScalarHandler(), username)).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i==0;
    }
}
