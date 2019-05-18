<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 27.04.2019
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Client menu</title>
    <link href="view/style/main.css" rel="stylesheet">
</head>
<body>
<c:import url="/view/page/general/top_panel.jsp"/>
<c:import url="/view/page/client/left_panel_client.jsp"/>
<div class="inner-content">
    <h1>Menu</h1>

    <%--@elvariable id="dishesInMenu" type="java.util.List<com.epam.cafe.entitie.Dish>"--%>
    <%--@elvariable id="user" type="com.epam.cafe.entitie.user.User"--%>
    <c:forEach items="${dishesInMenu}" var="dish">
        <div class="menu-dish">
            <h3>Name: ${dish.name}</h3>
            <img src="${dish.imageHref}" alt="${dish.name}">
            <p class="menu-dish-cost">Cost: ${dish.cost}</p>
            <p class="menu-dish-description">Description: ${dish.description}</p>

            <form action="command" method="post">
                <input type="hidden" name="command" value="save_dish_order">
                <input type="hidden" name="userID" value="${user.ID}">
                <input type="hidden" name="selectedDishID" value="${dish.ID}">
                <button type="submit">Order</button>
            </form>
        </div>
    </c:forEach>
</div>
<c:import url="/view/page/general/footer.jsp"/>
</body>
</html>
