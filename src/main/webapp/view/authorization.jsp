<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 27.03.2019
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
    <h1>Sign in to system</h1>
    <form method="post" action="command">
        <input type="hidden" name="command" value="authorize">
        <input type="text" required placeholder="Login" name="login"><br>
        <input type="text" required placeholder="Password" name="password"><br>
        <input type="submit" value="Sign in"/>
    </form>
</body>
</html>
