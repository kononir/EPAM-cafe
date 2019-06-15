<%--@elvariable id="exception" type="java.lang.Exception"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 05.04.2019
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
    <link href="view/style/main.css" rel="stylesheet">
</head>
<body>
<div class="page-wrapper">
    <div class="menu-and-content-wrapper">
        <c:import url="/view/page/general/top_panel.jsp"/>
        <c:import url="/view/page/client/left_panel_client.jsp"/>
        <div class="inner-content">
            <div class="centered">
                <h1>${exception.message}</h1>
                <button onclick="history.back()">Back to previous page</button>
            </div>
        </div>
    </div>
    <c:import url="/view/page/general/footer.jsp"/>
</div>
</body>
</html>
