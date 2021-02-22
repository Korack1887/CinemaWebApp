<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 11.02.2021
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="priv" method="post">
    <input name="date_get" type="datetime-local">
    <select name="film_get" required>
        <c:forEach var="film" items="${films}">
            <option value="${film.id}">${film.name}</option>
        </c:forEach>
    </select>
    <input type="submit">
</form>
</body>
</html>
