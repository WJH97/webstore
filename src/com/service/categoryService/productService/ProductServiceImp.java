package com.service.categoryService.productService;

import com.dao.categoryDao.CategoryDaoImp;
import com.dao.productDao.ProductDaoImp;
import com.domain.CategoryBean;
import com.domain.Product;
import com.service.categoryService.CategoryService;
import com.utils.PageHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.io.File;

public class ProductServiceImp implements ProductService {
    @Override
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        File repository = (File) request.getServletContext().getAttribute("javax.servlet.context.tempdir");
        diskFileItemFactory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setFileSizeMax(1024*1024);
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            Iterator<FileItem> iterator = fileItems.iterator();
            while(iterator.hasNext()){
                FileItem item = iterator.next();
                if(item.isFormField()){
                    processFormField(item,request);
                }else {
                    String realPath = request.getServletContext().getRealPath("");
                    processUploadedFile(item,request,realPath);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String pid = (String) request.getAttribute("pid");
        String pname = (String) request.getAttribute("pname");
        double estoreprice = Double.parseDouble((String) request.getAttribute("estoreprice"));
        System.out.println("estore="+estoreprice);
        double markprice = Double.parseDouble((String) request.getAttribute("markprice"));
        System.out.println("markprice="+markprice);
        int pnum = Integer.parseInt((String) request.getAttribute("pnum"));
        int cid = Integer.parseInt((String) request.getAttribute("cid"));
        String imgurl = (String) request.getAttribute("imgurl");
        String desc = (String) request.getAttribute("desc");
        ProductDaoImp productDaoImp = new ProductDaoImp();
        try {
            boolean b = productDaoImp.addProduct(pid, pname, estoreprice, markprice, pnum, cid, imgurl, desc);
            if(b){
                response.getWriter().println("增加成功");
                //String servletPath = request.getServletPath();
                //request.getRequestDispatcher("/admin/product/addProduct.jsp");
               // response.setHeader("refresh","1;url="+servletPath+"/admin/product/addProduct.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void findProduct(HttpServletRequest request, HttpServletResponse response) {

        List<CategoryBean> categoryBeans = CategoryService.showCategoryService();
       // request.setAttribute("categories",categoryBeans);
        request.getSession().setAttribute("categories",categoryBeans);

        //request.getRequestDispatcher("/admin/product/addProduct.jsp").forward(request,response);

        response.setHeader("refresh","1;url="+request.getContextPath()+"/admin/product/addProduct.jsp");


    }

    @Override
    public void showCurrentProductList(HttpServletRequest request, HttpServletResponse response,String num) {

        CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
        List<Product> list = null;
        try {
            list = this.getCurrentProductList(num);
            for (Product product : list) {
                product.setCategoryBean(categoryDaoImp.getCategoryByCid(product.getCid()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PageHelper pageProduct = this.findPageProduct(num);
        request.setAttribute("list",list);
        request.setAttribute("page",pageProduct);
        try {
            request.getRequestDispatcher("/admin/product/productList.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void updateFile(HttpServletRequest request, HttpServletResponse response) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        File repository = (File) request.getServletContext().getAttribute("javax.servlet.context.tempdir");
        diskFileItemFactory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setFileSizeMax(1024*1024);
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            Iterator<FileItem> iterator = fileItems.iterator();
            while(iterator.hasNext()){
                FileItem item = iterator.next();
                if(item.isFormField()){
                    processFormField(item,request);
                }else {
                    String realPath = request.getServletContext().getRealPath("");
                    processUploadedFile(item,request,realPath);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String pid = (String) request.getAttribute("pid");
        String pname = (String) request.getAttribute("pname");
        double estoreprice = Double.parseDouble((String) request.getAttribute("estoreprice"));
        System.out.println("estore="+estoreprice);
        double markprice = Double.parseDouble((String) request.getAttribute("markprice"));
        System.out.println("markprice="+markprice);
        int pnum = Integer.parseInt((String) request.getAttribute("pnum"));
        int cid = Integer.parseInt((String) request.getAttribute("cid"));
        String imgurl = (String) request.getAttribute("imgurl");
        String desc = (String) request.getAttribute("desc");
        ProductDaoImp productDaoImp = new ProductDaoImp();
        try {
           // Product product = productDaoImp.findProduct(pid);
            //System.out.println("product="+product);
            Product product = new Product(pid, pname, estoreprice, markprice, pnum, cid, imgurl, desc);

            int b = productDaoImp.updateProduct(product);
            if(b ==1){
                response.getWriter().println("修改成功");
               /* List<Product> list = this.getCurrentProductList("1");
                list.add(product);
                request.getSession().setAttribute("list",list);*/
                //String servletPath = request.getServletPath();
                //request.getRequestDispatcher("/admin/product/addProduct.jsp");
                 response.setHeader("refresh","1;url="+request.getContextPath()+"/ProductServlet?op=findAllProduct&num=1");
            }else {
                response.getWriter().println("加入失败，请重试");
                this.showCurrentProductList(request,response,"1");
                //response.setHeader("refresh","1;url="+request.getContextPath()+"/admin/product/updateProduct.jsp");
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> searchProduct(String pid, String pname, String cid, String maxprice, String minprice) {
        ProductDaoImp productDaoImp = new ProductDaoImp();
        List<Product> productList = null;
        try {
             productList = productDaoImp.searchProduct(pid, pname, cid, maxprice, minprice);
            //long i = productDaoImp.searchAllProduct(pid, pname, cid, maxprice, minprice);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public long searchProductNum(String pid, String pname, String cid, String maxprice, String minprice) {
        ProductDaoImp productDaoImp = new ProductDaoImp();

       long i = 0;
        try {
            i = productDaoImp.searchAllProduct(pid, pname, cid, maxprice, minprice);
            //long i = productDaoImp.searchAllProduct(pid, pname, cid, maxprice, minprice);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;

    }


    @Override
    public void deleteProduct(HttpServletRequest request, HttpServletResponse response,String pid) {

        ProductDaoImp productDaoImp = new ProductDaoImp();
        System.out.println("pid="+pid);
       // String pid = request.getParameter("pid");
        if(null!=pid && !"".equals(pid)){
            int i = productDaoImp.deleteProduct(pid);
            if(i == 1){
                try {
                    response.getWriter().println("删除成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                response.setHeader("refresh","1;url="+request.getContextPath()+"/ProductServlet?op=findAllProduct&num=1");
            }else  {
                try {
                    response.getWriter().println("删除失败");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response.setHeader("refresh","1,url="+request.getContextPath()+"/ProductServlet?op=findAllProduct&num=1");
            }
        }else {
            try {
                response.getWriter().println("错误商品编号");
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.setHeader("refresh","1,url="+request.getContextPath()+"/ProductServlet?op=findAllProduct&num=1");
        }

    }


    @Override
    public void findCategory(HttpServletRequest request, HttpServletResponse response,String pid) {
        ProductDaoImp productDaoImp = new ProductDaoImp();
        CategoryDaoImp categoryDaoImp = new CategoryDaoImp();
        if(pid!=null && !"".equals(pid)){
            Product product = productDaoImp.findProduct(pid);
            if(product!=null&&product.getPid().equals(pid)){
                try {
                    List<CategoryBean> categoryBeans = categoryDaoImp.showList();
                    request.setAttribute("product",product);
                    request.setAttribute("categories",categoryBeans);
                    request.getRequestDispatcher("/admin/product/updateProduct.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    response.getWriter().println("错误的商品id");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response.setHeader("refresh","1,url="+request.getContextPath()+"/ProductServlet?op=findAllProduct&num=1");
            }
        }
    }

    private List<Product> getCurrentProductList(String num) throws SQLException {
        List<Product> products = null;
        if(num!=null) {
            int n = Integer.parseInt(num);

            ProductDaoImp productDaoImp = new ProductDaoImp();
            int i = productDaoImp.getTotalProductNum();
            int countPerPage = 3;
            int j = (i + countPerPage - 1) / countPerPage;


            products = productDaoImp.getCurrentPageProductList(countPerPage, n - 1);

        }
        return products;

    }

    private PageHelper findPageProduct(String num){
        ProductDaoImp productDaoImp = new ProductDaoImp();
        PageHelper pageHelper = new PageHelper();
        int n = Integer.parseInt(num);
        pageHelper.setCurrentPageNum(Integer.parseInt(num));
        // 调用Dao查询当前总记录数
        int i = productDaoImp.getTotalProductNum();
        pageHelper.setTotalRecordsNum(i);
        //总的页码数
        int countPerPage = 3;
        int j   = (i+countPerPage-1)/countPerPage;
        pageHelper.setTotalPageNum(j);
        List<Product> products = null;
        try {
            products = productDaoImp.getCurrentPageProductList(countPerPage,n-1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pageHelper.setProductList(products);
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

    private void processUploadedFile(FileItem item, HttpServletRequest request, String realPath) throws UnsupportedEncodingException {
       // String realPath = getServletContext().getRealPath("");
        String name = item.getName(); // 文件名称
       /* byte[] bytes = name.getBytes("utf-8");
        String s = new String(bytes, "utf-8");*/
        request.setAttribute("imgurl",name);
        System.out.println("name="+name);
       // System.out.println(name);
        realPath = realPath + "/" + name;
        File uploadFile = new File(realPath);
        try {
            item.write(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void processFormField(FileItem item, HttpServletRequest request) {
        String name = item.getFieldName();
        String value = null;
        try {
            value=item.getString("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       request.setAttribute(name,value);
    }
}
