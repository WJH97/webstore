package com.service.categoryService.adminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AdminService {
    void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void deleteAdmin();
    void updateAdmin();
    void searchAdmin();

    void login(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void findAllAdmin(HttpServletRequest request, HttpServletResponse response, String num) throws IOException;
}
