<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: wade
  Date: 2018-01-26
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,java.text.*,com.model.UserService"%>
<%@ page import="com.model.Blah" %>

<html>
<head>
    <title>Gossip微博</title>
    <link rel="stylesheet" href="css/member.css" type="text/css">
</head>
<body>
<div class="leftPanel">
    <img src="images/pic1.jpg" alt="Gossip微博">
    <br><br>
    <a href="logout.do?username="${sessionScope.login} >注销${sessionScope["login"]}</a>
</div>
<form action="message.do" method="post">
    分享新鲜>!< <br>
    <!--<%
        String blabla=request.getParameter("blabla");
        if(blabla==null){
            blabla="";
        }else {
    %>
    信息要在140字以内
    <%
        }
    %>-->

    <c:if test="${requestScope.blabla!=null}">
        信息要在140字以内
    </c:if>

    <textarea cols="60" rows="4" name="blabla">${requestScope.blabla}</textarea><br>
    <input type="submit" value="提交" >
</form>

<table style="text-align: left;width:510px;height: 88px;" border="0" cellpadding="2" cellspacing="2">
    <thead>
    <tr>
        <th>
            <hr>
        </th>
    </tr>
    </thead>
    <tbody>

<%
    Blah blah=new Blah();
    blah.setUsername((String)session.getAttribute("login"));
    UserService userService = (UserService) application.getAttribute("userService");
    List<Blah> BlahList=(List<Blah>)userService.getBlahs(blah);
    request.setAttribute("BlahList",BlahList);
%>

<c:forEach var="blah" items="${BlahList}">
    <tr>
        <td style="vertical-align: top">
            ${blah.username}<br>
            <c:out value="${blah.txt}"/><br>
            <fmt:formatDate value="${blah.date}" type="both" dateStyle="full" timeStyle="full"/>
            <a href="delete.do?message=${blah.date.time}">删除</a>
            <hr>
        </td>
    </tr>
</c:forEach>


    </tbody>
</table>
<hr style="width: 100%;height: 1px;">
</body>
</html>
