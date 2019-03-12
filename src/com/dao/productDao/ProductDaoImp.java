package com.dao.productDao;

import com.domain.CategoryBean;
import com.domain.Product;
import com.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao {

    @Override
    public boolean addProduct(String pid, String pname, double estoreprice, double markprice, int pnum, int cid, String imgurl, String desc) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int i = queryRunner.update("insert into product values(?,?,?,?,?,?,?,?)"
                , pid, pname, estoreprice, markprice, pnum, cid, imgurl, desc);
        return i ==1;
    }

    @Override
    public int getTotalProductNum() {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int num = 0;
        try {
            num = ((Long) queryRunner.query("select count(pid) as num from product", new ScalarHandler())).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<Product> getCurrentPageProductList(int countPerPage, int offsetPage) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        List<Product> productList = queryRunner.query("select * from product limit ? offset ?"
                , new BeanListHandler<Product>(Product.class), countPerPage, countPerPage * offsetPage);
        return productList;
    }

    @Override
    public int  deleteProduct(String pid) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int i = 0;
        try {
             i = queryRunner.update("delete from product where pid=?", pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int  updateProduct(Product product) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        int i = 0;
        try {
            i = queryRunner.update("update product set pname = ?, estoreprice = ?, markprice = ?, pnum = ?, cid = ?, imgurl = ?, `desc` =? where pid=?"
                    , product.getPname(), product.getEstoreprice(), product.getMarkprice(), product.getPnum(), product.getCid(), product.getImgurl(), product.getDesc(), product.getPid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;

    }

    @Override
    public List<Product> searchProduct(String pid, String pname, String cid, String maxprice, String minprice) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select * from product where 1=1";
        ArrayList arrayList = new ArrayList();
        if(pid!=null && !pid.isEmpty()){
            sql = sql + "and pid=?" ;
            arrayList.add(pid);
        }
        if (cid!=null&&!cid.isEmpty()){

            sql =sql +" and  cid=?" ;
            arrayList.add(cid);


        }
        if (pname!=null&&!pname.isEmpty()){

            sql =sql +" and pname like ?" ;
            arrayList.add("%"+pname+"%");

        }

        if (minprice!=null&&!minprice.isEmpty()){

            sql =sql +" and estoreprice >= ?" ;
            arrayList.add(minprice);

        }
        if (maxprice!=null&&!maxprice.isEmpty()){

            sql =sql +" and estoreprice <= ?" ;
            arrayList.add(maxprice);

        }

        Object[] params = arrayList.toArray();
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), params);
        return productList;

    }

    @Override
    public long searchAllProduct(String pid, String pname, String cid, String maxprice, String minprice) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select count(*) from product where 1=1";
        ArrayList arrayList = new ArrayList();
        if(pid!=null && !pid.isEmpty()){
            sql = sql + "and pid=?" ;
            arrayList.add(pid);
        }
        if (cid!=null&&!cid.isEmpty()){

            sql =sql +" and  cid=?" ;
            arrayList.add(cid);


        }
        if (pname!=null&&!pname.isEmpty()){

            sql =sql +" and pname like ?" ;
            arrayList.add("%"+pname+"%");

        }

        if (minprice!=null&&!minprice.isEmpty()){

            sql =sql +" and estoreprice >= ?" ;
            arrayList.add(minprice);

        }
        if (maxprice!=null&&!maxprice.isEmpty()){

            sql =sql +" and estoreprice <= ?" ;
            arrayList.add(maxprice);

        }

        Object[] params = arrayList.toArray();
        Long query = (Long) queryRunner.query(sql, new ScalarHandler(), params);

        return query;

    }


    public Product findProduct(String pid) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        Product product = null;
        try {
            product = queryRunner.query("select * from product where pid=?", new BeanHandler<Product>(Product.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  product;
    }


}
