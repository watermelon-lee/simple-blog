package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "ServletDelete",urlPatterns = "/delete.do")
public class ServletDelete extends HttpServlet {
    private final String USER="d:out/user";
    private final String LOGIN_VIEW="index.html";
    private final String SUCCESS_VIEW="member.view";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("login")==null){
            response.sendRedirect(LOGIN_VIEW);
            return;
        }
        String name=(String)request.getSession().getAttribute("login");
        String message=request.getParameter("message");
        File file=new File(USER+"/"+name+"/"+message+".txt");
        if(file.exists()){
            file.delete();
        }
        response.sendRedirect(SUCCESS_VIEW);
    }
}
