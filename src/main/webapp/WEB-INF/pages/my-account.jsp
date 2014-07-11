<!DOCTYPE html>
<html>
<head lang="en">
    <link rel="stylesheet" type="text/css" href="src/main/webapp/resources/css/extend.css"/>
    <meta charset="UTF-8">
    <title>Open Crash</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/jquery.dataTables.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Open Crash</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#">Open Crash</a>
                        </div>
                        <div class="pull-right">
                            <ul class="nav navbar-nav">
                                <li>
                                    <a href="#" id="dropdown-login" onclick="menuShow()">LOGIN</a>
                                </li>
                                <li><a href="/registration">SIGNUP</a></li>
                            </ul>
                        </div>
                        </div><!-- /.container-fluid -->
                </nav>
            </div>
            <div>
                <ol class="breadcrumb">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">My Account</a></li>
                    <li class="active">Filters</li>
                </ol>
            </div>
            <div class="col-xs-12">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Filter</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#">Filter</a>
                        </div>
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle filter">Exception Class<span class="caret"></span></a>
                                <ul class="dropdown-menu filter" role="menu">
                                    <li>
                                        <select id="exceptionClass" class="multiselect" multiple="multiple">
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                        </select>
                                    </li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle filter">Date<span class="caret"></span></a>
                                <ul class="dropdown-menu filter" role="menu" >
                                    <li>
                                       <span>
                                           <select id="operation">
                                               <option value="from">from</option>
                                               <option value="to">to</option>
                                               <option value="from_to">from-to</option>
                                               <option value="eq">Eq</option>
                                           </select>
                                       </span>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <span>
                                            <input name="date" id="datepicker_date" placeholder="date" class="form-control data-input filter" ype="text">
                                        </span>
                                        <span> <input class="hidden data-input form-control filter" type="text" id="to_date" placeholder = "to" name="to"></span>
                                    </li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle filter">Device<span class="caret"></span></a>
                                <ul class="dropdown-menu filter" role="menu">
                                    <li>
                                        <select id="device" name="device" class="multiselect_device" multiple="multiple">
                                            <option value="1">Device 1</option>
                                            <option value="2">Device 2</option>
                                        </select>
                                    </li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle filter">Application<span class="caret"></span></a>
                                <ul class="dropdown-menu filter" role="menu">
                                    <li>
                                        <select  id="application" name="application" class="multiselect_application" multiple="multiple">
                                            <option value="1">Application 2</option>
                                            <option value="2">Application 1</option>
                                        </select>
                                    </li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Grouping<span class="caret"></span></a>
                                <ul class="dropdown-menu filter" role="menu">
                                    <li>
                                        <select id="grouping" class="multiselect" multiple="multiple">
                                            <option value="class">Exception Class</option>
                                            <option value="date">Date</option>
                                            <option value="application">Application</option>
                                            <option value="device">Device</option>
                                        </select>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <form class="navbar-form navbar-left" role="search">
                            <button type="button" id="form_submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </nav>
            </div>
            <div class="col-xs-12">
                <div class="col-xs-7 filter_t">
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
                        <ul id="pagination_t" class="pagination-sm pagination"></ul>
                    </div>
                </div>
                <div class="col-xs-5 filter_t_group" style="float:right">
                    <table id="filter_table_group" class="display" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>Group by</th>
                            <th class="sorting" sort="g_class">Exception Class</th>
                            <th class="sorting_desc" sort="count">Count</th>
                        </tr>
                        </thead>
                    </table>
                    <div class="pagination text-center">
                        <ul id="pagination_ft" class="pagination-sm pagination"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-multiselect.js"></script>
<script src="js/bootstrap-select.min.js"></script>
<script src="js/filter.js"></script>
<script src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="/js/jquery.twbsPagination.js"></script>

    <script type="text/javascript">
    $(document).ready(function() {
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
</body>
</html>