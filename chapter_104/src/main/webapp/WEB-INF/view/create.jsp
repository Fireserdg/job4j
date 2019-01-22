<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Create Role
  Role: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 21.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Create User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #FAEBD7">
<div class="container text-center">
    <h2 class="text-primary">Create User</h2>
    <form action="${pageContext.servletContext.contextPath}/" method="POST">
        <input type="hidden" name="action" value="add"/>
        <div class="form-group">
            <label for="name">
                User name:
            </label>
            <input type="text" name="name" id="name" required/>
        </div>
        <div class="form-group">
            <label for="login">
                User login:
            </label>
            <input type="text" name="login" id="login" required/>
        </div>
        <div class="form-group">
            <label for="password">
                User password:
            </label>
            <input type="password" name="password" id="password" required/>
        </div>
        <div class="form-group">
            <label for="email">
                User email:
            </label>
            <input type="text" name="email" id="email" required/>
        </div>
        <div class="form-group">
            <c:if test="${sessionScope.role == 'ADMIN'}">
                <label for="role">
                    Please select role:
                </label>
                <select name="role" id="role">
                    <option value="ADMIN">Administrator</option>
                    <option value="USER">User</option>
                </select>
            </c:if>
            <c:if test="${sessionScope.role == 'GUEST'}">
                <input type="hidden" name="role" value="USER"/>
            </c:if>
        </div>
        <div class="form-group">
            <input type="submit" value="Submit" class="btn btn-primary"/>
        </div>
    </form>
    <div>
        <form action="${pageContext.servletContext.contextPath}/" method="GET">
            <input type="submit" value="Back" class="btn btn-primary"/>
        </form>
    </div>
</div>

</body>
</html>
