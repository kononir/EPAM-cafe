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
            <input type="hidden" name="command" value="get_all_dishes">
            <button type="submit">Manage dishes</button>
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
