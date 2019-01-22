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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #FAEBD7">
<div class="container text-center">
    <h4 class="text-primary">
        Welcome <c:out value="${sessionScope.name}"/>!
        You current role is [<c:out value="${sessionScope.role}"/>].
        Please selected action.
    </h4>
</div>

<div class="container text-center">
    <c:if test="${sessionScope.role == 'ADMIN'}">
        <form action="${pageContext.servletContext.contextPath}/action/list" method="GET">
            <div class="form-group">
                <label for="goPage">
                    Go to the page with the list of users
                </label>
                <input type="submit" value="Go to page" id="goPage" class="btn btn-primary btn-sm"/>
            </div>
        </form>
    </c:if>

    <form action="${pageContext.servletContext.contextPath}/action/edit" method="GET">
        <input type="hidden" name="id" value="${sessionScope.id}">
        <div class="form-group">
            <label for="update">
                Edit your personal information
            </label>
            <input type="submit" value="Update" id="update" class="btn btn-primary btn-sm"/>
        </div>
    </form>
    <c:if test="${sessionScope.role == 'USER'}">
        <form action="${pageContext.servletContext.contextPath}/action/main" method="POST">
            <input type="hidden" name='action' value="delete"/>
            <input type="hidden" name='id' value="${sessionScope.id}"/>
            <div class="form-group">
                <input type="submit" value="Delete account" class="btn btn-primary btn-sm"/>
            </div>
        </form>
    </c:if>
    <form action="${pageContext.servletContext.contextPath}/action/logout" method="GET">
        <div class="form-group">
            <input type="submit" value="End sessions" class="btn btn-primary btn-sm"/>
        </div>
    </form>
</div>
</body>
</html>