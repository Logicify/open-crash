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