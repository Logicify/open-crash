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
//function filterInit(){
    var filter_query;
    var filter_query_with_grouping;
    var grouping;
    var sorting_field_t1 ="date";
    var sorting_type_t1 = "desc";
    var page_t1 = 1;
    var sorting_field_t2  ="count";
    var sorting_type_t2 = "desc";
    var page_t2 =1;
    var table_init = false;
    var t1_pagination = false;
    var t2_pagination = false;
    var table_group_init = false;
    var group_props=[];
    $('.filter_t_group').hide();
    $('.filter_t').hide();
    $("#form_submit").click(function() {
        grouping = false;
        t1_pagination =false;
        t2_pagination =false;
        buildQuery();
        page_t1 = 1;
        page_t2 = 1;
        tablePosition(grouping,false);
        getData();
    });
    function buildQuery(){
        var returnData = {};
        var filters = [];
        var filter_obj = {};
        var selectedValues = [];
        $("#exceptionClass :selected").each(function () {
            selectedValues.push($(this).val());
        });
        if (selectedValues.length > 0) {
            filter_obj.filter = "exceptionClass";
            filter_obj.value = selectedValues;
            filters.push(filter_obj);
        }
        selectedValues=[];
        $("#device :selected").each(function(){
            selectedValues.push($(this).val());
        });
        if(selectedValues.length >0){
            filter_obj={};
            filter_obj.filter ="device";
            filter_obj.device =selectedValues;
            filters.push(filter_obj);
        }
        selectedValues=[];
        $("#application :selected").each(function(){
            selectedValues.push($(this).val());
        });
        if(selectedValues.length!=0){
            filter_obj={};
            filter_obj.filter ="application";
            filter_obj.application =selectedValues;
            filters.push(filter_obj);
        }
        if($("#operation").find(":selected").val() == "from_to"){
            filter_obj={};
            filter_obj.operation = "from_to"
            filter_obj.from = $("input[name='date']").val();
            filter_obj.to = $("#to_date").val();
            if(filter_obj.from != "" && filter_obj.to != "" ){
                filter_obj.filter = "date";
                filters.push(filter_obj);
            }
        }else{
            if($("input[name='date']").val() != ""){
                filter_obj={};
                filter_obj.operation = $("#operation").find(":selected").val();
                filter_obj.data = $("input[name='date']").val();
                filter_obj.filter = "date";
                filters.push(filter_obj);
            }
        }

        group_by=[];
        $("#grouping :selected").each(function(){
            group_by.push($(this).val());
        });
        group_props = group_by;
        if(group_by.length!=0){
            returnData.filters = filters;
            returnData.group = group_by;
            filter_query_with_grouping =  JSON.stringify(returnData);
            grouping = true;
        }else{
            returnData.filters = filters;
            grouping = false;
            returnData.group = group_by;
            filter_query =  JSON.stringify(returnData);
        }
    }


    function getData(){
        var  post_data;
        if(grouping){
            post_data = "json="+filter_query_with_grouping+"&page="+page_t2+"&sorting_field="+sorting_field_t2+"&sorting_type="+sorting_type_t2;
             $.ajax({
                url:"/filter",
                type:"POST",
                data: post_data,
                cache:false,
                success:function( data ) {
                    buildGroupedTable(JSON.parse(data));
                }
            })
        }else{
            post_data = "json="+filter_query+"&page="+page_t1+"&sorting_field="+sorting_field_t1+"&sorting_type="+sorting_type_t1;
            $.ajax({
                url:"/filter",
                type:"POST",
                data: post_data,
                cache:false,
                success:function( data ) {
                    buildTable(JSON.parse(data));
                }
            })
        }
    }
    function buildTable(objects){
        var table = getTable();
        table
            .clear()
            .draw();
        for(var i=1;i<objects.length;i++){
            table.row.add( [
                objects[i].date,
                    "<a href='/myaccount/application/exception/view/"+objects[i].id+"'>"+objects[i].exceptionClass+"</a>",
                    "<a href='/myaccount/application/"+objects[i].application_id+"'>"+objects[i].application+"</a>",
                objects[i].message
            ] ).draw();
        }

        paginationInit(objects[0]);
    }

    function buildGroupedTable(objects) {
        var table = getGroupedTable();
        table
            .clear()
            .draw();
        if(group_props.indexOf("class")!=-1){
            $("th[class~='group_sort'][sort='g_class']").show();
        }else
            $("th[class~='group_sort'][sort='g_class']").hide();
        if(group_props.indexOf("date")!=-1){
            $("th[class~='group_sort'][sort='g_date']").show();
        }else
            $("th[class~='group_sort'][sort='g_date']").hide();

        if(group_props.indexOf("application")!=-1){
            $("th[class~='group_sort'][sort='g_application']").show()
        }
        else
            $("th[class~='group_sort'][sort='g_application']").hide()
        if(group_props.indexOf("device")!=-1){
            $("th[class~='group_sort'][sort='g_device']").show()
        }
        else
            $("th[class~='group_sort'][sort='g_device']").hide()

        for(var i=1;i<objects.length;i++){
            var filter_obj ={};
            filter_obj.date = objects[i].date;
            filter_obj.id = objects[i].id;
            filter_obj.deviceId = objects[i].deviceId;
            filter_obj.appId = objects[i].appId;
            data_for_table =[];
            if(group_props.indexOf("class")!=-1){
                data_for_table.push("<a id='exceptionId"+i+"' onclick='setFilter("+i+")'date='"+filter_obj.date+"' deviceId ='"+filter_obj.deviceId+"'appId='"+filter_obj.appId+"' classId="+objects[i].classId+">"+objects[i].className+"</a>");
            }else
                data_for_table.push("null");
            if(group_props.indexOf("date")!=-1){
                data_for_table.push("<a id='exceptionId"+i+"' onclick='setFilter("+i+")' date='"+filter_obj.date+"' deviceId ='"+filter_obj.deviceId+"'appId='"+filter_obj.appId+"' classId="+objects[i].classId+">"+objects[i].date+"</a>");
            }else
                data_for_table.push("null");
            if(group_props.indexOf("application")!=-1){
                data_for_table.push("<a id='exceptionId"+i+"' onclick='setFilter("+i+")'date='"+filter_obj.date+"' deviceId ='"+filter_obj.deviceId+"'appId='"+filter_obj.appId+"' classId="+objects[i].classId+">"+objects[i].appName+"</a>");
            }else
                data_for_table.push("null");
            if(group_props.indexOf("device")!=-1){
                data_for_table.push("<a id='exceptionId"+i+"' onclick='setFilter("+i+")'date='"+filter_obj.date+"' deviceId ='"+filter_obj.deviceId+"'appId='"+filter_obj.appId+"' classId="+objects[i].classId+">"+objects[i].deviceName+"</a>");
            }else
                data_for_table.push("null");
            data_for_table.push(objects[i].count);
            table.row.add(data_for_table).draw();
        }
        $("#filter_table_group").find("td:contains('null')").each(function(){
            $(this).hide();
        })
        paginationFTInit(objects[0]);
        propToTable();
    }
    function getTable(){
        if(table_init == false){
            var t = $('#filter_table').DataTable({
                "bPaginate": false,
                "bFilter": false,
                "bInfo": false,
                "bSort": false
            });
            table_init = true;
            return t;
        }else{
            var t = $('#filter_table').DataTable();
            return t;
        }
    }
    function getGroupedTable() {
        if(table_group_init == false){
            var t = $('#filter_table_group').DataTable({
                "bPaginate": false,
                "bFilter": false,
                "bInfo": false,
                "bSort": false
            });
            table_group_init = true;
            return t;
        }else{
            var t = $('#filter_table_group').DataTable();
            return t;
        }
    }

    $("th[class~='filter_sort']").click(function(){
        var obj = $(this);
        var sort=obj.attr("sort");
        var class_name = $(this).attr("class");
        class_name = class_name.split(" ");
        var sort_class = class_name[0];
        if(["sorting_desc","sorting_asc","sorting"].indexOf(sort_class) == -1)
            sort_class = class_name[2];
        grouping =false;
        $("th[class~='filter_sort']").each(function(){
            $(this).removeClass("sorting_desc");
            $(this).removeClass("sorting_asc");
            $(this).addClass("sorting");
        })

        if(sort_class == "sorting_desc"){
            $(this).removeClass("sorting");
            $(this).addClass("sorting_asc");
            sorting_field_t1 = sort;
            sorting_type_t1="asc";
        }else{
            $(this).removeClass("sorting");
            $(this).addClass("sorting_desc");
            sorting_field_t1 = sort;
            sorting_type_t1="desc";
        }
        getData();
    });
    $("th[class~='group_sort']").click(function(){
        var obj = $(this);
        var sort=obj.attr("sort");
        var class_name = $(this).attr("class");
        class_name = class_name.split(" ");
        var sort_class = class_name[0];
        if(["sorting_desc","sorting_asc","sorting"].indexOf(sort_class) == -1)
            sort_class = class_name[2];
        grouping =true;
        $("th[class~='group_sort']").each(function(){
            $(this).removeClass("sorting_desc");
            $(this).removeClass("sorting_asc");
            $(this).addClass("sorting");
        })

        if(sort_class == "sorting_desc"){
            $(this).removeClass("sorting");
            $(this).addClass("sorting_asc");
            sorting_field_t2 = sort;
            sorting_type_t2="asc";
        }else{
            $(this).removeClass("sorting");
            $(this).addClass("sorting_desc");
                sorting_field_t2 = sort;
                sorting_type_t2="desc";
        }
        getData();
    });
 function tablePosition(group,check){
        if(group&&!check){
            if($('.filter_t_group').parent().show().addClass("modal_filter")){
                $('.modal_filter').removeAttr("class").addClass("col-lg-7");
                //$('.filter_t_group').parent().removeClass("modal_filter")
            }
            $('.filter_t').hide().animate({height:"0px"},0);
            $('.filter_t_group').show();
            $('.filter_t_group').parent().animate({width: "100%"}, 500);
        }
        else if(!group&&!check){
            $('.filter_t_group').parent().hide();
            $('.filter_t').show().animate({width: "100%"}, 500);
        }
        else if(group&&check){
            $(".filter_t").show();
            var size = (group_props.length+1)*15;
            if(size==75)
                size = 60;
            $('.modal_filter').addClass("modal-filter-size-"+size);
            $(function () {
                $(".filter_t_group").parent().animate({
                    width:size+"%"
                }, { duration: 700}).addClass("modal-filter-size");
                $(".filter_t").animate({
                    width: '100%'
                }, { duration: 700 });
            });
        }
    }


function setFilter(obj_id){
    var group_by=[];
    $("#grouping :selected").each(function(){
        group_by.push($(this).val());
    });
    buildQueryByGroup(obj_id,group_by);
    grouping = false;
    t1_pagination = false;
    page_t1 = 1;
    var t2 = $(".filter_t_group").parent();
    t2.addClass("modal_filter");
    t2.removeClass("col-lg-12");
    t2.addClass("col-lg-5");
    tablePosition(true,true);
    t2.removeAttr("style");
    getData(false);
}

function buildQueryByGroup(obj_id,group_by){
    var object =$("a[id='exceptionId"+obj_id+"']");
    var appId = object.attr("appId");
    var date =object.attr("date");
    var id =object.attr("classId");
    var deviceId =object.attr("deviceId");
    var return_data = {};
    var filter_obj={};
    var filters=[];

    if(group_by.indexOf("class")!= -1){
        filter_obj.filter = "exceptionClass";
        filter_obj.value = [id];
        filters.push(filter_obj);
    }

    if(group_by.indexOf("date")!= -1){
        filter_obj={};
        filter_obj.operation ="eq";
        filter_obj.date = date.replace("-","/").replace("-","/");
        filter_obj.filter = "date";
        filters.push(filter_obj);
    }
    if(group_by.indexOf("application")!= -1){
        filter_obj={};
        filter_obj.filter ="application";
        filter_obj.application =[appId];
        filters.push(filter_obj);
    }
    if(group_by.indexOf("device")!= -1){
        filter_obj={};
        filter_obj.filter ="device";
        filter_obj.device =[deviceId];
        filters.push(filter_obj);
    }
    filter_obj={};
    return_data.filters = filters;
    return_data.group = [];
    filter_query = JSON.stringify(return_data);
}

function filerReset(){
    $("input:checkbox").attr('checked', false);
    $("li[class='active']").removeClass("active");
    $("li[class='dropdown active']").removeClass("active");
    $("#grouping :selected").each(function(){
        $(this).prop("selected", false);;
    });
    $("#to_date").val("");
    $("#datepicker_date").val("");
    $("#operation").val("from").trigger('click');
    $("input[name='date']").attr("placeholder","date");
    $("#to_date").addClass("hidden");
}

function paginationInit(total_pages){
    if(t1_pagination == false){
        $('#pagination_t').resetPaging();
        t1_pagination = true;
    }
    $('#pagination_t').twbsPagination({
        totalPages: total_pages,
        visiblePages: 3,
        href:"javascript:getPage({{number}})",
        onPageClick: function (event, page) {
            $('#page-content').text('Page ' + page);
        }
    });

}

function paginationFTInit(total_pages){
    if(t2_pagination == false){
        $('#pagination_ft').resetPaging();
        t2_pagination = true;
    }
    $('#pagination_ft').twbsPagination({
        totalPages: total_pages,
        visiblePages: 3,
        href:"javascript:getPageGroup({{number}})",
        onPageClick: function (event, page) {
            $('#page-content').text('Page ' + page);
        }
    });

}
function getPage(page){
   page_t1 = page;
   grouping = false;
   table_init = true;
   getData();
}

function getPageGroup(page){
    page_t2 = page;
    grouping =true;
    table_group_init_init = true;
    getData();
}

function propToTable(){
    var span=$("span[class='group']");
    var text;
    for(var i=0;i<group_props.length;i++){
        var prop='';
        if(group_props[i] == "class")
            prop="Exception Class";
        if(group_props[i] == "date")
            prop="Date";
        if(group_props[i] == "application")
            prop="Application";
        if(group_props[i] == "device")
            prop="Device";
        if(i==0)
            text = prop;
        else
            text = text+"."+prop;
    }
    $("span[class='group']").text(text);
}
$("#form_reset").click(function(){
  filerReset();
})
