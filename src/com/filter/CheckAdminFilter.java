package com.filter;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CheckAdminFilter",urlPatterns = {"/admin/*","/ProductServlet","/AdminServlet","/CategoryServlet"})
public class CheckAdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        if(requestURI.contains("Servlet")||(requestURI.contains(".jsp")&&!requestURI.contains("index.jsp"))){

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
