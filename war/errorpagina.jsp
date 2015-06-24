<%@page isErrorPage="true" %>
<!DOCTYPE html>
<%@page import="nl.atd.helper.ConfigHelper"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <!-- start: Meta -->
    <meta charset="utf-8">
    <title>ATD - Error</title>
    <!-- end: Meta -->
    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- end: Mobile Specific -->
    <!-- start: CSS -->
    <link id="bootstrap-style" href="<%=application.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=application.getContextPath() %>/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link id="base-style" href="<%=application.getContextPath() %>/css/style.css" rel="stylesheet">
    <link id="base-style-responsive" href="<%=application.getContextPath() %>/css/style-responsive.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext" rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="<%=application.getContextPath() %>/css/atd.css" rel="stylesheet">
    <!-- end: CSS -->
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="<%=application.getContextPath() %>/css/ie.css" rel="stylesheet">
	<![endif]-->
    <!--[if IE 9]>
		<link id="ie9style" href="<%=application.getContextPath() %>/css/ie9.css" rel="stylesheet">
	<![endif]-->
    <!-- start: Favicon -->
    <link rel="shortcut icon" href="<%=application.getContextPath() %>/img/favicon.ico">
    <!-- end: Favicon -->
    <style type="text/css">
    body {
        background: url('<%=application.getContextPath() %>/img/bg-login.jpg') !important;
    }
    </style>
</head>

<body>
    <div class="container-fluid-full">
        <div class="row-fluid">
            <div class="row-fuild" id="type-user">
                <div class="login-box">
                	<div class="logo-box">
                		<img src="<%=application.getContextPath() %>/img/atd.png" alt="logo atd" class="logo" />
                	</div>
                	
                    <h1 style="text-align:center;">Foutmelding</h1>
                    
                    <p>Waarschijnlijk bestaat de pagina niet, of heeft u verkeert geklikt.</p>
                    <p>Ga terug of sluit het venster</p>
                    
                    <p class="text-muted">HTTP <%=response.getStatus() %></p>
                </div>
            </div>
            <!--/row-->
        </div>
        <!--/.fluid-container-->
    </div>
    <!--/fluid-row-->
    <!-- start: JavaScript-->
    <script src="<%=application.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
    <script src="<%=application.getContextPath() %>/js/jquery-migrate-1.0.0.min.js"></script>
    <script src="<%=application.getContextPath() %>/js/jquery-ui-1.10.0.custom.min.js"></script>
    <script src="<%=application.getContextPath() %>/js/jquery.ui.touch-punch.js"></script>
    <script src="<%=application.getContextPath() %>/js/modernizr.js"></script>
    <script src="<%=application.getContextPath() %>/js/bootstrap.min.js"></script>
    <script src="<%=application.getContextPath() %>/js/atd.js"></script>
    <!-- end: JavaScript-->
</body>

</html>
