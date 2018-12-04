<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@elvariable id="user" type="ru.job4j.crud.models.User"--%>
<%--
  Update user.
  Role: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 21.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Update Users</title>
    <style> .sub {background: #FFF5EE; color: #696969; font-size: 9pt;
        font-weight: 600; border-radius: 10px;
        padding: 5px 10px;
        margin: 0;}
    </style>
</head>
<body>

<h2>Update User</h2>
<form action="${pageContext.servletContext.contextPath}/action/main" method="POST">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name='id' value="${user.id}"/>
    <label>
        Please enter new name:
        <input type="text" name="name" value="${user.name}" required/>
        <br>
        Please enter new login:
        <input type="text" name="login" value="${user.login}" required/>
        <br>
        Please enter new password:
        <input type="password" name="password" value="${user.password}" required/>
        <br>
        Please enter new email:
        <input type="text" name="email" value="${user.email}" required/>
        <br>
        Please select role:
        <select name="role">
            <c:if test="${sessionScope.role == 'ADMIN'}">
                <option value="ADMIN">Administrator</option>
            </c:if>
            <option value="USER">User</option>
        </select>
        <br>
        <input type="submit" value="submit" class="sub"/>
    </label>
</form>
<br>
<form action="${pageContext.servletContext.contextPath}/action/main" method="GET">
    <input type="submit" value="Back" class="sub"/>
</form>
</body>
</html>
