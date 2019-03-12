package com.test;


import com.dao.categoryDao.CategoryDaoImp;
import com.dao.productDao.ProductDaoImp;
import com.domain.CategoryBean;
import com.domain.Product;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class MyTest {
    CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
    ProductDaoImp productDaoImp =new ProductDaoImp();
    @Test
    public void test(){

        int totalRecordsNum = categoryDaoImp.getTotalRecordsNum();
        System.out.println(totalRecordsNum);
        Assert.assertEquals(6,totalRecordsNum);
    }

    @Test
    public void test2() throws SQLException {
        List<CategoryBean> currentPageCategoryList = categoryDaoImp.getCurrentPageCategoryList(3, 0);
        //System.out.println(currentPageCategoryList);
        for (CategoryBean categoryBean : currentPageCategoryList) {
            System.out.println(categoryBean);
        }
    }

    @Test
    public void test3(){
        Product product = new Product("1","NMSL",13,13,1,27,"asdf","嘻嘻嘻");

        int i = productDaoImp.updateProduct(product);
        Assert.assertEquals(1,1);
    }
}

