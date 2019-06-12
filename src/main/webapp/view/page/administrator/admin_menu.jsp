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
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1>Menu managing</h1>
            <div class="table-actions">
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Cost</th>
                        <th>Image href</th>
                        <th><label for="in-menu">Is in menu</label></th>
                        <th>Save changes</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="allDishes" type="java.util.List<com.epam.cafe.entitie.Dish>"--%>
                    <c:forEach items="${allDishes}" var="dish">
                        <tr>
                            <td>${dish.name}</td>
                            <td>${dish.description}</td>
                            <td>${dish.cost}</td>
                            <td>${dish.imageHref}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${dish.inMenu}">
                                        <input type="checkbox" id="in-menu" name="dishInMenu" value="true"
                                               form="save-changes-form" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" id="in-menu" name="dishInMenu" value="true"
                                               form="save-changes-form">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="command" method="post" id="save-changes-form">
                                    <input type="hidden" name="command" value="save_client_changes">
                                    <input type="hidden" name="dishID" value="${dish.ID}">
                                    <button type="submit">Save</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
