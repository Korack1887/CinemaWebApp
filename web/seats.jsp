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
<h1>${sessionScope.ses_role}</h1>
<c:set var="booked" value="${booked_seats}"/>
<c:set var="ses_id" value="${session_id}" scope="session"></c:set>
<c:set var="text" value="Session{id=${ses_id}"></c:set>
<c:forEach var="ticks" items="${sessionScope.tickets}">
    <c:choose>
        <c:when test="${ses_id==ticks.session.id}">
            <c:set var="smth" value="${smth}${ticks}"></c:set>
        </c:when>
    </c:choose>
</c:forEach>
<c:forEach var="column" items="${seats}">
    <c:forEach var="seat" items="${column.seats}">
<c:choose>
<c:when test="${fn:containsIgnoreCase(booked, seat)||(fn:contains(smth, seat)&&fn:contains(smth, text))}">
0..
</c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${not empty sessionScope.ses_rol}">
        <a href="/addToCart?id_seat=${seat.id}&id_col=${column.id}&ses_id=${session_id}">1..</a>
            </c:when>
            <c:otherwise>
                <a href="/login">1..</a>
            </c:otherwise>
        </c:choose>
        </c:otherwise>
    </c:choose>
</c:forEach>
    <br>
</c:forEach>
</body>
</html>
