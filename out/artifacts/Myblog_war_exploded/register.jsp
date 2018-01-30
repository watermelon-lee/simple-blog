<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: wade
  Date: 2018-01-26
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<c:if test="${requestScope.errors!=null}">
    <h1>注册失败</h1>
    <ul style="color: red">
    <c:forEach var="error" items="${requestScope.errors}">
        <li>${error}</li>
    </c:forEach>
    </ul><br>
</c:if>
<h1>用户注册</h1>
<form action="/Myblog/register.do" method="post">
    邮箱：<input type="text" name="email"><br>
    姓名：<input type="text" name="name"><br>
    密码：<input type="password" name="password" size="25" maxlength="16"><br>
    确认密码：<input type="password" name="confirmedPassword" size="25" maxlength="16"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
