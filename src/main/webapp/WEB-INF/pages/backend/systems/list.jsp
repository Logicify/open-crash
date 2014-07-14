<%@ page import="org.opencrash.domain_objects.MobileSystem" %>
<%@ page import="java.util.List" %>
<%@include file="../../includes/header.jsp"%>
<style type="text/css">
</style>
<div class="content row" >
    <%@include file="../settings.jsp"%>
    <div class="col-sm-12 col-md-12 col-xs-12 col-lg-5 left">
        <div class="panel-group admin-systems" id="accordion">
            <div class="btn-group">
                <button type="button" onclick="location.href ='/backend/settings/systems/add'"  class="btn btn-default">Add new</button>
            </div>
            <% List<MobileSystem> systems =(List<MobileSystem>) request.getAttribute("systems");
        if(!systems.isEmpty()){
            for(int i=0;i<systems.size();i++){%>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a>
                            <%=systems.get(i).getName()%>
                        </a>
                        <a href="/backend/settings/systems/edit/<%=systems.get(i).getId()%>" class="admin-edit"><span class="glyphicon glyphicon-pencil"></span></a>
                        <a href="/backend/settings/systems/delete/<%=systems.get(i).getId()%>" class=" admin-delete"><span class="glyphicon glyphicon-remove"></span></a>

                    </h4>
                </div>
            </div>
        <%}}%>
        </div>

    </div>
</div>
<%@include file="../../includes/footer.jsp"%>
