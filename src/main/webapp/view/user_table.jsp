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
    <h1>Clients table</h1>
    <div class="table-actions">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Login</th>
                <th><label for="score">Score number</label></th>
                <th><label for="banned">Banned</label></th>
                <th>Show bonuses</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <td colspan="7">
                    <form action="command" method="post">
                        <input type="hidden" name="command" value="save_changes">
                        <button type="submit">Save changes</button>
                    </form>
                </td>
            </tr>
            </tfoot>
            <tbody>
            <%--@elvariable id="clients" type="java.util.List"--%>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td>${client.getID()}</td>
                    <td>${client.getName()}</td>
                    <td>${client.getSurname()}</td>
                    <td>${client.getLogin()}</td>
                    <td>
                        <input type="number" id="score" value="${client.getScore()}">
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${client.getBanned()}">
                                <input type="checkbox" id="banned" checked>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="banned">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="command" method="post">
                            <input type="hidden" name="command" value="get_bonuses">
                            <button type="submit">Show</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
