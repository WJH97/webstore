package com.controller;



import com.dao.categoryDao.CategoryDaoImp;
import com.domain.CategoryBean;
import com.domain.Product;
import com.service.categoryService.CategoryService;
import com.service.categoryService.productService.ProductServiceImp;
import com.utils.PageHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;

@WebServlet(name = "AddProductServlet",urlPatterns = "/AddProductServlet")
public class ProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().println("");
        //String op = request.getParameter("op");
        ProductServiceImp productServiceImp = new ProductServiceImp();
        String op = request.getParameter("op");
        System.out.println("op="+op);
        if(op!=null&&!op.isEmpty()) {
            switch (op) {
                case "findProduct":
                    productServiceImp.findProduct(request, response);
                    break;
                case "addProduct":
                    productServiceImp.uploadFile(request, response);
                    String servletPath = request.getContextPath();

                    response.setHeader("refresh","1;url="+servletPath+"/admin/product/addProduct.jsp");
                   // productServiceImp.returnAddProduct(request,response);
               /* default:
                    productServiceImp.uploadFile(request, response);
                    break;*/
                case "searchProduct":
                    searchProduce(request,response);
                    break;
            }
        }else {
            response.getWriter().println("出错");
            String servletPath = request.getContextPath();
            response.setHeader("refresh","1;url="+servletPath+"/admin/product/addProduct.jsp");
        }
    }

    private void searchProduce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        String pname = request.getParameter("pname");
        String cid = request.getParameter("cid");
        String maxprice = request.getParameter("maxprice");
        String minprice = request.getParameter("minprice");
        String num = request.getParameter("num");
        ProductServiceImp productServiceImp = new ProductServiceImp();
        PageHelper<Product> productPageHelper = new PageHelper<>();
        List<Product> products = productServiceImp.searchProduct(pid, pname, cid, maxprice, minprice);
        long l = productServiceImp.searchProductNum(pid, pname, cid, maxprice, minprice);
        //List<Product> products = productPageHelper.getProductList();
        int i = Long.valueOf(l).intValue();
        productPageHelper.setTotalRecordsNum(i);
        //productServiceImp.showCurrentProductList(request,response,num);
        productPageHelper.setCurrentPageNum(Integer.parseInt(num));
        request.setAttribute("list",products);
        request.setAttribute("page",productPageHelper);

            request.getRequestDispatcher("/admin/product/productList.jsp").forward(request,response);

        /*  productPageHelper.setPrevPageNum(Integer.parseInt(num)-1);
        productPageHelper.setNextPageNum(Integer.parseInt(num)+1);
        if(productPageHelper.getTotalPageNum()<Integer.parseInt(num) || Integer.parseInt(num)<1){
            try {
                response.getWriter().println("页码错误,或者没有该类商品");
                response.setHeader("refresh","2,url="+request.getContextPath()+"/ProductServlet?op=searchProduct&num=1");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            request.setAttribute("products",productPageHelper.getProductList());
            request.setAttribute("page",productPageHelper);
            try {
                request.getRequestDispatcher("/admin/product/productList.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {

                request.setAttribute("products", new ArrayList<Product>() {
                });
                request.setAttribute("page",new PageHelper<Product>());
                request.getRequestDispatcher("/admin/product/productList.jsp").forward(request,response);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }*/
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
