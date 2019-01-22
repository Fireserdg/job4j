<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@elvariable id="error" type="java.lang.String"--%>
<%--
  Login JSP
  Role: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 26.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User authorization</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #FAEBD7">
<c:if test="${error != ''}">
    <div class="container text-center" style="background-color: #FF0000; width: 500px">
        <c:out value="${error}"/>
    </div>
    </c:if>
<div class="container text-center">
    <h3 class="text-primary">User authorization</h3>
    <form action="${pageContext.servletContext.contextPath}/action/signing" method="POST">
        <div class="form-group">
            <label for="login">
                User login:
            </label>
            <input type="text" name="login" id="login" placeholder="your login" required/>
        </div>
        <div class="form-group">
            <label for="password">
                User password:
            </label>
            <input type="password" name="password" id="password" placeholder="your password" required/>
        </div>
        <div class="form-group">
            <input type="submit" value="Submit" class="btn btn-primary"/>
        </div>
    </form>
    <form action="${pageContext.servletContext.contextPath}/" method="GET">
        <div class="form-group">
            <input type="submit" value="Back to guest page" class="btn btn-primary"/>
        </div>
    </form>
</div>
</body>
</html>
