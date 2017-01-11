<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 27.12.2016
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello ${sessionScope.get("user").getFirstname()}!</h1>
    <p>
        Место отправления: ${order.getStart()}, Место назначения: ${order.getFinish()}
    </p>
    А также сделать кнопку "Закончить поездку".
    <form method="POST" action="${pageContext.request.contextPath}/completeOrder">
        <input type="hidden" name="id" value="${order.getId()}">
        <input type="submit" value="Завершить заказ">
    </form>
<p>
    <a href="${pageContext.servletContext.contextPath}/logout">Выйти</a>
</p>
</body>
</html>
