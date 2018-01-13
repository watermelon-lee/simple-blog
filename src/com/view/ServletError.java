package com.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletError",urlPatterns = "/error.view")
public class ServletError extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset:UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta content='text/html;charset=UTF-8'"+"http-equiv='content-type'");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>注册失败</h1>");
        out.println("<ul style='color:rgb(255,0,0);'>");
        List<String> errors=(List<String>)request.getAttribute("errors");//请求属性
        for(String error:errors){
            out.println("<li>"+error+"</li>");
        }
        out.println("</ul>");
        out.println("<a href='register.html'>返回注册页面</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
