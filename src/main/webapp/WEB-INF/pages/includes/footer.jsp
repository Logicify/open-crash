</div>
<footer id="footer">
    <p class="pull-right"><a href="#top">Back to top</a></p>
    <div class="links">
        <!-- <a href="http://news.bootswatch.com">Blog</a>
        <a href="http://feeds.feedburner.com/bootswatch">RSS</a>
        <a href="https://twitter.com/thomashpark">Twitter</a> -->
    </div>
</footer>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js" type="text/javascript"></script>
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
