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
    <style> .sub {background: #FFF5EE; color: #696969; font-size: 9pt;
        font-weight: 600; border-radius: 10px;
        padding: 5px 10px;
        margin: 0;}
    </style>
</head>
<body>
<div style="text-align: center">
<h2>Create User</h2>
    <form action="${pageContext.servletContext.contextPath}/" method="POST">
        <input type="hidden" name="action" value="add"/>
        <label>
            User name:<input type="text" name="name" required/>
            <br>
            User login:<input type="text" name="login" required/>
            <br>
            User password:<input type="password" name="password" required/>
            <br>
            User email:<input type="text" name="email" required/>
            <br>
            <c:if test="${sessionScope.role == 'ADMIN'}">
            Please select role:
            <select name="role">
                <option value="ADMIN">Administrator</option>
                <option value="USER">User</option>
            </select>
            </c:if>
            <c:if test="${sessionScope.role == 'GUEST'}">
                <input type="hidden" name="role" value="USER"/>
            </c:if>
        </label>
        <br>
        <input type="submit" value="Submit" class="sub"/>
    </form>
    <form action="${pageContext.servletContext.contextPath}/" method="GET">
        <input type="submit" value="Back" class="sub"/>
    </form>
</div>

</body>
</html>
