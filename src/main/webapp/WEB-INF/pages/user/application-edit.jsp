<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.opencrash.domain_objects.Application" %>
<%@include file="../includes/header.jsp"%>

<%
    Application app = (Application) request.getAttribute("application");
    Map<String, String> errorList = (HashMap<String, String>) request.getAttribute("errors");
    if (errorList == null) {
        errorList = new HashMap<String, String>();
    }
%>
<div class="span9" >
    <form action="/myaccount/application/edit/<%=app.getId()%>" method="post">
        <div class="input-group registration">
            <%if(errorList.containsKey("application_name")){%>
            <div class="alert alert-error registration-error"><%=errorList.get("application_name")%></div>
            <%}%>
            <input type="text"  value="<%=request.getAttribute("application_name")== null? app.getName():request.getAttribute("application_name")%>" name="app_name" class="form-control margin-input" style="margin-bottom: 10px" placeholder="Application name">
            <%if(errorList.containsKey("varsion")){%>
            <div class="alert alert-error registration-error"><%=errorList.get("application_version")%></div>
            <%}%>
            <input type="text" value="<%=request.getAttribute("application_version")== null? app.getVersion():request.getAttribute("application_version")%>"  name="app_version" class="form-control margin-input" style="margin-bottom: 10px" placeholder="Application version">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>


<%@include file="../includes/footer.jsp"%>