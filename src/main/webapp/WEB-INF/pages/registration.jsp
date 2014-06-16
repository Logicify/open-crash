<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@include file="includes/header.jsp"%>
<%Map<String, String> errorList = (HashMap<String, String>) request.getAttribute("errors");
    if (errorList == null) {
        errorList = new HashMap<String, String>();
    }
%>
<div class="span4" id="registration">
    <form action="/registration" method="post">
        <div class="input-group registration">
            <%if(errorList.containsKey("username")){%>
                <div class="alert alert-error registration-error"><%=errorList.get("username")%></div>
            <%}%>
            <input type="text"  value="${username}" name="username" class="form-control margin-input" style="margin-bottom: 10px" placeholder="Username">
            <%if(errorList.containsKey("e-mail")){%>
            <div class="alert alert-error registration-error"><%=errorList.get("e-mail")%></div>
            <%}%>
            <input type="text" value="${email}"  name="email" class="form-control margin-input" style="margin-bottom: 10px" placeholder="Email">
            <%if(errorList.containsKey("password")){%>
            <div class="alert alert-error registration-error"><%=errorList.get("password")%></div>
            <%}%>
            <input type="password" name="password" class="form-control margin-input" style="margin-bottom: 10px" placeholder="Password">
            <input type="password" name="check_password" class="form-control" style="margin-bottom: 10px" placeholder="Confirm password">
        </div>

        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
<%@include file="includes/footer.jsp"%>