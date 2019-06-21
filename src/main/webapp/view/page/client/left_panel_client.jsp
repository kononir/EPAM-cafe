<%@ taglib prefix="fmx" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmx:setBundle basename="locale.client.left_panel_client" var="leftPanelClientB"/>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 20.04.2019
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmx:message bundle="${leftPanelClientB}" key="title"/></title>
</head>
<body>
<ul class="vertical-menu">
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="get_client_menu">
            <button type="submit">
                <fmx:message bundle="${leftPanelClientB}" key="form.get.client.menu.button.menu"/>
            </button>
        </form>
        <br>
    </li>
    <li>
        <button type="submit"><fmx:message bundle="${leftPanelClientB}" key="button.orders"/></button>
        <ul class="sub-menu">
            <li>
                <form action="command" method="post">
                    <input type="hidden" name="command" value="get_current_orders">
                    <button type="submit">
                        <fmx:message bundle="${leftPanelClientB}"
                                     key="form.get.current.orders.button.current"/>
                    </button>
                </form>
            </li>
            <li>
                <form action="command" method="post">
                    <input type="hidden" name="command" value="get_previous_orders">
                    <button type="submit">
                        <fmx:message bundle="${leftPanelClientB}"
                                     key="form.get.current.orders.button.previous"/>
                    </button>
                </form>
            </li>
            <li>
                <form action="command" method="post">
                    <input type="hidden" name="command" value="get_global_orders">
                    <button type="submit">
                        <fmx:message bundle="${leftPanelClientB}"
                                     key="form.get.current.orders.button.global"/>
                    </button>
                </form>
            </li>
        </ul>
    </li>
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="get_bonuses_client">
            <button type="submit">
                <fmx:message bundle="${leftPanelClientB}" key="form.get.bonuses.client.button.bonuses"/>
            </button>
        </form>
    </li>
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="show_basket">
            <button type="submit">
                <fmx:message bundle="${leftPanelClientB}" key="form.show.basket.button.basket"/>
            </button>
        </form>
    </li>
</ul>
</body>
</html>
