package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletLogout",urlPatterns = "/logout.do",initParams = {@WebInitParam(name="LOGIN_VIEW",value = "index.html")})
public class ServletLogout extends HttpServlet {
    private  String LOGIN_VIEW;

    @Override
    public void init() throws ServletException {
        LOGIN_VIEW=getServletConfig().getInitParameter("LOGIN_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("login")!=null){
            request.getSession().invalidate();
        }
        response.sendRedirect(LOGIN_VIEW);
    }
}
