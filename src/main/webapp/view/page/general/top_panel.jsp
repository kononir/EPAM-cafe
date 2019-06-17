<%--@elvariable id="user" type="com.epam.cafe.entitie.user.User"--%>
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
        <button type="submit">Change language</button>
        <ul class="sub-menu">
            <li>
                <form action="command" method="post">
                    <button type="submit">English</button>
                </form>
            </li>
            <li>
                <form action="command" method="post">
                    <button type="submit">Русский</button>
                </form>
            </li>
            <li>
                <form action="command" method="post">
                    <button type="submit">Беларуская</button>
                </form>
            </li>
        </ul>
    </li>
</ul>
<div class="inner-content">
    <div id="current-user">Login - ${user.login}, role - ${user.role}</div>
</div>
</body>
</html>
