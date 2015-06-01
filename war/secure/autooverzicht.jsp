<%@page import="nl.atd.model.Auto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="nl.atd.model.Klant"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.ArtikelService"%>
<%@page import="nl.atd.model.Artikel"%>
<%@page import="nl.atd.service.ServiceProvider"%>
<%@ include file="_header.jsp"%>

<%
if(request.getParameter("klant") == null) response.sendRedirect(request.getContextPath() + "/secure/klantoverzicht.jsp");

if(!AuthHelper.isMonteur(session) && !AuthHelper.isAdmin(session)) response.sendRedirect(request.getContextPath() + "/secure/");

Klant klant = ServiceProvider.getKlantService().getKlantByGebruikersnaam(request.getParameter("klant"));
if(klant == null) response.sendRedirect(request.getContextPath() + "/secure/klantoverzicht.jsp");
request.setAttribute("klant", klant);

%>

<div id="content" class="span10">
	<div class="row-fluid sortable ui-sortable">
		<div class="box span12">
			<div class="box-header" data-original-title="">
				<h2>
					<i class="halflings-icon white th-list"></i><span class="break"></span>Auto
					lijst voor '<c:out value="${klant.naam }" />'
				</h2>
			</div>
			<div class="box-content">
				<table
					class="table table-striped table-bordered bootstrap-datatable datatable dataTable">
					<thead>
						<tr role="row">
							<td>Merk</td>
							<td>Model</td>
							<td>Bouwjaar</td>
							<td>Kenteken</td>
							<td>Laatste Beurt</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="auto"
							items="${ServiceProvider.getAutoService().getAutosVanKlant(klant.getGebruikersnaam())}">
							<tr>
								<td>${auto.merk}</td>
								<td>${auto.model}</td>
								<td>${auto.bouwjaar}</td>
								<td>${auto.kenteken}</td>
								<td>${auto.laatsteBeurt}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
		<div class="clearfix"></div>

		<div class="row-fluid sortable ui-sortable">
			<div class="box span12">
				<div class="box-header" data-original-title="">
					<h2>
						<i class="halflings-icon white th-list"></i><span class="break"></span>Auto
						toevoegen
					</h2>
				</div>
				<div class="box-content">
				
					<c:if test="${error == true}">
						<div class="alert alert-danger">
							<c:out value="${errorString }" escapeXml="false"></c:out>
						</div>
					</c:if>
					<c:if test="${not empty param.done}">
						<div class="alert alert-success">
							Opslaan gelukt!
						</div>
					</c:if>
				
					<form class="form-horizontal" method="post" action="addauto">
						<fieldset>
							<input type="hidden" name="klant" value='<c:out value="${klant.gebruikersnaam }" />' />
							
							<div class="control-group">
								<label class="control-label" for="merk">Merk: </label>
								<div class="controls">
									<input type="text" class="input-xlarge" id="merk"
										name="merk">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="model">Model:
								</label>
								<div class="controls">
									<input type="text" class="input-xlarge" id="model"
										name="model">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="bouwjaar">Bouwjaar:
								</label>
								<div class="controls">
									<input type="text" class="input-xlarge" id="bouwjaar"
										name="bouwjaar">
								</div>
							</div>
	
							<div class="control-group">
								<label class="control-label" for="kenteken">Kenteken:
								</label>
								<div class="controls">
									<input class="input-xlarge" id="kenteken" type="text"
										name="kenteken">
								</div>
							</div>
	
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">Auto aan klant toevoegen</button>
								<button type="reset" class="btn">Annuleren</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="_footer.jsp"%>