<%--
  Created by IntelliJ IDEA.
  User: Fong
  Date: 14.05.14
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/resources/js/bootstrap.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
</head>
<body>
<form action="/registration" method="POST" name="registration_form">
    <label>Username:</label>
    <input name="username" type="text">
    <label>Password:</label>
    <input type="password" name="password">
    <label>Retype password</label>
    <input type="password" name="check_password">
    <label>E-mail:</label>
    <input type="email" name="email">
    <button type="submit">Submit</button>
</form>
</body>
</html>
