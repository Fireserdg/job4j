<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Error page.
  User: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 14.02.19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body style="background-color: bisque">
    <h4 class="text-success">Page not found</h4>
    <form action="${pageContext.servletContext.contextPath}/index.html" method="get">
        <input type="submit" value="Back to main page" class="btn btn-primary">
    </form>
</body>
</html>
