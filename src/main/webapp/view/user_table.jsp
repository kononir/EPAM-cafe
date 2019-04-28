<%--@elvariable id="error" type="java.lang.String"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 13.04.2019
  Time: 6:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Table of clients</title>
    <link href="view/style/main.css" rel="stylesheet">
</head>
<body>
<c:import url="/view/top_panel.jsp"/>
<c:import url="/view/left_panel_admin.jsp"/>
<div class="inner-content">
    <h1>Clients table</h1>
    <div class="table-actions">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Login</th>
                <th><label for="score">Score number</label></th>
                <th><label for="banned">Banned</label></th>
                <th>Show bonuses</th>
                <th>Save changes</th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="clients" type="java.util.List<com.epam.cafe.entitie.user.User>"--%>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td>${client.name}</td>
                    <td>${client.surname}</td>
                    <td>${client.login}</td>
                    <td>
                        <input type="number" id="score" name="clientScore"
                               value="${client.score}" form="save-changes-form">
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${client.banned}">
                                <input type="checkbox" id="banned" value="true" name="clientIsBanned"
                                       form="save-changes-form" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="banned" value="true" name="clientIsBanned"
                                       form="save-changes-form">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="command" method="post">
                            <input type="hidden" name="command" value="get_bonuses">
                            <input type="hidden" name="clientID" value="${client.ID}">
                            <input type="hidden" name="clientName" value="${client.name}">
                            <input type="hidden" name="clientSurname" value="${client.surname}">
                            <button type="submit">Show</button>
                        </form>
                    </td>
                    <td>
                        <form action="command" method="post" id="save-changes-form">
                            <input type="hidden" name="command" value="save_client_changes">
                            <input type="hidden" name="clientID" value="${client.ID}">
                            <button type="submit">Save</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
