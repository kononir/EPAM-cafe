<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="exception" type="java.lang.Exception"--%>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 15.06.2019
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
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
