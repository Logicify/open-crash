<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.opencrash.domain_objects.Application" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.opencrash.domain_objects.MobileSystem" %>
<%@ page import="com.opencrash.mvc.Interceptor" %>
<%@include file="../includes/header.jsp"%>

<div>
    <ol class="breadcrumb">
        <li><a href="/">Home</a></li>
        <li><a href="/myaccount">My Account</a></li>
        <li class="/myaccount/applications/add">Add Application</li>
    </ol>
</div>
<div class="content row">
    <%@include file="../includes/myaccount/applications.jsp"%>

<%
    Application app = (Application) request.getAttribute("application");
    Map<String, String> errorList = (HashMap<String, String>) request.getAttribute("errors");
    if (errorList == null) {
        errorList = new HashMap<String, String>();
    }
%>
<div class="col-sm-3 col-md-3 col-xs-3 col-lg-3 application_add" >
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
            <select id="systems" name="mobileSystem">
                <%ArrayList<MobileSystem> systems = (ArrayList<MobileSystem>) request.getAttribute("systems");
                Integer mobileSystem_id = app.getMobileSystem().getId();%>
                <%if(!systems.isEmpty()){
                    for(int i = 0;i<systems.size();i++){%>
                        <% Integer id = systems.get(i).getId();%>
                        <option value="<%=systems.get(i).getId()%>"<%if(mobileSystem_id.equals(id)){%>selected="selected"<%}%>><%=systems.get(i).getName()%></option>
                <%}}%>
            </select>
            <button type="submit" class="btn btn-default app-Btn">Submit</button>
        </div>
    </form>
</div>
</div>


<%@include file="../includes/footer.jsp"%>