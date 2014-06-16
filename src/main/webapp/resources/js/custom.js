$(".app_menu").click(function(){
    var href = $(this).attr("href");
    if ( $(this).hasClass("menu_active") ) {
        $(href).animate({height:0}, 0);
        $(".app_menu").removeClass("menu_active");
    }else{
        $(href).animate({
            height: 120
        }, 0);
        $(".app_menu").addClass("menu_active");
    }
});