<%@ page import="org.opencrash.domain_objects.Application" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="../includes/header.jsp"%>


<div class="span9" >

    <div class="tabbable tabs-left span3">
        <div id="myAccordion" class="accordion">
            <%
                ArrayList<Application> applications = (ArrayList<Application>) request.getAttribute("apps");
                if(!applications.isEmpty()){
                    for (int i = 0; i < applications.size(); i++) {
            %>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a href="#collapseApp-<%=applications.get(i).getId()%>" data-parent="#myAccordion" onclick="return false" class="accordion-toggle app_menu"><%=applications.get(i).getName()%></a>
                    </div>
                    <div class="accordion-body collapse" id="collapseApp-<%=applications.get(i).getId()%>">
                        <div class="accordion-inner">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="/myaccount/application/edit/<%=applications.get(i).getId()%>">Edit</a></li>
                                <li><a href="/myaccount/application/delete/<%=applications.get(i).getId()%>">Delete</a></li>
                                <li>
                                    <a  href="#myModal-<%=applications.get(i).getId()%>"  data-toggle="modal">Get Url</a>
                                    <div id="myModal-<%=applications.get(i).getId()%>" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                                            <h3 id="myModalLabel">Application key</h3>
                                        </div>
                                        <div class="modal-body">
                                            <p><%=applications.get(i).getApplication_key()%></p>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            <%}}%>
        </div>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>