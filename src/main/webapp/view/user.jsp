<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 30.03.2019
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>User view</title>
</head>
<body>
    <h1>Hello User!</h1>
    <form action="command" method="post">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="Sign out">
    </form>
</body>
</html>
