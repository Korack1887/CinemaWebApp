<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 11.02.2021
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="d" items="${dates}">
    <a href="/sessions_for_day?day_of_week=${d}">${d}</a> <br>
</c:forEach>
</body>
</html>
