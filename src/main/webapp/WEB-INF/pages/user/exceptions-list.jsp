<%@ page import="java.util.ArrayList" %>
<%@ page import="org.opencrash.domain_objects.ObtainedException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.opencrash.mvc.Interceptor" %>
<%@include file="../includes/header.jsp"%>


<div class="span9" >
    <%@include file="../includes/myaccount/applications.jsp"%>
    <div class="span6">
        <table class="table">
            <thead>
            <tr>
                <td>Date</td>
                <td>Message</td>
                <td>Backtrace</td>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<ObtainedException> obtained_exceptions = (ArrayList<ObtainedException>) request.getAttribute("exceptions");
                if(obtained_exceptions != null){
                    for (int i = 0; i < obtained_exceptions.size(); i++) {
            %>
            <tr>
                <td><%=obtained_exceptions.get(i).getCreate_at()%></td>
                <td><%=obtained_exceptions.get(i).getMessage()%></td>
                <td><%=obtained_exceptions.get(i).getBacktrace()%></td>
            </tr>
            <%}}%>
            </tbody>
        </table>
            <%
            Integer count =(Integer) request.getAttribute("count");
            Integer current_page = (Integer) request.getAttribute("page");
            Integer prev_page = current_page-1;
            Integer next_page = current_page+1;
            if(count > 10){%>
                <ul class="pagination">
                    <li <%if(current_page.equals(1)){%>class="disabled"<%}%>><a href="<%if(prev_page<1){%>#<%}else{%>page-<%=prev_page%><%}%>">&laquo;</a></li>
                    <li <%if(current_page.equals(count)){%>class="disabled"<%}%>><a href="<%if(next_page>count){%>#<%}else{%>page-<%=next_page%><%}%>">&raquo;</a></li>
                </ul>
            <%}%>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>