<%--@elvariable id="exception" type="java.lang.Exception"--%>
<%@ taglib prefix="e" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
<p>
    Exception:<br>
    ${exception}
</p>
<p>
    Message:<br>
    ${exception.message}
</p>
<p>
    Stack trace:<br>
    <c:forEach items="${exception.stackTrace}" var="stackElement">
        ${stackElement.toString()}<br>
    </c:forEach>
</p>
</body>
</html>
