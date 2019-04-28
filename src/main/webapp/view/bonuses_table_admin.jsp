<%--@elvariable id="clientName" type="java.lang.String"--%>
<%--@elvariable id="clientSurname" type="java.lang.String"--%>
<%--@elvariable id="client" type="com.epam.cafe.entitie.user.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 24.04.2019
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Bonuses table</title>
    <link href="view/style/main.css" rel="stylesheet">
</head>
<body>
<c:import url="/view/top_panel.jsp"/>
<c:import url="/view/left_panel_admin.jsp"/>
<h1>Bonuses table of ${clientSurname} ${clientName}</h1>
<div class="table-actions">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Delete bonus</th>
        </tr>
        </thead>
        <tbody>
        <%--@elvariable id="clientBonuses" type="java.util.List<com.epam.cafe.entitie.Bonus>"--%>
        <c:forEach items="${clientBonuses}" var="bonus">
            <tr>
                <td>${bonus.ID}</td>
                <td>${bonus.name}</td>
                <td>${bonus.description}</td>
                <td>
                    <form action="command" method="post">
                        <input type="hidden" name="command" value="delete_bonus">
                        <input type="hidden" name="bonusID" value="${bonus.ID}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
