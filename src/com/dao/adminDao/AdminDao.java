package com.dao.adminDao;

import com.domain.Admin;

import java.util.List;

public interface AdminDao {
        int login(String username, String password);
        int addAdmin(String username,String password);
        int deleteAdmin(int cid);

        List<Admin> findAllAdmin();
}
