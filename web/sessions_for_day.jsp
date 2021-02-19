<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 11.02.2021
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="assets/css/main.css"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="main">
<h3>Sort by: |<a href="/sessions_for_day?day_of_week=${day}&sort_content=">Date</a>|
    |<a href="/sessions_for_day?day_of_week=${day}&sort_content=seat">Seats</a>|
    |<a href="/sessions_for_day?day_of_week=${day}&sort_content=film">Films</a>|<br></h3>


    <div class="thumbnails">
<c:choose>
    <c:when test="${sessionScope.sort_content eq 'film'}">
        <c:forEach var="film" items="${sessions}">
            <div class="box">
            ${film.dateTime}<br>
            <a href="seats?session_id=${film.id}">${film.film.name}</a> <br>
            <img src="/images/${film.film.image}"><br>
            </div>
        </c:forEach>
    </c:when>
    <c:when test="${sessionScope.sort_content eq 'seat'}">
        <c:forEach var="sessionSeat" items="${sessionsSeat}">
    <div class="box">
            ${sessionSeat.key.dateTime}||Available seats: ${sessionSeat.value}<br>
            <a href="seats?session_id=${sessionSeat.key.id}">${sessionSeat.key.film.name}</a> <br>
            <img src="/images/${sessionSeat.key.film.image}"><br>
    </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:forEach var="s" items="${sessions}">
                <div class="box">
            ${s.dateTime}<br>
            <a href="seats?session_id=${s.id}">${s.film.name}</a> <br>
            <img src="/images/${s.film.image}"><br>
                </div>
        </c:forEach>
    </c:otherwise>
</c:choose>
</div>
<p>
    <a href="/createSession">Create new session</a>
</p>
    <a href="/hello">Back</a>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery.scrolly.min.js"></script>
<script src="assets/js/jquery.poptrox.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>
</body>
</html>
