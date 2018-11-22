<%@ page import="ru.job4j.crud.ValidateService" %>
<%@ page import="ru.job4j.crud.User" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
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

<%!private final ValidateService validate = ValidateService.getInstance();%>
<body>
<table width="60%" border="1" cellpadding="4" cellspacing="0" bgcolor="#FFF8DC">
    <caption>Current list of users</caption>
    <tr>
        <th>Id</th>
        <th>User name</th>
        <th>Create</th>
        <th>Actions</th>
    </tr>

    <%if (validate.findAll().isEmpty()) { %>
    <tr style="background: #FFFAFA; color: #C0C0C0; font-style: italic;">
        <th>Empty</th>
        <th>Empty</th>
        <th>Empty</th>
        <th>Empty</th>
    </tr>
    <%} else {
        for (User user : validate.findAll()) { %>
    <tr>
        <td align="center"><%=user.getId()%></td>
        <td align="center"><%=user.getName()%></td>
        <td align="center"><%=LocalDateTime.ofInstant(
                Instant.ofEpochMilli(user.getCreateDate()),
                TimeZone.getDefault().toZoneId()).format(
                DateTimeFormatter.ofPattern("yyyy-MMM-dd, HH:mm")
                        .withLocale(new Locale("en")))%></td>
        <td align="center">
            <form action="<%=request.getContextPath()%>/edit" method="GET">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" class="sub" value="Update"/>
            </form>
            <form action="<%=request.getContextPath()%>/user" method="POST">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" class="sub" value="Delete"/>
            </form>
        </td>
    </tr>
    <%}
    }%>
</table>
<p><form action="<%=request.getContextPath()%>/" method="GET">
    <input type="submit" value="Back to main page"/>
</form>
</body>
</html>