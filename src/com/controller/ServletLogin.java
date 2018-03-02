package com.controller;

import com.model.Account;
import com.model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@WebServlet(name = "ServletLogin",urlPatterns = "/login.do",initParams = {
        @WebInitParam(name="SUCCESS_VIEW",value = "member.jsp"),
        @WebInitParam(name = "ERROR_VIEW",value="index.jsp")})


public class ServletLogin extends HttpServlet {
    private  String SUCCESS_VIEW;
    private  String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW=getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW=getServletConfig().getInitParameter("ERROR_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html charset:UTF-8");
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        UserService userService=(UserService)getServletContext().getAttribute("userService");
        Account account=new Account();
        account.setName(name);
        account.setPassword(password);
        if(userService.checkLogin(account)){
            request.getSession().setAttribute("login",name);
            request.getRequestDispatcher(SUCCESS_VIEW).forward(request,response);
        }
        else{
            request.setAttribute("error","名称或密码错误");
            request.getRequestDispatcher(ERROR_VIEW).forward(request,response);
        }
    }

    /**
     * 将其总和到了userService
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    /*private boolean CheckLogin(String name,String password)throws IOException{
        if(name!=null&&password!=null){
            for(String filename:new File(USERS).list()){
                if(name.equals(filename)){
                    BufferedReader reader=new BufferedReader(new FileReader(USERS+"/"+filename+"/profile"));
                    String  passwd=reader.readLine().split("\t")[1];
                    if(passwd.equals(password)){
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
