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
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <h1>Menu</h1>

            <%--@elvariable id="dishesInMenu" type="java.util.List<com.epam.cafe.entitie.Dish>"--%>
            <c:forEach items="${dishesInMenu}" var="dish">
                <div class="entity">
                    <h3>Name - ${dish.name}</h3>
                    <p>Cost - ${dish.cost}$</p>
                    <p>Description:<br>${dish.description}</p>
                    <img src="${dish.imageHref}" alt="${dish.name}">

                    <form action="command" method="post">
                        <input type="hidden" name="command" value="save_dish_order">
                        <input type="hidden" name="selectedDishID" value="${dish.ID}">
                        <label for="servings-number">Choose servings number</label><br>
                        <input id="servings-number" type="number" name="servingsNumber" min="1"
                               required><br>
                        <button type="submit">Save to basket</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
