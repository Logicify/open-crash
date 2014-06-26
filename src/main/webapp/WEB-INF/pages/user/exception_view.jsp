<%@ page import="org.opencrash.domain_objects.ObtainedException" %>
<%@include file="../includes/header.jsp"%>
<div class="span9">
    <% ObtainedException  exception = (ObtainedException) request.getAttribute("exception");%>
    <div class="span3"><%=exception.getExceptionClass().getException_class()%></div>
    <div class="span5"><%=exception.getMessage()%></div>
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
    <div class="span7">
        <%=exception.getBacktrace()%>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>