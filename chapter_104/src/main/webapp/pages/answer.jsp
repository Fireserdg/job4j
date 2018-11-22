<%--
  Message.
  User: Sergey Filippov (serdg1984@yandex.ru).
  Version: 1.0
  Since: 22.11.18 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message about operation</title>
</head>
<body>

<div style="text-align: center;">
    <h2><%=request.getParameter("msg")%></h2>
</div><br>
<form action="<%=request.getContextPath()%>\" method="GET">
    <input type="submit" value='Back to main page'/>
</form>

</body>
</html>
