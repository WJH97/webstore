package com.service.categoryService.productService;

import com.domain.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ProductService {
    void uploadFile(HttpServletRequest request, HttpServletResponse response);

    void findProduct(HttpServletRequest request, HttpServletResponse response);

    void showCurrentProductList(HttpServletRequest request, HttpServletResponse response,String num);

    //void updateProduct(HttpServletRequest request, HttpServletResponse response);

    void deleteProduct(HttpServletRequest request, HttpServletResponse response, String pid);

    void findCategory(HttpServletRequest request, HttpServletResponse response,String pid);

    //void updateProduct(HttpServletRequest request, HttpServletResponse response, String pid);

    void updateFile(HttpServletRequest request, HttpServletResponse response);

    List<Product> searchProduct(String pid, String pname, String cid, String maxprice, String minprice);
    long searchProductNum(String pid, String pname, String cid, String maxprice, String minprice);
}
