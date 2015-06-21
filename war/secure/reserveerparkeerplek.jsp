<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.model.Klant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="nl.atd.model.Auto"%>
<%@page import="nl.atd.service.ServiceProvider"%>
<%@ include file="_header.jsp"%>
<%
if(request.getParameter("klant") == null && !AuthHelper.isAdmin(session)) { response.sendRedirect(request.getContextPath() + "/secure/klantoverzicht.jsp"); return; }
	String gebruikersnaam = request.getParameter("klant");
	Klant klant = ServiceProvider.getKlantService()
			.getKlantByGebruikersnaam(gebruikersnaam);
	if (klant == null)
		response.sendRedirect(request.getContextPath()
				+ "/secure/klantoverzicht.jsp");
	request.setAttribute("klant", klant);
%>
<!-- start: Content -->
<div id="content" class="span10">
	<ul class="breadcrumb">
	</ul>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon white edit"></i><span class="break"></span>Parkeerplek
					eserveren
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" method="post"
					action="reserveerparkeerplek">
					
					<c:if test="${error}">
					<div class="alert alert-error">
						${errorString}
					</div>
					</c:if>
					
					<div class="control-group">
						<div class="controls">
							<select name="auto">
								<c:forEach var="auto"
									items="${ServiceProvider.getAutoService().getAutosVanKlant(klant.getGebruikersnaam())}">
									<option value="${auto.kenteken}">${auto.merk}-
										${auto.model} - ${auto.kenteken}</option>
								</c:forEach>
							</select> <input type="hidden" name="rij" id="inputRij">
						</div>
					</div>
					<input type="hidden" name="klant" value="<% out.print(gebruikersnaam); %>">
					<input type="hidden" name="plek" id="inputPlek">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="van">Van: </label>
							<div class="controls">
								<input type="text" class="input-xlarge datepicker" id="van"
									name="vanDatum" value='<c:out value="${vanDatum}" />'>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="tot">Tot: </label>
							<div class="controls">
								<input type="text" class="input-xlarge datepicker" id="tot"
									name="totDatum" value='<c:out value="${totDatum}" />'>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="vantijd">Van tijd: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="vantijd"
									name="vanTijdstip" value='<c:out value="${vanTijdstip}" />'>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="tottijd">Tot tijd: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="tottijd"
									name="totTijdstip" value='<c:out value="${totTijdstip}" />'>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button class="btn btn-primary" id="zoekPlek">Vrije
									plekken zoeken</button>
							</div>
						</div>
						<div class="control-group" id="hide-parkeerplek">
							<label class="control-label" for="aantal">Plek: </label>
							<div class="controls">
								<span id="aantal-plekken"></span><br /> <select name="plek"
									id="parkeerplek-select">

								</select>
							</div>
						</div>
						<div class="control-group" id="hide-error">
							<div class="controls"></div>
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Parkeerplek
								reserveren</button>
							<a href="<%=application.getContextPath()%>/secure/"
								class="btn btn-danger promise-me">Annuleren</a>
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>

</div>
<%@ include file="_footer.jsp"%>