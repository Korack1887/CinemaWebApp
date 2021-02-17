<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 12.02.2021
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create session for ${day}</h1>
    <form method="post" action="createSession?day=day_of_week=${day}">
<input type="datetime-local" min="${minDate}" value="${minDate}" max="${maxDate}" name="date_get" readonly>
            <select name="film_get">
                <c:forEach var="film" items="${films}">
                    <option value="${film.id}">${film.name}</option>
                </c:forEach>
        </select>
        <input type="submit">
    </form>
</body>
</html>
