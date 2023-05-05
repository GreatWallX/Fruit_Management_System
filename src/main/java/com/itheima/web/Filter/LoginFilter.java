package com.itheima.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String[] urls = {"/login.jsp","/imgs/","/css/","/loginServlet","/register.jsp","/registerServlet","/checkCodeServlet"};
        String Path = req.getRequestURL().toString();
        for (String url : urls) {
            if(Path.contains(url)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            req.setAttribute("login_msg","您尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(req,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
