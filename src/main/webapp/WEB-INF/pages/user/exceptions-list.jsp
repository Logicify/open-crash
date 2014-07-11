<%@ page import="java.util.ArrayList" %>
<%@ page import="org.opencrash.domain_objects.ObtainedException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.opencrash.mvc.Interceptor" %>
<%@include file="../includes/header.jsp"%>
<% ArrayList<ObtainedException> obtained_exceptions = (ArrayList<ObtainedException>) request.getAttribute("exceptions");
ObtainedException breadcrumb = obtained_exceptions.get(0);%>
    <div>
    <ol class="breadcrumb">
        <li><a href="/">Home</a></li>
        <li><a href="/myaccount">My Account</a></li>
        <li><a href="/myaccount/application/<%=breadcrumb.getApplication().getId()%>"><%=breadcrumb.getApplication().getName()%></a></li>
        <li class="active"><%=breadcrumb.getExceptionClass().getException_class()%></li>
    </ol>
</div>
<div class="content row" >
    <%@include file="../includes/myaccount/applications.jsp"%>
    <div class="col-sm-12 col-md-12 col-xs-12 col-lg-9 left">
        <table class="table">
            <thead>
            <tr>
                <td class="sorting_desc" sort="date" style="width: 11%;">Date</td>
                <td class="sorting" sort="class" >Class</td>
                <td class="sorting" sort="message">Message</td>
            </tr>
            </thead>
            <tbody>
            <%
                if(obtained_exceptions != null){
                    for (int i = 0; i < obtained_exceptions.size(); i++) {
            %>
                <tr>
                    <td><%=obtained_exceptions.get(i).getCreate_at()%></td>
                    <td><a href="/myaccount/application/exception/view/<%=obtained_exceptions.get(1).getId()%>"><%=obtained_exceptions.get(i).getExceptionClass().getException_class()%></a></td>
                    <td><%=obtained_exceptions.get(i).getMessage()%></td>
                </tr>
            <%}}%>
            </tbody>
        </table>
            <%
            Integer count =(Integer) request.getAttribute("count");
            Integer current_page = (Integer) request.getAttribute("page");
            %>
            <div class="pagination text-center">
                <ul id="pagination" class="pagination-sm pagination"></ul>
            </div>
    </div>
</div>
<script>
    pagination(<%=count%>,<%=current_page%>,"<%=request.getAttribute("url").toString()%>");
    order();
</script>
<%@include file="../includes/footer.jsp"%>