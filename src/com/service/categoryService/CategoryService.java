package com.service.categoryService;

import com.dao.categoryDao.CategoryDaoImp;
import com.domain.CategoryBean;
import com.utils.PageHelper;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {

    public static int addCategoryService(CategoryBean categoryBean){
        CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
        int ret = 0 ;
        try {
            boolean exisit = categoryDaoImp.isCategoryExisit(categoryBean.getCname());
            if(exisit==false){
                ret = 1;
                return ret;
            }else {
                boolean b = categoryDaoImp.addCategory(categoryBean);
                if(b){
                    ret = 2;
                }else {
                    ret = -2;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ret = -1;
        }
        return ret;

    }
    public static List<CategoryBean>  showCategoryService()  {
        CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
        List<CategoryBean> list = null;
        try{
        list = categoryDaoImp.showList();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    public static int updateService(String cname,int cid)  {

        CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
        int i = 0;
        boolean ret  = false;
        try {
            ret = categoryDaoImp.updateOneCategory(cname,cid);
            if(ret){
                i= 1;
            }else {
                i = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            i = 0;
        }

       return i;
    }

    public static PageHelper findPageCategory(String num){
        CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
        PageHelper pageHelper = new PageHelper();
        int n = Integer.parseInt(num);
        pageHelper.setCurrentPageNum(Integer.parseInt(num));
        // 调用Dao查询当前总记录数
        int i = categoryDaoImp.getTotalRecordsNum();
        pageHelper.setTotalRecordsNum(i);
        //总的页码数
        int countPerPage = 3;
        int j   = (i+countPerPage-1)/countPerPage;
        pageHelper.setTotalPageNum(j);
        List<CategoryBean> categoryBeans = null;
        try {
            categoryBeans = categoryDaoImp.getCurrentPageCategoryList(countPerPage,n-1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pageHelper.setCategoryList(categoryBeans);
        //下一页
        if(n>=2) {
            pageHelper.setPrevPageNum(n - 1);
        }else {
            pageHelper.setPrevPageNum(n);
        }

        //上一页
        if(n<j) {
            pageHelper.setNextPageNum(n + 1);
        }else {
            pageHelper.setNextPageNum(n);
        }
        return  pageHelper;
    }


    public static List<CategoryBean> getCurrentPageCategory(String num) throws SQLException {
        int n = Integer.parseInt(num);
        CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
        int i = categoryDaoImp.getTotalRecordsNum();
        int countPerPage = 3;
        int j   = (i+countPerPage-1)/countPerPage;
        List<CategoryBean> categoryBeans = null;

        categoryBeans = categoryDaoImp.getCurrentPageCategoryList(countPerPage,n-1);


        return categoryBeans;
    }
}
