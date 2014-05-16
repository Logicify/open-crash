<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.opencrash.domain_objects.Obtained_exception" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%--
  Created by IntelliJ IDEA.
  User: Fong
  Date: 15.05.14
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <div id="exception_top">

        <%
            List<Object> top = (List<Object>) request.getAttribute("top_exceptions");
            Iterator iterator = top.iterator();
            while (iterator.hasNext()) {
                Object[] row = (Object[])iterator.next();
            %>
             <ul>
                 <li>
                     <label><a href="/myaccount/application/<%=request.getAttribute("applicationId")%>/exception/list/<%=row[0]%>"><%=row[2]%></a></label>
                     <label><%=row[1]%></label>
                 </li>
             </ul>
            <%}%>
    </div>
</div>
</body>
</html>
