package com.dao.categoryDao;

import com.domain.CategoryBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public interface CategoryDao {
    boolean addCategory(CategoryBean category) throws SQLException;
    boolean isCategoryExisit(String cname) throws SQLException;
    List<CategoryBean> showList() throws SQLException;
    boolean deleteCategory(int cid) throws SQLException;
    boolean updateOneCategory(String cname,int cid) throws SQLException;

    int getTotalRecordsNum() ;

    List<CategoryBean> getCurrentPageCategoryList(int countPerPage,int offset) throws SQLException;

    CategoryBean getCategoryByCid(int cid);
}
