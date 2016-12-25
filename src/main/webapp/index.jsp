<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 21.12.2016
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  Hello user! <br>
  Enter your login and password <br>
  <form method="POST" onsubmit="">
    <label for="username">Username:</label>
    <input id="username"> <br>
    <label for="username">Password:</label>
    <input id="password"> <br>

    <input type="submit" value="Sign in">

    <%--<button> Sign in </button>--%>
  </form>
  <br>
  <%--<label for="username">Username:</label>--%>
  <%--<input id="username"> <br>--%>
  <%--<label for="username">Password:</label>--%>
  <%--<input id="password">--%>

  <%--<button> Sign in </button>--%>
  <a href="registration.jsp"> Sign up </a>
  </body>
</html>
