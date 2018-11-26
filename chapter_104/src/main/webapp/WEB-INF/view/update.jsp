<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@elvariable id="user" type="ru.job4j.crud.User"--%>
<%--
  Update user.
  User: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 21.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Update Users</title>
</head>
<body>

<h2>Update User</h2>

<form action="${pageContext.servletContext.contextPath}/" method="POST">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name='id' value="${user.id}"/>
    Please enter new name:
    <input type="text" name="name" placeholder="Your name=${user.name}"/>
    <br>
    Please enter new login:
    <input type="text" name="login" placeholder="Your login=${user.login}"/>
    <br>
    Please enter new email:
    <input type="text" name="email" placeholder="Your email=${user.email}"/>
    <br>
    <input type="submit" value="submit"/>
</form>
<br>
<form action="${pageContext.servletContext.contextPath}/" method="GET">
    <input type="submit" value="Back to main page"/>
</form>

</body>
</html>
