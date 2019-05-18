<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.04.2019
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Left panel admin</title>
</head>
<body>
<ul class="vertical-menu">
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="get_full_menu">
            <button type="submit">Manage Menu</button>
        </form><br>
    </li>
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="get_clients">
            <button type="submit">Manage clients</button>
        </form>
    </li>
</ul>
</body>
</html>
