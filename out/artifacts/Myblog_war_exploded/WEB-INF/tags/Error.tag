<%@tag description="错误答案显示标签" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="headline"%>
<c:if test="${requestScope.errors!=null}">
    <h1>${headline}</h1>
    <ul style="color: #3333cc">
    <c:forEach var="error" items="${requestScope.errors}">
        <li>${error}</li>
    </c:forEach>
    </ul><br>
</c:if>
