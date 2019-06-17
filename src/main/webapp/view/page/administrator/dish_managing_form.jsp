<%--@elvariable id="dish" type="com.epam.cafe.entitie.Dish"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 04.05.2019
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Managing form</title>
    <link href="view/style/main.css" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1>Dish managing form</h1>

            <div class="form-area">
                <c:import url="/view/page/administrator/dish_form_fields.jsp"/>

                <button onclick="history.back()">Cancel</button>

                <form action="command" method="post" id="dish-form">
                    <input type="hidden" name="command" value="save_dish_changes">
                    <input type="hidden" name="dishID" value="${dish.ID}">
                    <button type="submit">Save</button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
