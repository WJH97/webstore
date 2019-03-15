package com.controller;

import com.domain.Error;
import com.domain.Msg;
import com.service.categoryService.userService.UserService;
import com.service.categoryService.userService.UserServiceImp;
import com.utils.CheckParaUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        UserServiceImp userService = new UserServiceImp();
        String contextPath = request.getContextPath();
        if(op!=null||op.isEmpty()||"null".equals(op)){
            response.getWriter().println("Invalid Operation");
            response.setHeader("refresh","1,url="+contextPath+"/index.jsp");
            return;
        }
        switch (op){
            case "regist" :
                userRegister(request,response);
                break;
                case "login":
                    userLogin(request,response);
                    break;
            case "logout":
                userLogout(request,response);
                break;
            default:
                    response.getWriter().println("Invalid Operation");
                    response.setHeader("refresh","1,url="+contextPath+"/index.jsp");
                    break;
        }

    }

    private void userLogout(HttpServletRequest request, HttpServletResponse response) {
    }

    private void userLogin(HttpServletRequest request, HttpServletResponse response) {

    }

    private void userRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        String username = request.getParameter("username");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        PrintWriter writer = response.getWriter();
        Msg msg = new Msg();
        msg.setUsername(username);
        msg.setNickname(nickname);
        msg.setPassword(password);
        msg.setEmail(email);
        msg.setError(new Error());
        boolean canRegister = true;
        if(CheckParaUtil.checkString(username)){
            if(!username.matches("\\w{8,}")){
                msg.getError().setUsername("用户名不能少于八位");
                canRegister= false;
            }
        }else {
            msg.getError().setUsername("用户名不能为空");
            canRegister= false;
        }

        if (!CheckParaUtil.checkString(nickname)){
//                writer.println("nickname为空");
//                response.setHeader("refresh","1,url="+contextPath+"/user/register.jsp");
//                return;
            canRegister = false;
            msg.getError().setNickname("昵称不能为空");
        }
        if (!CheckParaUtil.checkString(password)){
            msg.getError().setPassword("密码不能为空");
            canRegister = false;
        }else if (!password.matches("\\w{8,}")){
            msg.getError().setPassword("密码不能少于8位");
            canRegister = false;
        }

        if(!CheckParaUtil.checkString(email)){
            msg.getError().setEmail("邮箱不能为空");
            canRegister=false;
        }else if (!email.matches("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")){
            msg.getError().setEmail("邮箱格式错误");
            canRegister=false;
        }
        if(!canRegister){
            request.setAttribute("msg",msg);
            request.getRequestDispatcher("/user/regist.jsp");
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
