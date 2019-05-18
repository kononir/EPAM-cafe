<%--@elvariable id="user" type="com.epam.cafe.entitie.user.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 30.03.2019
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Administrator view</title>
    <link rel="stylesheet" href="view/style/main.css">
</head>
<body>
<c:import url="/view/page/general/top_panel.jsp"/>
<c:import url="/view/page/administrator/left_panel_admin.jsp"/>
<div class="inner-content">
    <h1>Hello, ${user.login}!</h1>
</div>
<c:import url="/view/page/general/footer.jsp"/>
</body>
</html>
