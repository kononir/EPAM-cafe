<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 30.03.2019
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Administrator view</title>
</head>
<body>
    <h1>Hello Administrator!</h1>
    <form action="command" method="post">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="Sign out">
    </form>
</body>
</html>
