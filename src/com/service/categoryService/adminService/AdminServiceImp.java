package com.service.categoryService.adminService;

import com.dao.adminDao.AdminDaoImp;
import com.domain.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminServiceImp implements AdminService {
    AdminDaoImp adminDaoImp = new AdminDaoImp();
    @Override
    public void addAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        if(password!=null && password.equals(password1) && username!=null){
            int i = adminDaoImp.addAdmin(username, password);
            if(i == 1){
                response.getWriter().println("添加成功");
                //response.sendRedirect(request.getContextPath()+"/admin/admin/addAdmin.jsp");
                response.setHeader("refresh","1;url="+request.getContextPath()+"/admin/admin/addAdmin.jsp");
            }else {
                response.getWriter().println("添加失败");
                response.sendRedirect(request.getContextPath()+"/admin/admin/addAdmin.jsp");
            }
        }else {
            response.getWriter().println("出错");
            response.setHeader("refresh","1;url="+request.getContextPath()+"/admin/admin/addAdmin.jsp");
        }

    }

    @Override
    public void deleteAdmin() {

    }

    @Override
    public void updateAdmin() {

    }

    @Override
    public void searchAdmin() {

    }

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin admin = new Admin();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username!=null && password!=null ){
            int i = adminDaoImp.login(username, password);
            System.out.println("i=" + i);
            if(i == 1){
                admin.setPassword(password);
                admin.setUsername(username);
                //request.getRequestDispatcher("/admin/main.jsp");
                //request.setAttribute("admin",admin);
                request.getSession().setAttribute("admin",admin);
                response.setHeader("refresh","1;url="+request.getContextPath()+"/admin/main.jsp");
            }else {
                    response.getWriter().println("用户名或密码错误");
                    response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
            }
        }else {
            response.getWriter().println("账号名或密码为空");
            response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
        }
    }

    @Override
    public void findAllAdmin(HttpServletRequest request, HttpServletResponse response, String num) throws IOException {
        AdminDaoImp adminDaoImp = new AdminDaoImp();
        List<Admin> adminList = adminDaoImp.findAllAdmin();
        if(adminList!=null){
            request.setAttribute("records",adminList);
            //request.getRequestDispatcher("/admin/admin/adminList.jsp");
            //request.getSession().setAttribute("records",adminList);
            response.setHeader("refresh","1;url="+request.getContextPath()+"/admin/admin/adminList.jsp");
        }else {
            response.getWriter().println("管理员表为空");
            response.setHeader("refresh","1;url="+request.getContextPath()+"/admin/admin/addAdmin.jsp");
        }
    }
}
