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
    <title>Left panel client</title>
</head>
<body>
<ul class="vertical-menu">
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="get_client_menu">
            <button type="submit">Menu</button>
        </form><br>
    </li>
    <li>
        <button type="submit">Orders</button>
        <ul class="sub-menu">
            <li>
                <form action="command" method="post">
                    <input type="hidden" name="command" value="get_current_orders">
                    <button type="submit">Current</button>
                </form>
            </li>
            <li>
                <form action="command" method="post">
                    <input type="hidden" name="command" value="get_previous_orders">
                    <button type="submit">Previous</button>
                </form>
            </li>
            <li>
                <form action="command" method="post">
                    <input type="hidden" name="command" value="get_global_orders">
                    <button type="submit">Global</button>
                </form>
            </li>
        </ul>
    </li>
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="get_bonuses_client">
            <button type="submit">Bonuses</button>
        </form>
    </li>
    <li>
        <form action="command" method="post">
            <input type="hidden" name="command" value="show_basket">
            <button type="submit">Basket</button>
        </form>
    </li>
</ul>
</body>
</html>
