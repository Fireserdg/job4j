<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@elvariable id="users" type="java.util.List<ru.job4j.crud.User>"--%>
<%--
  List of users
  User: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 21.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>List of Users</title>
    <style> .sub {background: #FFF5EE; color: #696969; font-size: 9pt;
        font-weight: 600; border-radius: 10px;
        padding: 5px 10px;
        margin: 0;}
    </style>
</head>

<body>
<table width="60%" border="1" cellpadding="4" cellspacing="0" bgcolor="#FFF8DC">

        <caption>Current list of users</caption>
    <tr>
        <th>Id</th>
        <th>User name</th>
        <th>Create</th>
        <th>Actions</th>
    </tr>

    <c:if test="${users.size() == 0}">
        <tr style="background: #FFFAFA; color: #C0C0C0; font-style: italic;">
            <th>Empty</th>
            <th>Empty</th>
            <th>Empty</th>
            <th>Empty</th>
        </tr>
    </c:if>
    <c:forEach items="${users}" var="user">
    <tr>
        <td align="center"><c:out value="${user.id}"/></td>
        <td align="center"><c:out value="${user.name}"/></td>
        <td align="center"><c:out value="${user.date}"/></td>
        <td align="center">
            <form action="${pageContext.servletContext.contextPath}/edit" method="GET">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" class="sub" value="Update"/>
            </form>
            <form action="${pageContext.servletContext.contextPath}/" method="POST">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" class="sub" value="Delete"/>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
<p><form action="${pageContext.servletContext.contextPath}/" method="GET">
    <input type="submit" value="Back to main page"/>
</form>
</body>
</html>