package com.controller;

import com.model.Blah;
import com.model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ServletMessage",urlPatterns = "/message.do",initParams = {
        @WebInitParam(name="MEMBER_VIEW",value = "member.jsp")})
public class ServletMessage extends HttpServlet {


    private  String MEMBER_VIEW;

    @Override
    public void init() throws ServletException {
        MEMBER_VIEW=getServletConfig().getInitParameter("MEMBER_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //若无login属性， 重定位到登陆页面
        //如果信息在140字以内，保存到用户的目录文档，否则转发会员网页
        /*if(request.getSession().getAttribute("login")==null){
            response.sendRedirect(LOGIN_VIEW);
            return;
        }*/
        request.setCharacterEncoding("utf-8");
        String username=(String)request.getSession().getAttribute("login");
        UserService userService=(UserService) getServletContext().getAttribute("userService");
        Blah blah=new Blah();
        blah.setUsername(username);
        String blabla=request.getParameter("blabla");

        if(blabla!=null&&blabla.length()!=0){
            if(blabla.length()<140){
                blah.setDate(new Date());
                blah.setTxt(blabla);
                userService.addBlah(blah);
            }
            else {
                request.setAttribute("blabla",blabla);
            }
        }
        List<Blah> blahs = userService.getBlahs(blah);
        request.setAttribute("blahs", blahs);
        request.getRequestDispatcher(MEMBER_VIEW).forward(request, response);
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
