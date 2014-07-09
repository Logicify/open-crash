<%@ page import="java.util.ArrayList" %>
<%@ page import="org.opencrash.domain_objects.MobileSystem" %>
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
    <div class="col-sm-3 col-md-3 col-xs-3 col-lg-3 application_add">
        <form method="post" action="/myaccount/applications/add" name="add_new_application">
            <div>
                <input type="text"  name="app_name" value="" class="form-control"  style="margin-bottom: 10px" placeholder="Application name">
                <input type="text"  name="app_version" value="" class="form-control"  style="margin-bottom: 10px" placeholder="Application version">
                <select id="systems" name="mobileSystem">
                    <%ArrayList<MobileSystem> systems = (ArrayList<MobileSystem>) request.getAttribute("mobileSystems");%>
                    <%if(!systems.isEmpty()){
                        for(int i = 0;i<systems.size();i++){%>
                            <option value="<%=systems.get(i).getId()%>"><%=systems.get(i).getName()%></option>
                    <%}}%>
                </select>
                <button type="submit" class="btn btn-default app-Btn">Submit</button>
            </div>
        </form>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>