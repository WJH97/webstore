package com.dao.categoryDao;


import com.domain.CategoryBean;
import com.utils.DBUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImp implements CategoryDao{

    /**
     * 在数据库中插入新的category
     * @param category
     * @return
     * @throws SQLException
     */
    @Override
    public boolean addCategory(CategoryBean category) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());

        int update = queryRunner.update("insert into category values(null,?)", category.getCname());
        return update==1;
    }

    /**
     * 判断是否是重复元素
     * @param cname
     * @return
     * @throws SQLException
     */
    @Override
    public boolean isCategoryExisit(String cname) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        CategoryBean categoryBean = queryRunner.query("select * from category where cname=?", new BeanHandler<CategoryBean>(CategoryBean.class), cname);

        return categoryBean==null;
    }

    @Override
    public List<CategoryBean> showList() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        List<CategoryBean> beans = queryRunner.query("select * from category", new BeanListHandler<CategoryBean>(CategoryBean.class));


        return beans;
    }

    /**
     * 删除元素
     * @param cid
     * @return
     * @throws SQLException
     */
    @Override
    public boolean deleteCategory(int cid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int i = queryRunner.update("delete from category where cid=?",cid);
        return i==1;
    }

    /**
     * 更新元素
     * @param cname
     * @param cid
     * @return
     * @throws SQLException
     */
    @Override
    public boolean updateOneCategory(String cname,int cid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int i = queryRunner.update("update category set cname = ? where cid = ?", cname, cid);

        return i==1;
    }

    @Override
    public int getTotalRecordsNum()  {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int num = 0;
        try {
            num = ((Long) queryRunner.query("select count(*) as num from category", new ScalarHandler())).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    /**
     * 返回当前页数的查询列表
     * @param countPerPage 每页的查询数量
     * @param offsetPage  当前页数
     * @return
     */
    @Override
    public List<CategoryBean> getCurrentPageCategoryList(int countPerPage,int offsetPage) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int totalRecordsNum = this.getTotalRecordsNum();

            List<CategoryBean> categoryBeanList = queryRunner.query("select * from category limit ? offset ?"
                    , new BeanListHandler<CategoryBean>(CategoryBean.class), countPerPage, countPerPage * offsetPage);


        return  categoryBeanList;
    }

    @Override
    public CategoryBean getCategoryByCid(int cid) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        CategoryBean query = null;
        try {
             query = queryRunner.query("select * from category where cid=?", new BeanHandler<CategoryBean>(CategoryBean.class), cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return query;
    }


}
