package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletRegister",urlPatterns = "/register.do")
public class ServletRegister extends HttpServlet {

    private String USERS="d:/out/user";
    private String SUCCESS_VIEW="success.view";
    private String ERROR_VIEW="error.view";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String username=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String confirmedPassword=request.getParameter("confirmedPassword");//获取所有参数
        List<String> errors= new ArrayList<String>();//收集出错信息
        if(IsInValidUsername(username)){
            errors.add("用户名为空或已存在");
        }
        if(IsInValidEmail(email)){
            errors.add("未填写邮件或邮件格式不正确");
        }
        if(IsInValidPassword(password,confirmedPassword)){
            errors.add("确认符合密码格式并重新确认密码");
        }

        String resultPage=ERROR_VIEW;
        if(!errors.isEmpty()){
            request.setAttribute("errors",errors);
        }
        else{
            resultPage=SUCCESS_VIEW;
            createUserData(email,username,password);//创建用户资料
        }
        request.getRequestDispatcher(resultPage).forward(request,response);
    }

    private boolean IsInValidUsername(String username){
        for(String str:new File(USERS).list()){//检查用户资料夹是否创建来确认用户是否注册
            if(str.equals(username)){
                return true;
            }
        }
        return false;
    }

    private boolean IsInValidEmail(String email){
        return email==null||!email.matches("^[a-z0-9]+([.]"+"[a-z0-9]+)*@[a-z0-9]+([.][a-z0-9]+)*$");
    }

    private boolean IsInValidPassword(String password,String confirmedPassword){
        return password.length()>16||password.length()<6||password==null||!password.equals(confirmedPassword);
    }

    private void createUserData(String email,String username,String password)throws IOException{
        File file=new File(USERS+"/"+username);
        file.mkdir();//建立一级目录的文件夹
        BufferedWriter writer=new BufferedWriter(new FileWriter(file+"/profile"));
        writer.write(email+"\t"+password);
        writer.close();
    }

}
