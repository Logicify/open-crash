var table_check  =false;
$(".app_menu").click(function(){
    var href = $(this).attr("href");
    if ( $(this).hasClass("menu_active") ) {
        $(href).animate({height:0}, 0);
        $(".app_menu").removeClass("menu_active");
    }else{
        var obj=$(href +" > div > ul");
        $(href).animate({
            height: obj.height()+20
        }, 0);
        $(".app_menu").addClass("menu_active");
    }
});
$("#form_submit").click(function() {
    if(table_check == false){
        var t =tableInitiation(true);
        table_check = true;
    }else
        var t =tableInitiation(false);
    getData(0,t,true);
});
function getData(page,t,check){
    var filter_cbx = $("input[name='filter[]']");
    var group_cbx = $("input[name='group[]']");
    var returnData = {};
    var filters = [];
    var group_by = [];
    var j=0;
    for (var i = 0; i < filter_cbx.length; i++) {
        if (filter_cbx[i].checked) {
            var filter_obj = {};
            var cbx_value = filter_cbx[i].getAttribute("value");
            if(cbx_value == "exceptionClass"){
                filter_obj.filter = cbx_value;
                var selectedValues = [];
                $("#exceptionClass :selected").each(function(){
                    selectedValues.push($(this).val());
                });
                filter_obj.value = selectedValues;
            }else if (cbx_value == "date"){
                if($("#operation").find(":selected").val() == "from_to"){
                    filter_obj.filter = cbx_value;
                    filter_obj.operation = "from_to"
                    filter_obj.from = $("input[name='date']").val();
                    filter_obj.to = $("#to_date").val();
                }else{
                    filter_obj.filter = cbx_value;
                    filter_obj.operation = $("#operation").find(":selected").val();
                    filter_obj.date = $("input[name='date']").val();
                }
            }else if (cbx_value =="user"){
                filter_obj.filter =cbx_value;
                var selectedValues = [];
                $("#user :selected").each(function(){
                    selectedValues.push($(this).val());
                });
                filter_obj.user =selectedValues;
            }else if (cbx_value =="application"){
                filter_obj.filter =cbx_value;
                var selectedValues = [];
                $("#application :selected").each(function(){
                    selectedValues.push($(this).val());
                });
                filter_obj.application =selectedValues;
            }
            filters[j] = filter_obj;
            j++;
        }
    }
    j=0;
    for(var i=0;i<group_cbx.length;i++){
        if (group_cbx[i].checked) {
            group_by[j] = group_cbx[i].getAttribute("value");
            j++;
        }
    }
    returnData.filters = filters;
    returnData.group = group_by;
    var  post_data = "json="+JSON.stringify(returnData)+"&page="+page;
    $.ajax({
        url:"/filter",
        type:"POST",
        data: post_data,
        cache:false,
        success:function( data ) {
            var objects = JSON.parse(data)
            var table = $('#filter_table');
            if(table.hasClass("hidden"))
                table.removeClass("hidden");
            buildTable(objects,t,check);
        }
    })

}
function buildTable(objects,t,check){
    t
        .clear()
        .draw();
    for(var i=1;i<objects.length;i++){
        t.row.add( [
            objects[i].date,
            "<a href='/myaccount/application/exception/view/"+objects[i].id+"'>"+objects[i].exceptionClass+"</a>",
            "<a href='/myaccount/application/"+objects[i].application_id+"'>"+objects[i].application+"</a>",
            objects[i].message
        ] ).draw();
    }
    if(check == true)
        paginationInit(objects[0]);
}
function tableInitiation(check){
    if(check==true){
        var t = $('#filter_table').DataTable({
            "bPaginate": false,
            "bFilter": false,
            "bInfo": false,
            "bSort": false
        });
        return t;
    }else{
        var t = $('#filter_table').DataTable();
        return t;
    }
}
function getPage(page){
    var t =tableInitiation(false);
    getData(page,t,false);
}
function paginationInit(total_pages){
    $('#pagination').twbsPagination({
        totalPages: total_pages,
        visiblePages: 3,
        href:"javascript:getPage({{number}})",
        onPageClick: function (event, page) {
            $('#page-content').text('Page ' + page);
        }
    });
}