<%@ page import="org.opencrash.domain_objects.ObtainedException" %>
<%@include file="../includes/header.jsp"%>
<% ObtainedException  exception = (ObtainedException) request.getAttribute("exception");%>
<div>
    <ol class="breadcrumb">
        <li><a href="/">Home</a></li>
        <li><a href="/myaccount">My Account</a></li>
        <li><a href="/myaccount/application/<%=exception.getApplication().getId()%>"><%=exception.getApplication().getName()%></a></li>
        <li><a href="/myaccount/application/<%=exception.getApplication().getId()%>/exception/list/<%=exception.getExceptionClass().getId()%>"><%=exception.getExceptionClass().getException_class()%></a></li>
        <li class="active">View</li>
    </ol>
</div>
<div class="content row" >
    <%@include file="../includes/myaccount/applications.jsp"%>

<div class="col-lg-9">
     <div class="span5">
        <table class="table">
            <tbody>
            <tr>
                <td>Exception class</td>
                <td><%=exception.getExceptionClass().getException_class()%></td>
            </tr>
                <tr>
                    <td>Date</td>
                    <td><%=exception.getCreate_at()%></td>
                </tr>
                <tr>
                    <td>Application</td>
                    <td><%=exception.getApplication().getName()%></td>
                </tr>
                <tr>
                    <td>Gps status</td>
                    <td><%=exception.getGps_on().toString()%></td>
                </tr>
                <tr>
                    <td>Operation system</td>
                    <td><%=exception.getApplication().getMobileSystem().getName()%></td>
                </tr>
                <tr>
                    <td>Os version</td>
                    <td><%=exception.getOsVersion()%></td>
                </tr>
                <tr>
                    <td>Screen height</td>
                    <td><%=exception.getScreen_height()%></td>
                </tr>
                <tr>
                    <td>Screen width</td>
                    <td><%=exception.getScreen_width()%></td>
                </tr>
                <tr>
                    <td>Wifi status</td>
                    <td><%=exception.getWifi_on().toString()%></td>
                </tr>
                <tr>
                    <td>Screen orientation</td>
                    <td><%=exception.getScreen_orientation()%></td>
                </tr>
                <tr>
                    <td>Uid</td>
                    <td><%=exception.getUid()%></td>
                </tr>
                <tr>
                    <td>Device</td>
                    <td><%=exception.getDevice().getName()%></td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="span5">
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-message">
                            Message
                        </a>
                    </h4>
                </div>
                <div id="collapse-message" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <%=exception.getMessage()%>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse-backtrace">
                            Backtrace
                        </a>
                    </h4>
                </div>
                <div id="collapse-backtrace" class="panel-collapse collapse">
                    <div class="panel-body">
                        <%=exception.getBacktrace()%>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
<%@include file="../includes/footer.jsp"%>