<!DOCTYPE html>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<%
if(AuthHelper.isLoggedIn(request.getSession())) response.sendRedirect(request.getContextPath() + "/secure/");
%>
<head>
    <!-- start: Meta -->
    <meta charset="utf-8">
    <title>ATD - Login</title>
    <!-- end: Meta -->
    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- end: Mobile Specific -->
    <!-- start: CSS -->
    <link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
    <link id="base-style" href="css/style.css" rel="stylesheet">
    <link id="base-style-responsive" href="css/style-responsive.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext" rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="css/atd.css" rel="stylesheet">
    <!-- end: CSS -->
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->
    <!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->
    <!-- start: Favicon -->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- end: Favicon -->
    <style type="text/css">
    body {
        background: url(img/bg-login.jpg) !important;
    }
    </style>
</head>

<body>
    <div class="container-fluid-full">
        <div class="row-fluid">
            <div class="row-fuild" id="type-user">
                <div class="login-box">
                	<div class="logo-box">
                		<img src="img/atd.png" alt="logo atd" class="logo" />
                	</div>
                	
                    <h2>Wie ben jij?</h2>
                    
                    <c:if test="${not empty param.error }">
                    <div class="alert alert-danger">
                		<strong>Gebruikersnaam en/of wachtwoord onjuist!</strong>
                	</div>
                	</c:if>
                    
                    <div class="input-prepend">
                        <select id="user-type">
                            <option value="klant">Klant</option>
                            <option value="monteur">Monteur</option>
                            <option value="bedrijfsleider">Bedrijfsleider</option>
                        </select>
                    </div>
                    <div class="input-prepend">
                    	<button class="btn btn-primary next-step">
							Doorgaan
                    	</button>
                    </div>
                </div>
            </div>
            <div class="row-fluid" id="inlog">
                <div class="login-box">
                    <div class="icons">
                        <i class="fa-icon-arrow-left" id="goback">Terug</i>
                    </div>
                    <h2>ATD Login</h2>
                    <form class="form-horizontal" action="login" method="post">
                    	<input type="hidden" id="user-type2" name="user-type2">
                        <fieldset>
                            <div class="input-prepend" title="Username">
                                <span class="add-on"><i class="halflings-icon user"></i></span>
                                <input class="input-large span10" name="username" id="username" type="text" placeholder="Gebruikersnaam" />
                            </div>
                            <div class="clearfix"></div>
                            <div class="input-prepend" title="Password">
                                <span class="add-on"><i class="halflings-icon lock"></i></span>
                                <input class="input-large span10" name="password" id="password" type="password" placeholder="Wachtwoord" />
                            </div>
                            <div class="clearfix"></div>
                            <div class="button-login">
                                <button type="submit" class="btn btn-primary">Login</button>
                            </div>
                            <div class="clearfix"></div> 
                        </fieldset>
                    </form>
                    <hr>
                    <!-- <h3>Forgot Password?</h3> -->
                    <p>
                        <!-- No problem, <a href="#">click here</a> to get a new password. -->
                    </p>
                </div>
                <!--/span-->
            </div>
            <!--/row-->
        </div>
        <!--/.fluid-container-->
    </div>
    <!--/fluid-row-->
    <!-- start: JavaScript-->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/jquery-migrate-1.0.0.min.js"></script>
    <script src="js/jquery-ui-1.10.0.custom.min.js"></script>
    <script src="js/jquery.ui.touch-punch.js"></script>
    <script src="js/modernizr.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/atd.js"></script>
    <!-- end: JavaScript-->
</body>

</html>
