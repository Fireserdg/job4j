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
    <style> .sub {background: #FFF5EE; color: #696969; font-size: 9pt;
        font-weight: 600; border-radius: 10px;
        padding: 5px 10px;
        margin: 0;}
    </style>
</head>
<body>

<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<h3 style="text-align: center">User authorization</h3>
<div style="text-align: center">
    <form action="${pageContext.servletContext.contextPath}/action/signing" method="POST">
        <label>
            User login:<input type="text" name="login" placeholder="your login" required/>
        </label><br>
        <label>
            User password:<input type="password" name="password" placeholder="your password" required/>
        </label><br>
        <input type="submit" value="Submit" class="sub"/>
    </form>
    <br>
    <form action="${pageContext.servletContext.contextPath}/" method="GET">
        <input type="submit" value="Back to guest page" class="sub"/>
    </form>
</div>

</body>
</html>
