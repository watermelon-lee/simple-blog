package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@WebServlet(name = "ServletLogin",urlPatterns = "/login.do")
public class ServletLogin extends HttpServlet {
    private final String USERS="d:out/user";
    private final String SUCCESS_VIEW="member.view";
    private final String ERROR_VIEW="index.html";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html charset:UTF-8");
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        if(CheckLogin(name,password)){
            request.getSession().setAttribute("login",name);
            request.getRequestDispatcher(SUCCESS_VIEW).forward(request,response);
        }
        else{
            response.sendRedirect(ERROR_VIEW);//重定位为登陆页面
        }
    }

    private boolean CheckLogin(String name,String password)throws IOException{
        if(name!=null&&password!=null){
            for(String filename:new File(USERS).list()){
                if(name.equals(filename)){
                    BufferedReader reader=new BufferedReader(new FileReader(USERS+"/"+filename+"/profile.txt"));
                    String  passwd=reader.readLine().split("\t")[1];
                    if(passwd.equals(password)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
