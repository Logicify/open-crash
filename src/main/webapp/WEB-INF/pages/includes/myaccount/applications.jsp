<%@ page import="org.opencrash.domain_objects.Application" %>
<%@ page import="java.util.ArrayList" %>

    <div class="col-sm-12 col-md-12 col-xs-12 col-lg-3 left">
        <div class="btn-group">
            <button type="button" onclick="location.href ='/myaccount/applications/add'" class="btn btn-default">New Application</button>
            <button type="button" onclick="location.href ='/myaccount/filter'"  class="btn btn-default">Filters</button>
        </div>
        <div class="panel-group" id="accordion">
        <%
            ArrayList<Application> applications = (ArrayList<Application>) request.getAttribute("apps");
            if(!applications.isEmpty()){
                for (int i = 0; i < applications.size(); i++) {
        %>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-<%=applications.get(i).getId()%>">
                        <%=applications.get(i).getName()%>
                    </a>
                </h4>
            </div>
            <div id="collapse-<%=applications.get(i).getId()%>" class="panel-collapse collapse">
                <div class="panel-body">
                    <ul class="nav nav-pills nav-stacked">
                        <li><a href="/myaccount/application/<%=applications.get(i).getId()%>">Exceptions</a></li>
                        <li><a href="/myaccount/application/edit/<%=applications.get(i).getId()%>">Edit</a></li>
                        <li><a href="/myaccount/application/delete/<%=applications.get(i).getId()%>">Delete</a></li>
                        <li>
                            <a data-toggle="modal" data-target="#myModal-<%=applications.get(i).getId()%>">
                                Get Key
                            </a>

                            <div class="modal fade" id="myModal-<%=applications.get(i).getId()%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                            <h4 class="modal-title" id="myModalLabel">Application Key</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p><%=applications.get(i).getApplication_key()%></p>
                                        </div>
                                    </div>
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