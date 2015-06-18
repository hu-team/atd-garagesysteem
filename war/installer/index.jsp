<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Installatie</title>
<link id="bootstrap-style"
	href="<%=application.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>
				ATD Installatie <small>Installatie van Web Applicatie</small>
			</h1>
		</div>
		<div class="row">
			<div class="span5">
				<p class="lead">Vul de velden in, en klik op start installatie.</p>

				<form action="install" method="post" class="form">
				<c:choose>
				<c:when test="${empty installed }">
					<div class="form-group">
						<label for="mysql_server"> MySQL Server </label> <input
							type="text" class="form-control" id="mysql_server"
							name="mysql_server" value="localhost">
					</div>

					<div class="form-group">
						<label for="mysql_username"> MySQL Username </label> <input
							type="text" class="form-control" id="mysql_username"
							name="mysql_username" value="root">
					</div>

					<div class="form-group">
						<label for="mysql_password"> MySQL Password </label> <input
							type="text" class="form-control" id="mysql_password"
							name="mysql_password" value="">
					</div>

					<div class="form-group">
						<label for="mysql_database"> MySQL Database (moet al
							bestaan) </label> <input type="text" class="form-control"
							id="mysql_database" name="mysql_database" value="atd">
					</div>

					<button class="btn btn-lg btn-success" type="submit">Installatie
						starten!</button>
				</c:when>
				<c:otherwise>
					<p class="lead text-success">Installatie voltooid</p>
					<a href="<%=request.getContextPath() %>/installer/install?save=1" class="btn btn-lg btn-success">Opslaan, installatie afronden en starten!</a>
				</c:otherwise>
				</c:choose>
				</form>
			</div>
			<div class="span7">
				<h3>Uitvoer</h3>
				<c:if test="${not empty error }">
					<div class="alert alert-danger">
						<c:out value="${errorString }" escapeXml="false" />
					</div>
				</c:if>

				<c:if test="${not empty output }">
					<div class="alert alert-info">
						<c:out value="${outputString }" escapeXml="false" />
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<script src="<%=application.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=application.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
</html>