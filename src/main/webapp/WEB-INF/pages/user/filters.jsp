<%@ page import="org.opencrash.domain_objects.Application" %>
<%@ page import="org.opencrash.domain_objects.ObtainedException" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="org.opencrash.domain_objects.Device" %>
<%@ page import="org.opencrash.domain_objects.ExceptionClass" %>
<%@include file="../includes/header.jsp"%>
<div>
    <ol class="breadcrumb">
        <li><a href="/">Home</a></li>
        <li><a href="/myaccount">My Account</a></li>
        <li class="active">Filters</li>
    </ol>
</div>
<%
    List<ObtainedException> classes =  (List<ObtainedException>)request.getAttribute("exceptionClasses");
    List<Device> devices  =  (List<Device>)request.getAttribute("devices");
    List<Application> applications =  (List<Application>)request.getAttribute("applications_for_filter");
%>
<div class="col-xs-12">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Filter</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Filter</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle filter">Exception Class<span class="caret"></span></a>
                    <ul class="dropdown-menu filter" role="menu">
                        <li>
                            <select id="exceptionClass" class="multiselect" multiple="multiple">
                                <%Iterator iterator = classes.iterator();
                                    while (iterator.hasNext()) {
                                        Object[] row = (Object[])iterator.next();
                                %>
                                <option value="<%=row[1]%>"><%=row[2]%></option>
                                <%}%>
                            </select>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle date filter" data-toggle="dropdown" href="#">
                        Date <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu date" role="menu">
                        <form>
                            <li class="text-center">
                               <span>
                                   <select id="operation">
                                       <option value="from">from</option>
                                       <option value="to">to</option>
                                       <option value="from_to">from-to</option>
                                       <option value="eq">Equals</option>
                                   </select>
                               </span>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <span>
                                    <input name="date" id="datepicker_date" placeholder="date" class="form-control data-input filter" ype="text">
                                </span>

                                <span>
                                    <input class="hidden data-input form-control filter" type="text" id="to_date" placeholder = "to" name="to">
                                </span>
                            </li>
                        </form>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle filter">Device<span class="caret"></span></a>
                    <ul class="dropdown-menu filter" role="menu">
                        <li>
                            <select id="device" name="device" class="multiselect_device" multiple="multiple">
                                <%for(int i=0;i<devices.size();i++){%>
                                <option value="<%=devices.get(i).getId()%>"><%=devices.get(i).getName()%></option>
                                <%}%>
                            </select>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle filter">Application<span class="caret"></span></a>
                    <ul class="dropdown-menu filter" role="menu">
                        <li>
                            <select  id="application" name="application" class="multiselect_application" multiple="multiple">
                                <%for(int i=0;i<applications.size();i++){%>
                                <option value="<%=applications.get(i).getId()%>"><%=applications.get(i).getName()%></option>
                                <%}%>
                            </select>
                        </li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <button type="button" id="form_submit" class="btn btn-default">Submit</button>
                <button type="button" id="form_reset" class="btn btn-default">Reset</button>
            </form>
            <ul class="nav navbar-nav p_right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Grouping<span class="caret"></span></a>
                    <ul class="dropdown-menu filter grouping_right" role="menu">
                        <li>
                            <select id="grouping" class="multiselect" multiple="multiple">
                                <option value="class">Exception Class</option>
                                <option value="date">Date</option>
                                <option value="application">Application</option>
                                <option value="device">Device</option>
                            </select>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="col-xs-12">
    <div class="col-xs-7 filter_t">
        <table id="filter_table" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th class="sorting_desc filter_sort" sort="date">Date</th>
                <th class="sorting filter_sort" sort="class" style="max-width: 200px">Exception Class</th>
                <th class="sorting filter_sort" sort="application">Application</th>
                <th class="sorting filter_sort" sort="message">Message</th>
            </tr>
            </thead>
        </table>
        <div class="pagination text-center">
            <ul id="pagination_t" class="pagination-sm pagination"></ul>
        </div>
    </div>
    <div class="col-lg-5">
    <div class="filter_t_group">
        <table id="filter_table_group" class="display" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th class="sorting group_sort" sort="g_class">Exception Class</th>
                    <th class="sorting group_sort" sort="g_date">Date</th>
                    <th class="sorting group_sort" sort="g_application">Application</th>
                    <th class="sorting group_sort" sort="g_device">Device</th>
                    <th class="sorting_desc group_sort" sort="count">Count</th>
                </tr>
            </thead>
        </table>
        <div class="panel panel-default group-panel">
            <div class="panel-heading">Grouping by:<span class='group'></span></div>
        </div>
        </div>
        <div class="pagination pagination-center">
            <ul id="pagination_ft" class="pagination-sm pagination"></ul>
        </div>
    </div>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>