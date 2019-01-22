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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #FAEBD7">
<div class="container">
    <c:if test="${msg != ''}">
        <h5 class="text-center" style="background-color: aquamarine"><c:out value="${msg}"/></h5>
    </c:if>
    <c:if test="${exc != ''}">
        <h5 class="text-center" style="background-color: lightcoral"><c:out value="${exc}"/></h5>
    </c:if>
    <c:if test="${exit == 'YES'}">
        <form action="${pageContext.servletContext.contextPath}/" method="GET">
            <div class="form-group">
                <input type="submit" value='Back' class="btn btn-primary"/>
            </div>
        </form>
    </c:if>
    <c:if test="${exit != 'YES'}">
        <form action="${pageContext.servletContext.contextPath}/action/main" method="GET">
            <div class="form-group text-left">
                <input type="submit" value='Back' class="btn btn-primary"/>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>