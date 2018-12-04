<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@elvariable id="msg" type="java.lang.String"--%>
<%--@elvariable id="exc" type="java.lang.String"--%>
<%--@elvariable id="exit" type="java.lang.String"--%>
<%--
  Message.
  Role: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 22.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message about operation</title>
    <style> .sub {background: #FFF5EE; color: #696969; font-size: 9pt;
        font-weight: 600; border-radius: 10px;
        padding: 5px 10px;
        margin: 0;}
    </style>
</head>
<body>

<c:if test="${msg != ''}">
<div style="text-align: center; background-color: darkseagreen">
    <c:out value="${msg}"/>
</div>
</c:if>

<c:if test="${exc != ''}">
<div style="text-align: center; background-color: red">
        <c:out value="${exc}"/>
</div>
</c:if>
<br>

<c:if test="${exit == 'YES'}">
    <form action="${pageContext.servletContext.contextPath}\" method="GET">
        <input type="submit" value='Back' class="sub"/>
    </form>
</c:if>

<c:if test="${exit != 'YES'}">
    <form action="${pageContext.servletContext.contextPath}\action\main" method="GET">
        <input type="submit" value='Back' class="sub"/>
    </form>
</c:if>
</body>
</html>
