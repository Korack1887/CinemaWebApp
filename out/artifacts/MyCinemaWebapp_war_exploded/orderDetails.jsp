<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 16.02.2021
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="tickets" items="${sessionScope.tickets}">
    <h3>${tickets}</h3><br>
</c:forEach>
<form action="makeOrder" method="post">
    <input type="submit">
</form>
</body>
</html>
