<%@ page import="org.opencrash.domain_objects.Application" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Fong
  Date: 15.05.14
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <div id="applications">

        <ul>
            <%
                ArrayList<Application> applications = (ArrayList<Application>) request.getAttribute("apps");
                if(!applications.isEmpty()){
                for (int i = 0; i < applications.size(); i++) {
            %>
            <li><a href="#"><%=applications.get(i).getName()%></a></li>
            <%}}%>
            <input type="button" value="add_app">
        </ul>
    </div>
    <div id="application">

    </div>
</div>
</body>
</html>
