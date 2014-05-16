<%@ page import="java.util.ArrayList" %>
<%@ page import="org.opencrash.domain_objects.Obtained_exception" %>
<%--
  Created by IntelliJ IDEA.
  User: Fong
  Date: 16.05.14
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>


<%
    ArrayList<Obtained_exception> obtained_exceptions = (ArrayList<Obtained_exception>) request.getAttribute("exceptions");
    if(obtained_exceptions != null){
        for (int i = 0; i < obtained_exceptions.size(); i++) {
%>
<tr>
    <td><%=obtained_exceptions.get(i).getCreate_at()%></td>
    <td><%=obtained_exceptions.get(i).getMessage()%></td>
    <td><%=obtained_exceptions.get(i).getBacktrace()%></td>
</tr>
<%}}%>
</table>
</body>
</html>
