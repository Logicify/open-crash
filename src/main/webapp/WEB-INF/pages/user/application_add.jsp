<%@include file="../includes/header.jsp"%>
    <form method="post" action="/myaccount/applications/add" name="add_new_application">
        <div>
            <input type="text"  name="app_name" value="" class="form-control"  style="margin-bottom: 10px" placeholder="Application name">
            <input type="text"  name="app_version" value="" class="form-control"  style="margin-bottom: 10px" placeholder="Application version">
            <select name="mobileSystem">
                <option value="1">Android</option>
                <option value="2">iOs</option>
            </select>
            <button type="submit">Add</button>
        </div>
    </form>
<%@include file="../includes/footer.jsp"%>