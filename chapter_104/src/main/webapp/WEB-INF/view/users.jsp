<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Main page
  User: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 21.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>

<h2><div style="text-align: center;">
    Hello! Selected action.
</div></h2>
<div style="text-align: center;">
    <form action="${pageContext.servletContext.contextPath}/create" method="GET">
        Go to page for create new user
        <input type="submit" value="Go to page"/>
    </form>
</div>

<div style="text-align: center;">
    <form action="${pageContext.servletContext.contextPath}/list" method="GET">
        Go to the page with the list of users
        <input type="submit" value="Go to page"/>
    </form>
</div>
</body>
</html>
