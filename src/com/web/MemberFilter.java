package com.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MemberFilter",urlPatterns = {"/delete.do","/logout.do","/message.do","/member.view"},
        initParams ={@WebInitParam(name="LOGIN_VIEW",value = "index.html")})
public class MemberFilter implements Filter {
    private String LOGIN_VIEW;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        if(request.getSession().getAttribute("login")!=null){//具备login属性，调用doilter
            chain.doFilter(req,resp);
        }
        else{
            HttpServletResponse response=(HttpServletResponse)resp;
            response.sendRedirect(LOGIN_VIEW);//重定向到登陆页面
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.LOGIN_VIEW=config.getInitParameter("LOGIN_VIEW");
    }

}
