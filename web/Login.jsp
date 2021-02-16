<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 14.02.2021
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="login">
    <h3>Name</h3>
    <input type="text" name="name">
    <h3>Email</h3>
    <input type="email" name="email">
    <h3>Password</h3>
    <input type="password" name="pass">
    <h3>Birthday</h3>
    <input type="datetime-local" name="birthday">
    <h3>Role</h3>
    <select name="role">
        <option value="USER" selected>User</option>
        <option value="ADMIN">Admin</option>
    </select>
    <h3>Gender</h3>
    <select name="gender">
        <option value="MALE">Male</option>
        <option value="FEMALE">Female</option>
    </select>
    <input type="submit">
    <a href="/sign_in">I already have an account</a>
</form>
</body>
</html>
