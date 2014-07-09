<%@ page import="org.opencrash.domain_objects.ObtainedException" %>
<%@include file="../includes/header.jsp"%>
<div>
    <ol class="breadcrumb">
        <li><a href="/">Home</a></li>
        <li><a href="/myaccount">My Account</a></li>
        <li>Application</li>
    </ol>
</div>
<div class="content row" >
    <%@include file="../includes/myaccount/applications.jsp"%>

<div class="col-lg-9">
    <% ObtainedException  exception = (ObtainedException) request.getAttribute("exception");%>
    <div class="col-lg-9 text-center"><h1><%=exception.getExceptionClass().getException_class()%></h1></div>
    <div class="span5">
        <table class="table">
            <thead>
                <th>Parameter</th>
                <th>Value</th>
            </thead>
            <tbody>
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
            </tbody>
        </table>
    </div>
    <div class="span5">
        <div class="bs-example bs-example-tabs">
            <ul id="myTab" class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#message" role="tab" data-toggle="tab">Message</a></li>
                <li class=""><a href="#backtrace" role="tab" data-toggle="tab">Backtrace</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade active in" id="message">
                    <%=exception.getMessage()%>
                </div>
                <div class="tab-pane fade" id="backtrace">
                    <%=exception.getBacktrace()%>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
<%@include file="../includes/footer.jsp"%>