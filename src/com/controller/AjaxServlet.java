package com.controller;

import com.service.categoryService.AjaxServices;
import com.utils.CheckParaUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxServlet",urlPatterns = "/AjaxServlet")
public class AjaxServlet extends HttpServlet {
    AjaxServices ajaxServices = new AjaxServices();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if(!CheckParaUtil.checkString(op)){
            return;
        }

        switch (op){
            case "checkUsername":
                checkUsername(request,response);
                return;
                default:
                    return;
        }

    }

    private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        response.getWriter().println(ajaxServices.checkUsername(username));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
