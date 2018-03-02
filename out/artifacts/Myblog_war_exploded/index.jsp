<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: wade
  Date: 2018-01-30
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gossip </title>
    <link rel="stylesheet" href="css/index.css" type="text/css">
</head>
<body>
<div class="leftPanel">
    <img src='images/pic1.jpg' alt='Gossip 微网志'/>
</div>
<div>
    <h1>Gossip ... XD</h1>
    <ul>
        <li>谈天说地不奇怪
        <li>分享讯息也可以
        <li>随意写写表心情
    </ul>
    <table style="text-align: left; width: 510px;height: 88px;" border="0" cellpadding="2" cellspacing="2">
        <thead>
        <tr>
            <th><hr></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="blah" items="${applicationScope.userService.newest}">
            <tr>
                <td style="vertical-align: top">
                    ${blah.username}<br>
                    <c:out value="${blah.txt}"/><br>
                    <fmt:formatDate value="${blah.date}" type="both" dateStyle="full" timeStyle="full"/>
                    <hr>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>

    <p>
    <form method='post' action='/Myblog/login.do'>
        <div style="color: dodgerblue ">${requestScope.error}</div><br>
        <table bgcolor='#cccccc'>
            <tr>
                <td colspan='2'>会员登入</td>
            <tr>
                <td>名称：</td>
                <td><input type='text' name='name' value="${param.username}"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type='password' name='password'></td>
            </tr>
            <tr>
                <td colspan='2' align='center'><input type='submit' value='登入'></td>
            </tr>
            <tr>
                <td colspan='2'><a href='forgot.html'>忘记密码？</a> <br>
                    <a href='register.jsp'>还不是会员？</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
