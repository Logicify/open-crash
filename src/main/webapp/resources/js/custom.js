var table_check  =false;
var sorting_field;
var sorting_type = "desc";
var page=1;
var t;
var t1;
var pagination;

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
        t=tableInitiation(true);
        t1=tableWithGroupInitiation(true);
        table_check = true;
    }else
        t =tableInitiation(false);
    getData(0,t,true,"date","desc");
});
function getData(page,t,check,sorting_field,sorting_type){
    var filter_cbx = $("input[name='filter[]']");
    var group_cbx = $("input[name='group[]']");
    var returnData = {};
    var filters = [];
    var group_by = [];
    var j=0;
    var grouped = false;
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
            }else if (cbx_value =="device"){
                filter_obj.filter =cbx_value;
                var selectedValues = [];
                $("#device :selected").each(function(){
                    selectedValues.push($(this).val());
                });
                filter_obj.device =selectedValues;
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
            grouped = true;
            j++;
        }
    }
    returnData.filters = filters;
    returnData.group = group_by;
    if(grouped&&check==true){
        sorting_field="count";
    }
    else if (grouped == false && check == true)
        sorting_field = "date";

    var  post_data = "json="+JSON.stringify(returnData)+"&page="+page+"&sorting_field="+sorting_field+"&sorting_type="+sorting_type;
    if(grouped){
        var table = $('.filter_t');
        if($(".filter_t_group").hasClass("hidden"))
            $(".filter_t_group").removeClass("hidden")
        if(!table.hasClass("hidden"))
            table.addClass("hidden");
        $.ajax({
            url:"/filter",
            type:"POST",
            data: post_data,
            cache:false,
            success:function( data ) {
                var objects = JSON.parse(data);
                buildGroupedTable(objects,t1,check);
            }
        })
    }else{
        var table = $('.filter_t');
        if(table.hasClass("hidden"))
            table.removeClass("hidden");
        if(!$(".filter_t_group").hasClass("hidden"))
            $(".filter_t_group").addClass("hidden")
        $.ajax({
            url:"/filter",
            type:"POST",
            data: post_data,
            cache:false,
            success:function( data ) {
                var objects = JSON.parse(data);
                buildTable(objects,t,check);
            }
        })
    }

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
    if(check==true)
        paginationInit(objects[0],true);
    else
        paginationInit(objects[0],false);
}
function buildGroupedTable(objects,check){
    t1
        .clear()
        .draw();
    for(var i=1;i<objects.length;i++){
        var filter_obj ={};
        filter_obj.date = objects[i].date;
        filter_obj.id = objects[i].id;
        filter_obj.deviceId = objects[i].deviceId;
        filter_obj.appId = objects[i].appId;
        t1.row.add([
            "<span class='group'></span>",
            "<a id='exceptionId"+i+"' onclick='setFilter("+i+")'date='"+filter_obj.date+"' deviceId ='"+filter_obj.deviceId+"'appId='"+filter_obj.appId+"' classId="+objects[i].id+">"+objects[i].exceptionClass+"</a>",
            objects[i].count
        ]).draw();

    }
    groupToTable();
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
function tableWithGroupInitiation(check){
    if(check==true){
        var t = $('#filter_table_group').DataTable({
            "bPaginate": false,
            "bFilter": false,
            "bInfo": false,
            "bSort": false
        });
        return t;
    }else{
        var t = $('#filter_table_group').DataTable();
        return t;
    }
}
function getPage(p){
    page=p;
    var t =tableInitiation(false);
    getData(page,t,false,sorting_field,sorting_type);
}
function paginationInit(total_pages,check){
    $('#pagination').resetPaging();
    $('#pagination').twbsPagination({
        totalPages: total_pages,
        visiblePages: 3,
        href:"javascript:getPage({{number}})",
        onPageClick: function (event, page) {
            $('#page-content').text('Page ' + page);
        }
    });

}
$("th[class^='sorting']").click(function(){
    var obj = $(this);
    sorting_field=obj.attr("sort");
    console.log(sorting_field);
    var class_name = $(this).attr("class");
    $("th[class^='sorting']").each(function(){
        $(this).removeClass("sorting_desc");
        $(this).removeClass("sorting_asc");
        $(this).addClass("sorting");
    })
    if(class_name == "sorting_disabled sorting_desc"){
        $(this).removeClass("sorting");
        $(this).addClass("sorting_asc");
        sorting_type="asc";
    }else{
        $(this).removeClass("sorting");
        $(this).addClass("sorting_desc");
        sorting_type="desc";
    }
    var t =tableInitiation(false);
    getData(page,t,false,obj.attr("sort"),sorting_type);
});

function setFilter(obj_id){
    var group_cbx = $("input[name='group[]']");
    var j=0;
    var grouped=[];
    var object =$("a[id='exceptionId"+obj_id+"']");
    var appId = object.attr("appId");
    var date =object.attr("date");
    var id =object.attr("classId");
    var deviceId =object.attr("deviceId");
      console.log(obj_id,appId);
    for(var i=0;i<group_cbx.length;i++){
        if (group_cbx[i].checked) {
            grouped[j]=group_cbx[i].getAttribute("value");
        }
        j++;
    }
    filerReset();

    for(var i=0;i<grouped.length;i++){
        var val =grouped[i] ;
        if(val == "exceptionClass"){
            $(".filter_cbx[name='filter[]'][value='exceptionClass']").trigger('click');
            $("input[name='class'][value="+id+"]").trigger('click');
        }
        if(val=="date"){
            $(".filter_cbx[name='filter[]'][value='date']").trigger('click');
            $("input[name='date']").attr("value",date.replace('-','/').replace('-','/'));
            $("#operation").find("option[value='eq']").attr("selected",true)
        }
        if(val=="application"){
            $(".filter_cbx[name='filter[]'][value='application']").trigger('click');
            $("input[name='application'][value="+appId+"]").trigger('click');
        }
        if(val=="device"){
            $(".filter_cbx[name='filter[]'][value='device']").trigger('click');
            $("input[name='device'][value="+deviceId+"]").trigger('click');
        }
    }

    $("#form_submit").trigger("click");

}
function filerReset(){
    $("input:checkbox").attr('checked', false);
    $("li[class='active']").removeClass("active");
    $("input[class='filter']").attr("value","");
    $("#exceptionClass").find("option").attr("selected", false);
}
function groupToTable(){
    var objects = $("span[class='group']").parent().attr("rowspan",4);
    for(var i=1;i<objects.length;i++){
        objects[i].remove();
    }
    var group_cbx = $("input[name='group[]']");
    var group_by;
    for(var i=0;i<group_cbx.length;i++){
        if (group_cbx[i].checked) {
            if(i==0)
                group_by =group_cbx[i].getAttribute("value");
            else
                group_by = group_by+','+group_cbx[i].getAttribute("value");
        }
    }
    $("span[class='group']").text(group_by);
}