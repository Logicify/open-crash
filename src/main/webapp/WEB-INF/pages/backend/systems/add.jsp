<%@ page import="org.opencrash.domain_objects.MobileSystem" %>
<%@ page import="java.util.List" %>
<%@include file="../../includes/header.jsp"%>
<style type="text/css">
</style>
<div class="content row" >
    <%@include file="../settings.jsp"%>
    <div class="col-sm-12 col-md-12 col-xs-12 col-lg-5 left">
        <form action="/backend/settings/systems/add" method="post">
            <div class="input-group login-inputs login_form">
                <input  name="systemName" class="form-control"  style="margin-bottom: 10px" placeholder="Name">
            </div>
            <button type="submit" class="btn btn-default">Save</button>
        </form>
    </div>
</div>
<%@include file="../../includes/footer.jsp"%>
