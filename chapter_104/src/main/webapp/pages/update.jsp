<%@ page import="ru.job4j.crud.User" %>
<%@ page import="ru.job4j.crud.ValidateService" %>
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

<%!private final ValidateService validate = ValidateService.getInstance();%>
<%User user = this.validate.findById(request.getParameter("id"));%>

<form action="<%=request.getContextPath()%>/user" method="POST">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name='id' value="<%=user.getId()%>"/>
    Please enter new name:
    <input type="text" name="name" placeholder="Your name=<%=user.getName()%>"/>
    <br>
    Please enter new login:
    <input type="text" name="login" placeholder="Your login=<%=user.getLogin()%>"/>
    <br>
    Please enter new email:
    <input type="text" name="email" placeholder="Your email=<%=user.getEmail()%>"/>
    <br>
    <input type="submit" value="submit"/>
</form>
<br>
<form action="<%=request.getContextPath()%>/" method="GET">
    <input type="submit" value="Back to main page"/>
</form>

</body>
</html>
