<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2019
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Administrator menu</title>
    <link href="view/style/main.css" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1>Dishes view</h1>

            <div class="content-actions">
                <form action="command" method="post">
                    <input type="hidden" name="command" value="specify_new_dish">
                    <button type="submit">Add new dish</button>
                </form>
            </div>

            <%--@elvariable id="allDishes" type="java.util.List<com.epam.cafe.entitie.Dish>"--%>
            <c:forEach items="${allDishes}" var="dish">
                <div class="dish">
                    <h3>Name - ${dish.name}</h3>
                    <p class="dish-cost">Cost - ${dish.cost}$</p>
                    <p class="dish-description">Description:<br>${dish.description}</p>
                    <p>Is in client menu -
                        <c:choose>
                            <c:when test="${dish.inMenu}">Yes</c:when>
                            <c:otherwise>No</c:otherwise>
                        </c:choose>
                    </p>
                    <img src="${dish.imageHref}" alt="${dish.name}">

                    <form action="command" method="post">
                        <input type="hidden" name="command" value="manage_dish_information">
                        <input type="hidden" name="dishID" value=${dish.ID}>
                        <button type="submit">Manage</button>
                    </form>
                    <form action="command" method="post">
                        <input type="hidden" name="command" value="delete_dish">
                        <input type="hidden" name="dishID" value=${dish.ID}>
                        <button id="delete-button" type="submit">x</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
