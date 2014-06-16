<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@include file="includes/header.jsp"%>
<%Map<String, String> errorList = (HashMap<String, String>) request.getAttribute("errors");
    if (errorList == null) {
        errorList = new HashMap<String, String>();
    }
%>
<div class="span3 login"style="margin: 0 auto;float: none;">
    <form action="/logged-in" method="post">
        <div class="input-group login-inputs login_form">
            <%if(errorList.containsKey("e-mail")){%>
            <div class="alert alert-error registration-error"><%=errorList.get("e-mail")%></div>
            <%}%>
            <input type="text"  name="email" value="${email}" class="form-control"  style="margin-bottom: 10px" placeholder="Email">
            <%if(errorList.containsKey("password")){%>
            <div class="alert alert-error registration-error"><%=errorList.get("password")%></div>
            <%}%>
            <input type="password" name="password" class="form-control" style="margin-bottom: 10px" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-default">Login</button>
    </form>
</div>
<%@include file="includes/footer.jsp"%>