package com.controller;

import com.model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@WebServlet(name = "ServletMessage",urlPatterns = "/message.do",initParams = {
        @WebInitParam(name="SUCCESS_VIEW",value = "member.view"), @WebInitParam(name="ERROR_VIEW",value="member.view")})
public class ServletMessage extends HttpServlet {


    private  String SUCCESS_VIEW;
    private  String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW=getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW=getServletConfig().getInitParameter("ERROR_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //若无login属性， 重定位到登陆页面
        //如果信息在140字以内，保存到用户的目录文档，否则转发会员网页
        /*if(request.getSession().getAttribute("login")==null){
            response.sendRedirect(LOGIN_VIEW);
            return;
        }*/
        request.setCharacterEncoding("utf-8");
        String blabla=request.getParameter("blabla");
        if(blabla!=null&&blabla.length()!=0){
            if(blabla.length()<140){
                String name=(String)request.getSession().getAttribute("login");
                UserService userService=(UserService)getServletContext().getAttribute("userService");
                userService.addMessage(name,blabla);
                response.sendRedirect(SUCCESS_VIEW);
            }
            else {
                request.getRequestDispatcher(ERROR_VIEW).forward(request,response);
            }
        }
        else{
            response.sendRedirect(ERROR_VIEW);
        }
    }

   /* private void addMessage(String name,String blabla)throws IOException{
        String file=USER+"/"+name+"/"+new Date().getTime()+".txt";
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
        writer.write(blabla);
        writer.close();
    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
