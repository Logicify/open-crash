<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.opencrash.domain_objects.ObtainedException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.sun.java_cup.internal.runtime.virtual_parse_stack" %>
<%@include file="../includes/header.jsp"%>

<div>
    <ol class="breadcrumb">
        <li><a href="/">Home</a></li>
        <li><a href="/myaccount">My Account</a></li>
        <li>Application</li>
    </ol>
</div>
<div class="content row" >
    <%@include file="../includes/myaccount/applications.jsp"%>
    <div class="col-sm-3 col-md-3 col-xs-3 col-lg-3 left">
        <table class="table">
            <thead>
            <tr>
                <td class="sorting" sort="class">Exception</td>
                <td class="sorting_desc" sort="count">Count</td>
            </tr>
            </thead>
            <tbody>
            <% List<ObtainedException> list =(List<ObtainedException>) request.getAttribute("top_exceptions");
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    Object[] row = (Object[])iterator.next();
                %>
                <tr>
                    <td><a href="/myaccount/application/<%=request.getAttribute("applicationId")%>/exception/list/<%=row[1]%>"><%=row[2]%></a></td>
                    <td><%=row[0]%></td>
                </tr>
            <%}%>
            </tbody>
        </table>
        <div class="pagination text-center">
            <ul id="pagination" class="pagination-sm pagination"></ul>
        </div>
    </div>
</div>
<script>
    pagination(<%=request.getAttribute("count")%>,<%=request.getAttribute("page")%>,"<%=request.getAttribute("url")%>");
    order();
</script>
<%@include file="../includes/footer.jsp"%>
