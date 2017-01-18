<%@ page import="ru.innopolis.uni.course3.taxiapp.POJO.User" %><%--
  Created by IntelliJ IDEA.
  UserDAO: julia
  Date: 25.12.2016
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current order</title>
</head>
<body>
    <h1>Hello, ${sessionScope.get("user").getFirstname()}!</h1>
    <p>You placed order. Now you can wait only!</p>

    <form method="POST" action="/logout">
        <input type="submit" value="Выйти" >

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>

    <%--<p>--%>
        <%--<a href="${pageContext.servletContext.contextPath}/logout">Выйти</a>--%>
    <%--</p>--%>
</body>
</html>
