<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 13.02.2021
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form enctype="multipart/form-data" action="create_film" method="post">
        <h3>director</h3>
        <select name="director_film">
            <c:forEach var="director" items="${directors}">
                <option value="${director.id}">${director.name}</option>
            </c:forEach>
        </select><br>
        <h3>Description</h3>
        <input type="text" name="description"><br>
        <h3>DescriptionUa</h3>
        <input type="text" name="descriptionUa"><br>
        <h3>film_namer</h3>
        <input type="text" name="film_name"><br>
        <h3>film_nameUa</h3>
        <input type="text"  name="film_nameUa"><br>
        <h3>duration</h3>
        <input type="number" name="duration"><br>
        <h3>release</h3>
        <input type="datetime-local" name="release"><br>
        <h3>film_pic</h3>
        <input type="file" name="film_pic" accept="image/jpeg"><br>
        <h3>genre_film</h3>
        <select name="genre_film" multiple>
            <c:forEach var="genre" items="${genres}">
                <option value="${genre.id}">${genre.name}</option>
            </c:forEach>
        </select>

        <input type="submit">
    </form>
</body>
</html>
