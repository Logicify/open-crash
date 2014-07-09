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
function order(){
    $(document).ready(function(){
        if(location.search.split('sorting_type=') != ""){
            var type = location.search.split('sorting_type=')[1];
            var field = location.search.split('sorting_field=')[1];
            type = type.slice(0, type.indexOf("&"));
            var obj = $("td[sort='"+field+"']");
            $("td[class^='sorting']").each(function(){
                $(this).removeClass("sorting_desc");
                $(this).removeClass("sorting_asc");
                $(this).addClass("sorting");
            })
            obj.addClass("sorting_"+type);
        }
    });
    $("td[class^='sorting']").click(function(){
        var obj = $(this);
        var class_name ;
        field=$(this).attr("sort");
        var type="";
        if(location.search.split('sorting_type=') != ""){
            t = location.search.split('sorting_type=')[1];
            class_name = "sorting_"+t.slice(0, t.indexOf("&")) ;
        }else{
            class_name  = $(this).attr("class");
        }
        if(class_name == "sorting_desc"){
            obj.removeClass("sorting");
            obj.addClass("sorting_asc");
            type="asc";
        }else if(class_name == "sorting_asc"){
            obj.removeClass("sorting");
            obj.addClass("sorting_desc");
            type="desc";
        }else if(class_name == "sorting"){
            obj.removeClass("sorting");
            obj.addClass("sorting_desc");
            type="desc";
        }
        location.href="?sorting_type="+type+"&sorting_field="+field;
    });
}
function pagination(total,current,url){
    var get ="";
    if(location.search.split('sorting_type=') != ""){
        get = "?sorting_type="+location.search.split('sorting_type=')[1];
    }
    $('#pagination').twbsPagination({
        totalPages: total,
        startPage:current,
        visiblePages: 3,
        href:url+"/page-{{number}}"+get,
        onPageClick: function (event, page) {
            $('#page-content').text('Page ' + page);
        }
    });
}