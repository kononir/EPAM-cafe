<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="dish" type="com.epam.cafe.entitie.Dish"--%>
<%--@declare id="dish-form"--%>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 17.06.2019
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dish form fields</title>
</head>
<body>
<div class="form-area-field">
    <label for="dish-name">Name</label>
    <input type="text" id="dish-name" name="dishName" value="${dish.name}"
           form="dish-form" required><br>
</div>
<div class="form-area-field">
    <label for="dish-cost">Cost($)</label>
    <input type="number" id="dish-cost" name="dishCost" value="${dish.cost}"
           form="dish-form" required><br>
</div>
<div class="form-area-field">
    <label for="dish-description">Description</label>
    <textarea id="dish-description" name="dishDescription"
              form="dish-form">${dish.description}</textarea><br>
</div>
<div class="form-area-field checkbox-field">
    <label for="in-menu">Is in client menu</label>
    <c:choose>
        <c:when test="${dish.inMenu}">
            <input type="checkbox" id="in-menu" name="dishIsInMenu" value="true"
                   form="dish-form" checked><br>
        </c:when>
        <c:otherwise>
            <input type="checkbox" id="in-menu" name="dishIsInMenu" value="true"
                   form="dish-form"><br>
        </c:otherwise>
    </c:choose>
</div>
<div class="form-area-field">
    <label for="dish-image-href">Image reference</label>
    <input type="url" id="dish-image-href" name="dishImageHref" value="${dish.imageHref}"
           form="dish-form"><br>
</div>
</body>
</html>
