<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@include file="../includes/header.jsp"%>

<%Map<String, String> errorList = (HashMap<String, String>) request.getAttribute("errors");
    if (errorList == null) {
        errorList = new HashMap<String, String>();
    }
%>
<div class="double-border"></div>
<div class="content row">
    <div class="col-sm-3 col-md-3 col-xs-3 col-lg-3 login text-center">
        <form action="/backend/log-in" method="post">
            <div class="input-group login-inputs login_form">
                <%if(errorList.containsKey("username")){%>
                <div class="alert alert-error registration-error"><p class="alert-danger"><%=errorList.get("e-mail")%></p></div>
                <%}%>
                <input type="text"  name="username" value="${username}" class="form-control"  style="margin-bottom: 10px" placeholder="Email">
                <%if(errorList.containsKey("password")){%>
                <div class="alert alert-error registration-error"><p class="alert-danger"><%=errorList.get("password")%></p></div>
                <%}%>
                <input type="password" name="password" class="form-control" style="margin-bottom: 10px" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-default">Login</button>
        </form>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>