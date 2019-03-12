package com.dao.productDao;

import com.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    boolean addProduct(String pid, String pname, double estoreprice, double markprice, int pnum, int cid, String imgurl, String desc) throws SQLException;

    int getTotalProductNum();

    List<Product> getCurrentPageProductList(int countPerPage, int i) throws SQLException;

    int  deleteProduct(String pid);
    int  updateProduct(Product product);

    List<Product> searchProduct(String pid, String pname, String cid, String maxprice, String minprice) throws SQLException;

    long searchAllProduct(String pid, String pname, String cid, String maxprice, String minprice) throws SQLException;
    //void searchProduct();
}
