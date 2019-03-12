package com.controller;

import com.service.categoryService.adminService.AdminServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet",urlPatterns = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        AdminServiceImp adminServiceImp = new AdminServiceImp();
        String num = request.getParameter("num");
        if(op!=null && !"".equals(op)){
            switch (op){
                case "login":
                    adminServiceImp.login(request,response);
                    break;
                case "addAdmin":
                    adminServiceImp.addAdmin(request,response);
                    break;
                case "findAllAdmin":
                    adminServiceImp.findAllAdmin(request,response,num);
                    break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
