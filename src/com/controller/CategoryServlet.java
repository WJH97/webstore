package com.controller;

import com.dao.categoryDao.CategoryDaoImp;
import com.domain.CategoryBean;
import com.service.categoryService.CategoryService;
import com.utils.PageHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // response.setContentType("text/html;charset=utf-8");
        String op = request.getParameter("op");

        if ("addCategory".equals(op)) {
            String cname = request.getParameter("cname");

            if (cname != null && !"".equals(cname)) {
                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setCname(cname);

                int i = CategoryService.addCategoryService(categoryBean);
                if (i == 1) {
                    response.getWriter().println("创建失败，已存在");
                    String contextPath = request.getContextPath();
                    response.setHeader("refresh", "1;url=" + contextPath + "/admin/category/addCategory.jsp");
                } else if (i == 2) {
                    response.getWriter().println("添加成功！");
                    String contextPath = request.getContextPath();
                    response.setHeader("refresh", "1;url=" + contextPath + "/admin/category/addCategory.jsp");
                } else if (i == -1) {
                    response.getWriter().println("添加发生异常，请重新添加");
                    String contextPath = request.getContextPath();
                    response.setHeader("refresh", "1;url=" + contextPath + "/admin/category/addCategory.jsp");
                } else {
                    response.getWriter().println("添加失败，请重新添加");
                    String contextPath = request.getContextPath();
                    response.setHeader("refresh", "1;url=" + contextPath + "/admin/category/addCategory.jsp");
                }
            }
        } /*else if("updateCategory".equals(op)){
            try {

                String realPath = request.getContextPath();
                response.setHeader("refresh","1;url="+realPath+"admin/category/categoryList.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/ else if ("findAllCategory".equals(op)) {
            try {

                //HttpSession session = request.getSession();
                //session.setAttribute("list", list);
                response.getWriter().println("正在跳转1");
                //String realPath = request.getContextPath();
                String num = request.getParameter("num");
                List<CategoryBean> list = CategoryService.getCurrentPageCategory(num);

                PageHelper pageCategory = CategoryService.findPageCategory(num);
                request.setAttribute("list",list);
                request.setAttribute("page",pageCategory);
                request.getRequestDispatcher("/admin/category/categoryList.jsp").forward(request,response);
               // response.setHeader("refresh", "1;url=" + realPath + "/admin/category/categoryList.jsp");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("deleteMulti".equals(op)) {

            try {
                String[] checkboxes = request.getParameterValues("pid");
                //int i = Integer.parseInt(cid);
                if (null != checkboxes) {
                    for (String checkbox : checkboxes) {
                        if (null != checkbox) {
                            int i = Integer.parseInt(checkbox);
                            CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
                            boolean b = categoryDaoImp.deleteCategory(i);
                            //System.out.println(b);
                            if (b) {
                                List<CategoryBean> list = CategoryService.showCategoryService();
                                HttpSession session = request.getSession();
                                session.setAttribute("list", list);
                                String servletContext = request.getContextPath();
                                response.setHeader("refresh", "1;url=" + servletContext + "/admin/category/categoryList.jsp");
                            }
                        } else {
                            response.getWriter().println("删除失败,请重新删除");
                            String servletContext = request.getContextPath();
                            response.setHeader("refresh", "1;url=" + servletContext + "/admin/category/categoryList.jsp");
                        }
                    }
                } else {
                    String cid = request.getParameter("cid");
                    int i = Integer.parseInt(cid);
                    CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
                    boolean b = categoryDaoImp.deleteCategory(i);
                    //System.out.println(b);
                    if (b) {
                        List<CategoryBean> list = CategoryService.showCategoryService();
                        HttpSession session = request.getSession();
                        session.setAttribute("list", list);
                        String servletContext = request.getContextPath();
                        response.setHeader("refresh", "1;url=" + servletContext + "/admin/category/categoryList.jsp");
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("updateCategory".equals(op)) {
            String cname = request.getParameter("cname");
            String cid = request.getParameter("cid");



                if (null != cid && null != cname) {
                    int i = Integer.parseInt(cid);
                    int b = CategoryService.updateService(cname, i);
                    if (b == 1) {
                        List<CategoryBean> list = CategoryService.showCategoryService();
                        HttpSession session = request.getSession();
                        session.setAttribute("list", list);
                        String servletContext = request.getContextPath();
                        response.setHeader("refresh", "1;url=" + servletContext + "/admin/category/categoryList.jsp");
                    } else if (b == -1) {
                        response.getWriter().println("更新失败");
                        String servletContext = request.getContextPath();
                        response.setHeader("refresh", "1;url=" + servletContext + "/admin/category/updateCategory.jsp");
                    }
                } else {
                    response.getWriter().println("更新发生异常");
                    String servletContext = request.getContextPath();
                    response.setHeader("refresh", "1;url=" + servletContext + "/admin/category/updateCategory.jsp");
                }

        } /*else if ("findAllCategory".equals(op)) {
            String num = request.getParameter("num");
            PageHelper pageCategory = CategoryService.findPageCategory(num);
            request.setAttribute("page",pageCategory);
            request.getRequestDispatcher("/admin/category/categoryList.jsp").forward(request,response);

        }*/
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
