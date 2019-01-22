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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #FAEBD7">
<div class="container text-center">
    <h4 class="text-primary">Update User</h4>
    <form action="${pageContext.servletContext.contextPath}/action/main" method="POST">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name='id' value="${user.id}"/>
        <div class="form-group">
            <label for="name">
                Please enter new name:
            </label>
                <input type="text" name="name" id="name" value="${user.name}" required/>
        </div>
        <div class="form-group">
            <label for="login">
                Please enter new login:
            </label>
            <input type="text" name="login" id="login" value="${user.login}" required/>
        </div>
        <div class="form-group">
            <label for="password">
                Please enter new password:
            </label>
            <input type="password" name="password" id="password" value="${user.password}" required/>
        </div>
        <div class="form-group">
            <label for="email">
                Please enter new email:
            </label>
            <input type="text" name="email" id="email" value="${user.email}" required/>
        </div>
        <div class="form-group">
            <label for="role">
                Please select role:
            </label>
            <select name="role" id="role">
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <option value="ADMIN">Administrator</option>
                </c:if>
                <option value="USER">User</option>
            </select>
        </div>
        <input type="submit" value="submit" class="btn btn-primary"/>
    </form>
    <form action="${pageContext.servletContext.contextPath}/action/main" method="GET">
        <div class="form-group">
            <input type="submit" value="Back" class="btn btn-primary"/>
        </div>
    </form>
</div>
</body>
</html>
