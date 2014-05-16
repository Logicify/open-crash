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
<form action="/login" method="POST" name="login_form">
    <label>Email:</label>
    <input name="email" type="email">
    <label>Password:</label>
    <input type="password" name="password">
    <button type="submit">Submit</button>
</form>
</body>
</html>
