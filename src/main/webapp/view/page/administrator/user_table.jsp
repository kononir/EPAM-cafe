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
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/administrator/left_panel_admin.jsp"/>
        <div class="inner-content">
            <h1>Clients table</h1>
            <div class="table-actions">
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Login</th>
                        <th>Score number</th>
                        <th>Is banned</th>
                        <th>Show bonuses</th>
                        <th>Manage information</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="clients" type="java.util.List<com.epam.cafe.entitie.user.User>"--%>
                    <c:forEach items="${clients}" var="client">
                        <tr>
                            <td>${client.name}</td>
                            <td>${client.surname}</td>
                            <td>${client.login}</td>
                            <td>${client.score}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${client.banned}">Yes</c:when>
                                    <c:otherwise>No</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="command" method="post">
                                    <input type="hidden" name="command" value="get_bonuses_admin">
                                    <input type="hidden" name="clientID" value="${client.ID}">
                                    <input type="hidden" name="clientLogin" value="${client.login}">
                                    <button type="submit">Show</button>
                                </form>
                            </td>
                            <td>
                                <form action="command" method="post">
                                    <input type="hidden" name="command" value="manage_client_information">
                                    <input type="hidden" name="clientID" value="${client.ID}">
                                    <button type="submit">Manage</button>
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
