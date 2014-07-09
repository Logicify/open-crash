<%--
  Created by IntelliJ IDEA.
  User: Fong
  Date: 20.05.14
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>OpenCrash</title>
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/jquery.dataTables.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/bootstrap-multiselect.css" type="text/css"/>
    <link rel="stylesheet" href="/css/pickmeup.css" type="text/css"/>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/js/custom.js"></script>
    <script type="text/javascript" src="/js/jquery.twbsPagination.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-xs-12 col-lg-12">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Open Crash</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/">Open Crash</a>
                    </div>
                    <div class="pull-right">
                        <ul class="nav navbar-nav">
                            <%if(request.getAttribute("user").equals("logged-out")){%>
                            <li>
                                <a href="#" id="dropdown-login" onclick="menuShow()">LOGIN</a>
                                <ul class="dropdown-menu-login login_form text-center" role="menu">
                                    <div id="login_form">
                                        <form action="/logged-in" method="post">
                                            <div class="input-group login-inputs">
                                                <input type="text"  name="email" class="form-control"  style="margin-bottom: 10px" placeholder="Email">
                                                <input type="password" name="password" class="form-control" style="margin-bottom: 10px" placeholder="Password">
                                         </div>
                                            <button type="submit" class="btn btn-default login-btn">Login</button>
                                        </form>
                                    </div>
                                </ul>
                            </li>
                            <li><a href="/registration">SIGNUP</a></li>
                            <%}else{%>
                            <li><a href="/myaccount">My account</a></li>
                            <li><a href="/log-out">Log out</a></li>
                            <%}%>
                        </ul>
                    </div>
                </div><!-- /.container-fluid -->
            </nav>
        </div>
    </div>
