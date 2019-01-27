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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #FAEBD7">
<div class="container" style="text-align: center">
    <h3 class="text-primary">Please enter you action</h3>
    <form action="${pageContext.servletContext.contextPath}/action/signing" method="GET">
        <div class="form-group">
            <input type="submit" value="Authorization" class="btn btn-primary"/>
        </div>
    </form>
    <form action="${pageContext.servletContext.contextPath}/create" method="GET">
        <div class="form-group">
        <input type="submit" value="Registration" class="btn btn-primary"/>
        </div>
    </form>
</div>
</body>
</html>
