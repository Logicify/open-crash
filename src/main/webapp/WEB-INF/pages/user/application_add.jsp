<%@ page import="java.util.ArrayList" %>
<%@ page import="org.opencrash.domain_objects.MobileSystem" %>
<%@include file="../includes/header.jsp"%>
    <form method="post" action="/myaccount/applications/add" name="add_new_application">
        <div>
            <input type="text"  name="app_name" value="" class="form-control"  style="margin-bottom: 10px" placeholder="Application name">
            <input type="text"  name="app_version" value="" class="form-control"  style="margin-bottom: 10px" placeholder="Application version">
            <select name="mobileSystem">
                <%ArrayList<MobileSystem> systems = (ArrayList<MobileSystem>) request.getAttribute("mobileSystems");%>
                <%if(!systems.isEmpty()){
                    for(int i = 0;i<systems.size();i++){%>
                        <option value="<%=systems.get(i).getId()%>"><%=systems.get(i).getName()%></option>
                <%}}%>
            </select>
            <button type="submit">Add</button>
        </div>
    </form>
<%@include file="../includes/footer.jsp"%>