<%@include file="../includes/header.jsp"%>
<div class="double-border"></div>
<div class="content row">
    <%@include file="settings.jsp"%>
    <div class="col-sm-12 col-md-12 col-xs-12 col-lg-9 right">
        <%if(request.getAttribute("success")!=null){%>
        <div class="alert alert-success" role="alert">The operation was completed successfully</div>
        <%}%>
        <form action="/backend/settings/save" method="post">
            <div class="input-group login-inputs login_form">
                <input  name="elements" value="<%=request.getAttribute("pagination")%>" class="form-control small" style="margin-bottom: 10px">
                <button type="submit" class="btn btn-default">Save</button>
            </div>
        </form>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>
