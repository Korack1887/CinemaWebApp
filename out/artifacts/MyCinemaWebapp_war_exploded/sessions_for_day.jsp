<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 11.02.2021
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="s" items="${sessions}">
    ${s.dateTime}<br>
   <a href="seats?session_id=${s.id}">${s.film.name}</a> <br>
</c:forEach>
<p>
    <a href="/createSession">Create new session</a>
</p>
</body>
</html>
