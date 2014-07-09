
<footer id="footer">
    <div class="links">
        <!-- <a href="http://news.bootswatch.com">Blog</a>
        <a href="http://feeds.feedburner.com/bootswatch">RSS</a>
        <a href="https://twitter.com/thomashpark">Twitter</a> -->
    </div>
</footer>
</div
<div class="single"></div>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="/js/bootstrap-multiselect.js"></script>
<script src="/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="/js/jquery.pickmeup.js"></script>
<script type="text/javascript" src="/js/jquery.twbsPagination.js"></script>
<script type="text/javascript" src="/js/custom.js"></script>
<script src="/js/filter.js"></script>
<script src="/js/jquery.dataTables.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#to_date').pickmeup({
            position		: 'right',
            hide_on_select	: true
        });
        $('#datepicker_date').pickmeup({
            position		: 'right',
            hide_on_select	: true
        });111
        $('.multiselect').multiselect({
            includeSelectAllOption: true,
            maxHeight: 200,
            buttonWidth:150,
            checkboxName:"class"
        });
        $('.multiselect_device').multiselect({
            includeSelectAllOption: true,
            maxHeight: 200,
            buttonWidth:150,
            checkboxName:"device"
        });
        $('.multiselect_application').multiselect({
            includeSelectAllOption: true,
            maxHeight: 200,
            buttonWidth:150,
            checkboxName:"device"
        });
        $('.selectpicker').selectpicker({
        });
        $('.selectpicker_group').selectpicker();

        function menuShow(){
            var menu =$(".dropdown-menu-login");
            menu.show({duration: 0, queue: true});
            setTimeout('setCheck()', 2000);
        }
        function setCheck() {
            var obj = $(".showed");
            if(obj.hasClass("hover"))
                setTimeout(setCheck, 500);
            else
                obj.removeClass("showed");
        }
        $("ul").hover(
                function() {
                    $(this).addClass( "hover" );
                }, function() {
                    $(this).removeClass( "hover" );
                }
        );
        $(document).ready(function() {
            $('.dropdown-toggle.date').dropdown();
            $('.dropdown-menu .date').find('form').click(function ( e) {
                e.stopPropagation();
            });
        });
        $(".filter").click(
                function(){
                    var obj = $(this);
                    var ulObject =obj.parent().children("ul");
                    $(".showed").removeClass("showed");
                    ulObject.addClass("showed");
                    setTimeout(setCheck,1000);
                });
        $( "#operation" ).change(function() {
            var data =$("#operation").val();
            if(data == "from_to"){
                $("input[name='date']").attr("placeholder","from");
                $("#to_date").removeClass("hidden");
            }else{
                $("input[name='date']").attr("placeholder","date");
                $("#to_date").addClass("hidden")
            };
        });

    });
</script>
<script>
    function menuShow(){
        var menu =$(".dropdown-menu-login");
        menu.show({duration: 0, queue: true});
        setTimeout('setCheck()', 2000);
    }

    $(".dropdown-menu-login").hover(
            function() {
                $(this).addClass( "hover" );
            }, function() {
                $(this).removeClass( "hover" );
            }
    );
    function setCheck(){
        var menu = $(".dropdown-menu-login");
        if(menu.hasClass("hover"))
            setTimeout('setCheck()', 500);
        else
            menu.hide();
    }


</script>
</body>
</html>
