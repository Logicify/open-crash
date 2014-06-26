<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.opencrash.domain_objects.ObtainedException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.sun.java_cup.internal.runtime.virtual_parse_stack" %>
<%@include file="../includes/header.jsp"%>


<div class="span9" >
    <%@include file="../includes/myaccount/applications.jsp"%>
    <div class="span6">
        <table class="table">
            <thead>
            <tr>
                <td>Exception</td>
                <td>Count</td>
            </tr>
            </thead>
            <tbody>
            <% List<ObtainedException> list =(List<ObtainedException>) request.getAttribute("top_exceptions");
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    Object[] row = (Object[])iterator.next();
                %>
                <tr>
                    <td><a href="/myaccount/application/<%=request.getAttribute("applicationsId")%>/exception/list/<%=row[1]%>"><%=row[2]%></a></td>
                    <td><%=row[0]%></td>
                </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>
