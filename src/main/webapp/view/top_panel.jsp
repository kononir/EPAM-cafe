<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.04.2019
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Top panel</title>
</head>
<body>
    <ul class="horizontal-menu">
        <li>
            <form method="post">
                <input type="hidden" name="command" value="logout">
                <button type="submit">Sign out</button>
            </form>
        </li>
        <li>
            <form method="post">
                <button type="submit">Change language</button>
            </form>
        </li>
    </ul>
</body>
</html>
