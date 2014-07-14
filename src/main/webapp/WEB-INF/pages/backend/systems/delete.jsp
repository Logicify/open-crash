<%@ page import="org.opencrash.domain_objects.MobileSystem" %>
<%@ page import="java.util.List" %>
<%@include file="../../includes/header.jsp"%>
<style type="text/css">
</style>
<div class="content row" >
    <%@include file="../settings.jsp"%>
    <div class="col-sm-12 col-md-12 col-xs-12 col-lg-5 left">
        <% MobileSystem system = (MobileSystem) request.getAttribute("system");%>
        <form action="/backend/settings/systems/delete/<%=system.getId()%>" method="post">
            <div class="input-group login-inputs login_form">
                <div class="panel panel-default">
                    <div class="panel-heading">Confirm delete</div>
                    <div class="panel-body">
                        <div class="btn-group">
                            <button type="submit" class="btn btn-default">Delete</button>
                            <button type="button" onclick="location.href ='/backend/settings/systems'"  class="btn btn-default">Back</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="../../includes/footer.jsp"%>
