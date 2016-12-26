<%@ page import="ru.innopolis.uni.course3.taxiapp.User" %><%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 25.12.2016
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create order page</title>
</head>
<body>
<%
    HttpSession ses = request.getSession();
    User user = (User) ses.getAttribute("user");
    String firstname = "";
    if (user != null) {
        firstname = user.getFirstname();

    } else {
        response.sendRedirect(request.getContextPath().concat("/index.jsp"));
    }
%>
    <h1>Hello, <%=firstname%>!</h1>
    <p>Для заказа такси укажите параметры поездки:</p>
    <form method="POST" action="${pageContext.request.contextPath}/order">
        <label for="start">Точка отправление:</label>
        <input id="start" name="start"> <br>
        <label for="finish">Точка назначения:</label>
        <input id="finish" name="finish"> <br>

        <input type="submit" value="Make order">
    </form>
</body>
</html>
