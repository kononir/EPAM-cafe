<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmx:setBundle basename="locale.administrator.left_panel_admin" var="leftPanelB"/>
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
    <title><fmx:message bundle="${leftPanelB}" key="title"/></title>
</head>
<body>
<ul class="vertical-menu">
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="get_all_dishes">
            <button type="submit">
                <fmx:message bundle="${leftPanelB}" key="form.get.all.dishes.button.manage.dishes"/>
            </button>
        </form><br>
    </li>
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="get_clients">
            <button type="submit">
                <fmx:message bundle="${leftPanelB}" key="form.get.clients.button.manage.clients"/>
            </button>
        </form>
    </li>
</ul>
</body>
</html>
