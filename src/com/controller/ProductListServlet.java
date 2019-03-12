package com.controller;

import com.dao.categoryDao.CategoryDaoImp;
import com.domain.CategoryBean;
import com.service.categoryService.CategoryService;
import com.service.categoryService.productService.ProductService;
import com.service.categoryService.productService.ProductServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductListServlet",urlPatterns = "/ProductServlet")
public class ProductListServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductServiceImp();
        String op = request.getParameter("op");

        System.out.println("op="+op);
        String num = request.getParameter("num");
        String pid = request.getParameter("pid");
        if(op==null){
            response.getWriter().println("****");
        }

       /* List<CategoryBean> categoryBeans = CategoryService.showCategoryService();
        request.getServletContext().setAttribute("categories",categoryBeans);
*/
        if(op!=null&&!op.isEmpty()){
            switch (op){
                case "findAllProduct":
                    productService.showCurrentProductList(request,response,num);
                    break;
                case "deleteOne":
                    //String pid = request.getParameter("pid");
                    productService.deleteProduct(request,response,pid);
                    break;
                case "findProduct":
                    productService.findCategory(request,response,pid);
                    //String pid = request.getParameter("pid");
                    break;
                case "updateProduct":
                    request.setAttribute("pid",pid);

                    //productService.updateProduct(request,response,pid);
                    productService.updateFile(request,response);
                    //response.setHeader("refresh","1;url="+request.getContextPath()+"/admin/product/productList.jsp");
                case "preSearchProduct":

                    CategoryBean noCidCategoryBean = new CategoryBean();
                    noCidCategoryBean.setCid(-1);
                    noCidCategoryBean.setCname("无");
                    CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
                    try {
                        categoryDaoImp.addCategory(noCidCategoryBean);
                        List<CategoryBean> categoryBeans = categoryDaoImp.showList();
                        //request.setAttribute("categories",categoryBeans);
                        request.getSession().setAttribute("categories",categoryBeans);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //request.getRequestDispatcher("/admin/product/searchProduct.jsp");
                    response.sendRedirect(request.getContextPath()+"/admin/product/searchProduct.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
