<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Create User
  User: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 21.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Create User</title>
</head>
<body>
<h2>Create User</h2>
<form action="${pageContext.servletContext.contextPath}/" method="POST">
    <input type="hidden" name="action" value="add"/>
    <label>
        User name:<input type="text" name="name" required/>
    </label><br>
    <label>
        User login:<input type="text" name="login" required/>
    </label><br>
    <label>
        User email:<input type="text" name="email" required/>
    </label><br>
    <input type="submit" value="Submit"/>
    </form>
<form action="${pageContext.servletContext.contextPath}/" method="GET">
    <input type="submit" value="Back to main page"/>
</form>

</body>
</html>
