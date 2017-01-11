<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 22.12.2016
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form method="POST" action="/add">
        <label for="username">Username:</label>
        <input id="username" name="username"> <br>
        <label for="password">Password:</label>
        <input id="password" name="password"> <br>
        <label for="phone">Phone number:</label>
        <input id="phone" name="phone"> <br>
        <label for="firstname">First name:</label>
        <input id="firstname" name="firstname"> <br>

        <label>User type:</label> <br>
        <p><input name="usertype" type="radio" checked="true" value="C" onclick="carInfoChangeVisibility()">Client</p>
        <p><input name="usertype" type="radio" value="D" onclick="carInfoChangeVisibility()">Driver</p>

        <div id="carInfo" style="display: none;">
            <label for="brand">Car brand:</label>
            <input id="brand" name="brand"> <br>
            <label for="number">Car number:</label>
            <input id="number" name="number"> <br>
        </div>

        <input type="submit" value="Зарегистрироваться">

    </form>
    <script>
        function carInfoChangeVisibility() {
            var element=document.getElementById("carInfo");
            console.log(element);
            if (element.style.display == "none") {
                element.style.display = "block";
            } else {
                element.style.display = "none";
            }
        }
    </script>
</body>
</html>
