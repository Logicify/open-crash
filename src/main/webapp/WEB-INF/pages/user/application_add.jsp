<%--
  Created by IntelliJ IDEA.
  User: Fong
  Date: 15.05.14
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form method="post" action="/myaccount/applications/add" name="add_new_application">
        <div>
            <label>Application name:</label>
            <input name="app_name">
            <label>Application version:</label>
            <input name="app_version">
            <button type="submit">Add</button>
        </div>
    </form>
</body>
</html>
