<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Main page
  Role: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 21.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <style> .sub {background: #FFF5EE; color: #696969; font-size: 9pt;
        font-weight: 600; border-radius: 10px;
        padding: 5px 10px;
        margin: 0;}
        div {text-align: center}
    </style>
</head>
<body>
<h3><div>
    Welcome <c:out value="${sessionScope.name}"/>!
    You current role is [<c:out value="${sessionScope.role}"/>].
    <br>
    Please selected action.
</div></h3>

<c:if test="${sessionScope.role == 'ADMIN'}">
    <div>
        <form action="${pageContext.servletContext.contextPath}/action/list" method="GET">
            Go to the page with the list of users
            <input type="submit" value="Go to page" class="sub"/>
        </form>
    </div>
</c:if>

<div>
    <form action="${pageContext.servletContext.contextPath}/action/edit" method="GET">
        <input type="hidden" name="id" value="${sessionScope.id}">
        Edit your personal information
        <input type="submit" value="Update" class="sub"/>
    </form>
</div>
<div>
    <c:if test="${sessionScope.role == 'USER'}">
        <form action="${pageContext.servletContext.contextPath}/action/main" method="POST">
            <input type="hidden" name='action' value="delete"/>
            <input type="hidden" name='id' value="${sessionScope.id}"/>
            <input type="submit" value="Delete account" class="sub"/>
        </form>
    </c:if>
</div>
<div>
    <form action="${pageContext.servletContext.contextPath}/action/logout" method="GET">
        <input type="submit" value="End sessions" class="sub"/>
    </form>
</div>
</body>
</html>