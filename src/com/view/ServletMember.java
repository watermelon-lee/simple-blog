package com.view;

import com.model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.util.*;

@WebServlet(name = "ServletMember",urlPatterns = "/member.view")
public class ServletMember extends HttpServlet {

        private final String USER = "d:out/user";
        private final String LOGIN_VIEW = "index.html";

        protected void processRequest(HttpServletRequest request,
                                      HttpServletResponse response) throws ServletException, IOException {
            if (request.getSession().getAttribute("login") == null) {
                response.sendRedirect(LOGIN_VIEW);
                return;
            }

            String username = (String) request.getSession().getAttribute("login");

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
            out.println("<html>");
            out.println("<head>");
            out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
            out.println("<title>Gossip </title>");
            out.println("<link rel='stylesheet' href='css/member.css' type='text/css'>");
            out.println("</head>");
            out.println("<body>");

            String name = (String) request.getSession().getAttribute("login");

            out.println("<div class='leftPanel'>");
            out.println("<img src='images/pic1.jpg' alt='Gossip 微博' /><br><br>");


            out.println("<a href='logout.do?user="+name+"'>注销"+name+"</a>");


            out.println("</div>");
            out.println("<form method='post' action='message.do'>");
            out.println("分享新鲜事...<br>");



            String blabla=request.getParameter("blabla");
            if(blabla==null){
                blabla="";
            }
            else{
                out.println("信息不得大于140字");
            }

            out.println("<textarea cols='60' rows='4' name='blabla'>"+blabla+"</textarea>");
            out.println("<br>");
            out.println("<input type='submit' value='提交'>");
            out.println("</form>");
            out.println("<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>");
            out.println("<thead>");
            out.println("<tr><th><hr></th></tr>");
            out.println("</thead>");
            out.println("<tbody>");

            UserService userService=(UserService)getServletContext().getAttribute("userService");

            Map<Date,String> message=userService.readMessage(name);
            DateFormat dateFormat=DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL, Locale.CHINA);//获取指定地区日期，时间的格式

            for(Date date:message.keySet()){
                out.println("<tr><td style='vertical-align:top;'>");
                out.println(name+"<br>");
                out.println(message.get(date)+"<br>");//返回指定键的值
                out.println(dateFormat.format(date));
                out.println("<a href='delete.do?message="+date.getTime()+"'>删除</a>");
                out.println("<hr></td></tr>");
            }


            out.println("</tbody>");
            out.println("</table>");
            out.println("<hr style='width: 100%; height: 1px;'>");
            out.println("</body>");
            out.println("</html>");

            out.close();
        }

        /**
        //用以过滤.txt文件名
        private class TxtFilenameFilter implements FilenameFilter{
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        }

        private TxtFilenameFilter filenameFilter=new TxtFilenameFilter();

        //treeMap 排序用，希望日期最近的在上面显示
        private class DateComparator implements Comparator{
            @Override
            public int compare(Object o1, Object o2) {
                Date d1=(Date)o1;
                Date d2=(Date)o2;
                return -d1.compareTo(d2);
            }
        }

        private DateComparator dateComparator=new DateComparator();

        private Map<Date, String> readMessage(String name)throws IOException{
            File boder=new File(USER+"/"+name);
            String[] txts=boder.list(filenameFilter);

            Map<Date,String> messages=new TreeMap<Date, String>(dateComparator);
            for(String txt : txts) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(USER + "/" + name + "/" + txt), "UTF-8"));
                String text = null;
                StringBuilder builder = new StringBuilder();
                while((text = reader.readLine()) != null) {
                    builder.append(text);
                }
                Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));//文件名为发送时间
                messages.put(date, builder.toString());
                reader.close();
            }

            return messages;

        }
         *
         * *
         *
         *
         **/
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            processRequest(request, response);
            }
}
