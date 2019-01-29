<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@elvariable id="users" type="java.util.List<ru.job4j.crud.models.User>"--%>
<%--
  List of users
  Role: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 21.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>List of Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript"><%@include file="/WEB-INF/scripts/list.js"%></script>
</head>
<body style="background-color: #FFF9F8">
<div class="container">
    <table class="table table-bordered" style="background-color: #FFF9F8">
        <h3 class="text-center">Current list of users</h3>
        <tr style="background-color: #FFE4C4">
            <th class="text-center">Id</th>
            <th class="text-center">User name</th>
            <th class="text-center">User login</th>
            <th class="text-center">User password</th>
            <th class="text-center">Create</th>
            <th class="text-center">Role</th>
            <th class="text-center">Actions</th>
        </tr>
        <c:if test="${users.size() == 0}">
            <tr>
                <th>Empty</th>
                <th>Empty</th>
                <th>Empty</th>
                <th>Empty</th>
                <th>Empty</th>
                <th>Empty</th>
                <th>Empty</th>
            </tr>
        </c:if>
        <c:forEach items="${users}" var="user">
            <tr class="warning">
                <td class="text-center"><c:out value="${user.id}"/></td>
                <td class="text-center"><c:out value="${user.name}"/></td>
                <td class="text-center"><c:out value="${user.login}"/></td>
                <td class="text-center"><c:out value="${user.password}"/></td>
                <td class="text-center"><c:out value="${user.date}"/></td>
                <td class="text-center"><c:out value="${user.role}"/></td>
                <td class="text-center">
                    <form action="${pageContext.servletContext.contextPath}/action/edit" method="GET">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <input type="submit" class="btn btn-primary btn-sm" value="Update"/>
                    </form>
                    <form>
                        <input type="hidden" name="action" id="action" value="delete"/>
                        <input type="hidden" name="id" id="userId" value="${user.id}"/>
                        <input type="button" class="btn btn-primary btn-sm" value="Delete" onclick="deleteUser(${user.id})"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="container text-left">
    <p><form action="${pageContext.servletContext.contextPath}/create" method="GET">
    <input type="submit" value="Add user" class="btn btn-primary"/>
</form>
    <p><form action="${pageContext.servletContext.contextPath}/action/main" method="GET">
    <input type="submit" value="Back to main page" class="btn btn-primary"/>
</form>
</div>
<div class="container text-left">
    <form action="${pageContext.servletContext.contextPath}/action/logout" method="GET">
        <input type="submit" value="End sessions" class="btn btn-primary"/>
    </form>
</div>
</body>
</html>