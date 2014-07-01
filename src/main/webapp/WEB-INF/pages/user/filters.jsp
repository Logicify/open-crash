<%@ page import="org.opencrash.domain_objects.Application" %>
<%@ page import="org.opencrash.domain_objects.ObtainedException" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@include file="../includes/header.jsp"%>
<style>
    .filter_cbx {
        margin-left: 40%;
    }
</style>
<div class="span12">
<div class="span4">
    <form method="post">
        <table>
            <thead>
            <tr>
                <th>
                    Parameter
                </th>
                <th>
                    Filter by
                </th>
                <th class="filter_value">
                    Filter value
                </th>
                <th>
                    Group by
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Exception Class</td>
                <td><input type="checkbox" class="filter_cbx" name="filter[]" value="exceptionClass"></td>
                <td>
                    <select id ="exceptionClass" class="multiselect" multiple="multiple">
                        <% List<ObtainedException> list =(List<ObtainedException>) request.getAttribute("exceptionClasses");
                            Iterator iterator = list.iterator();
                            while (iterator.hasNext()) {
                                Object[] row = (Object[])iterator.next();
                        %>
                        <option value="<%=row[1]%>"><%=row[2]%></option>
                        <%}%>
                    </select>
                </td>
                <td><input class="filter_cbx" type="checkbox" name="group[]" value="exceptionClass"></td>
            </tr>
            <tr>
                <td>Date</td>
                <td>
                    <input  class="filter_cbx" type="checkbox" name="filter[]" value="date">
                </td>
                <td>
                    <div id="date">
                        <select id="operation" class="filter">
                            <option value="from">from</option>
                            <option value="to">to</option>
                            <option value="from_to">from-to</option>
                            <option value="eq"></option>
                        </select>
                        <input name="date" id="datepicker_date" class="filter">
                        <input class="hidden filter" id="to_date" name="to">
                    </div>
                </td>
                <td><input class="filter_cbx" type="checkbox" name="group[]" value="date"></td>
            </tr>
            <tr>
                <td>User</td>
                <td><input class="filter_cbx" type="checkbox" name="filter[]" value="user" class="multiselect" multiple="multiple"></td>
                <td>
                    <select id="user" name="user" class="multiselect_users" multiple="multiple">
                        <option value="1" >User 1</option>
                        <option value="2">User 2</option>
                    </select>
                </td>
                <td><input class="filter_cbx" type="checkbox" name="group[]" value="user"></td>
            </tr>
            <tr>
                <td>Application</td>
                <td><input  class="filter_cbx" type="checkbox" name="filter[]" value="application"></td>
                <td>
                    <select  id="application" name="application" class="multiselect" multiple="multiple">
                        <% List<Application> applications = (List<Application>)request.getAttribute("applications_for_filter");
                        for(int i =0;i<applications.size();i++){%>
                          <option value="<%=applications.get(i).getId()%>"><%=applications.get(i).getName()%></option>
                        <%}%>
                    </select>
                </td>
                <td><input class="filter_cbx" type="checkbox" name="group[]" value="application"></td>
            </tr>
            </tbody>
        </table>
        <button type="button" id="form_submit">submit</button>
    </form>
</div>
<div class="span7 filter_t hidden">
    <table id="filter_table" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th class="sorting_desc" sort="date">Date</th>
            <th class="sorting" sort="class">Exception Class</th>
            <th class="sorting" sort="application">Application</th>
            <th class="sorting" sort="message">Message</th>
        </tr>
        </thead>
    </table>
        <div class="pagination text-center">
        <ul id="pagination" class="pagination-sm pagination"></ul>
    </div>
</div>
    <div class="span7 filter_t_group hidden">
        <table id="filter_table_group" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>Group by</th>
                <th class="sorting" sort="class">Exception Class</th>
                <th class="sorting" sort="count">Count</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript">

    $(document).ready(function() {
        $('.multiselect').multiselect({
            includeSelectAllOption: true,
            enableFiltering: true,
            maxHeight: 200,
            buttonWidth:150,
            checkboxName:"class"
        });
    });
    $(document).ready(function() {
        $('.multiselect_users').multiselect({
            includeSelectAllOption: true,
            enableFiltering: true,
            maxHeight: 200,
            buttonWidth:150
        });
    });
    $( "#operation" ).change(function() {
        var data =$("#operation").val();
        if(data == "from_to"){
            $("#to_date").removeClass("hidden");
        }else
            $("#to_date").addClass("hidden");
    });
    $('#datepicker_date').pickmeup({
        position		: 'right',
        hide_on_select	: true
    });
    $('#to_date').pickmeup({
        position		: 'right',
        hide_on_select	: true
    });
</script>
<%@include file="../includes/footer.jsp"%>