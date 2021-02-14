<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 14.02.2021
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="booked" value="${booked_seats}"/>
<c:forEach var="column" items="${seats}">
    <c:forEach var="seat" items="${column.seats}">
<c:choose>
<c:when test="${fn:containsIgnoreCase(booked, seat)}">
0..
</c:when>
    <c:otherwise>
        1..
        </c:otherwise>
    </c:choose>
</c:forEach>
    <br>
</c:forEach>
</body>
</html>
