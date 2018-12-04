<%--
  Enter JSP.
  User: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 03.12.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <style> .sub {background: #FFF5EE; color: #696969; font-size: 9pt;
        font-weight: 600; border-radius: 10px;
        padding: 5px 10px;
        margin: 0;}
    </style>
</head>
<body>
<div style="text-align: center">
    <h3>Please enter you action</h3>

    <form action="${pageContext.servletContext.contextPath}/action/signing" method="GET">
        <input type="submit" value="Authorization" class="sub"/>
    </form>
    <form action="${pageContext.servletContext.contextPath}/create" method="GET">
        <input type="submit" value="Registration" class="sub"/>
    </form>
</div>
</body>
</html>
