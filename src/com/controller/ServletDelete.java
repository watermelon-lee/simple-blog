package com.controller;

import com.model.Blah;
import com.model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletDelete",urlPatterns = "/delete.do",initParams = {
        @WebInitParam(name="SUCCESS_VIEW",value = "member.jsp")})
public class ServletDelete extends HttpServlet {
    private  String SUCCESS_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW=getServletConfig().getInitParameter("SUCCESS_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* if(request.getSession().getAttribute("login")==null){
            response.sendRedirect(LOGIN_VIEW);
            return;
        }*/
        String username=(String)request.getSession().getAttribute("login");
        String message=request.getParameter("message");
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        Blah blah = new Blah();
        blah.setUsername(username);
        blah.setDate(new Date(Long.parseLong(message)));
        userService.deleteBlah(blah);

        response.sendRedirect(SUCCESS_VIEW);
        /*File file=new File(USER+"/"+name+"/"+message+".txt");
        if(file.exists()){
            file.delete();
        }*/
    }
}
