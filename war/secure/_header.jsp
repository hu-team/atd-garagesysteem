<%@page import="nl.atd.helper.AuthHelper"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ATD Applicatie</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link id="bootstrap-style"
	href="<%=application.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="<%=application.getContextPath()%>/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link
	href="<%=application.getContextPath()%>/css/sweetalert.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link id="base-style"
	href="<%=application.getContextPath()%>/css/style.css"
	rel="stylesheet">
<link id="base-style-responsive"
	href="<%=application.getContextPath()%>/css/style-responsive.css"
	rel="stylesheet">
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext"
	rel='stylesheet' type='text/css'>
<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="<%=application.getContextPath()%>/css/ie.css" rel="stylesheet">
	<![endif]-->
<!--[if IE 9]>
		<link id="ie9style" href="<%=application.getContextPath()%>/css/ie9.css" rel="stylesheet">
	<![endif]-->
<link rel="shortcut icon"
	href="<%=application.getContextPath()%>/img/favicon.ico">
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a><a class="brand" href="index.jsp"><span><i class="fa fa-car hidden-tablet hidden-phone"></i> ATD</span></a>

				<div class="nav-no-collapse header-nav">
					<ul class="nav pull-right">
						<li class="dropdown"><a class="btn dropdown-toggle"
							data-toggle="dropdown" href="#"> <i
								class="halflings-icon white user"></i> <%= AuthHelper.getNaam(session) %> <span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li class="dropdown-menu-title">
 									<span>Mijn ATD</span>
								</li>
								<li><a href="index.jsp"><i class="icon-dashboard user"></i>
										Dashboard</a></li>
								<li><a href="../logout"><i class="halflings-icon off"></i>
										Uitloggen</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid-full">
		<div class="row-fluid">
			<div id="sidebar-left" class="span2">
				<div class="nav-collapse sidebar-nav">
					<%@ include file="_sidebar.jsp"%>
				</div>
			</div>
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>