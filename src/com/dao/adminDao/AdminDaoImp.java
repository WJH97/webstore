package com.dao.adminDao;

import com.domain.Admin;
import com.utils.DBUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDaoImp implements  AdminDao{
    @Override
    public int login(String username, String password) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int i = 0;
        try {
              i = ((Long) queryRunner.query("select count(*) from `admin` where username=? and password=?", new ScalarHandler(), username, password)).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return i;
    }

    @Override
    public int addAdmin( String username, String password) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int i = 0;
        try {
            i = queryRunner.update("insert into `admin`(username,password) values(?,?)",  username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteAdmin(int cid) {
        return 0;
    }

    @Override
    public List<Admin> findAllAdmin() {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        List<Admin> query = null;
        try {
            query = queryRunner.query("select * from `admin`", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
