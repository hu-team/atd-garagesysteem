<%@page import="nl.atd.model.Onderdeel"%>
<%@page import="nl.atd.model.Artikel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="nl.atd.model.Klus"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.ArtikelService"%>
<%@page import="nl.atd.service.KlusService"%>
<%@page import="nl.atd.service.ServiceProvider"%>
<%@ include file="_header.jsp"%>
<%
if(request.getParameter("id") == null) response.sendRedirect(request.getContextPath() + "/secure/index.jsp");

if(!AuthHelper.isMonteur(session) && !AuthHelper.isAdmin(session)) { response.sendRedirect(request.getContextPath() + "/secure/index.jsp"); return; }

int id = 0;
try{
	id = Integer.parseInt(request.getParameter("id"));
}catch(NumberFormatException nfe) {
	response.sendRedirect(request.getContextPath() + "/secure/index.jsp");
	return;
}

Klus klus = ServiceProvider.getKlusService().getKlusOpId(id);
if(klus == null) {response.sendRedirect(request.getContextPath() + "/secure/index.jsp"); return;}

ArrayList<Onderdeel> onderdelen = ServiceProvider.getOnderdeelService().getAlleOnderdelenVanKlus(klus);

pageContext.setAttribute("klus", klus);
pageContext.setAttribute("onderdelen", onderdelen);
pageContext.setAttribute("artikelen", ServiceProvider.getArtikelService().getAlleArtikelen());
%>

<div id="content" class="span10">
<a href="<%=application.getContextPath()%>/secure/editklus.jsp?id=<%= id %>" class="btn btn-primary">Terug</a>
	<div class="margin-abit"></div>
	<div class="row-fluid sortable ui-sortable">
		<div class="box span12">
			<div class="box-header" data-original-title="">
				<h2>
					<i class="halflings-icon white th-list"></i><span class="break"></span>
					Klus kenteken '<c:out value="${klus.auto.kenteken }" />'
				</h2>
			</div>
			<div class="box-content">
				<table
					class="table table-striped table-bordered bootstrap-datatable datatable dataTable">
					<thead>
						<tr role="row">
							<td>Artikel</td>
							<td>Code</td>
							<td>Prijs</td>
							<td>Aantal</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="onderdeel"
							items="${onderdelen}">
							<tr>
								<td>${onderdeel.artikel.naam}</td>
								<td>${onderdeel.artikel.code}</td>
								<td>${onderdeel.artikel.prijs}</td>
								<td>${onderdeel.aantal}</td>
								<td></td>
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
						<i class="halflings-icon white th-list"></i><span class="break"></span>
						Onderdeel toevoegen
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
				
					<form class="form-horizontal" method="post" action="addonderdeel">
						<fieldset>
							<input type="hidden" name="klusid" value='<c:out value="${param.id }" />' />
							
							<div class="control-group">
								<label class="control-label" for="artikel">Artikel: </label>
								<div class="controls">
									<select class="form-control" name="artikel">
									<c:forEach var="artikel" items="${artikelen }">
										<option value="${artikel.code }">${artikel.naam } (${artikel.code })</option>
									</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label" for="aantal">Aantal: </label>
								<div class="controls">
									<input type="number" class="input-xlarge" id="aantal"
										name="aantal">
								</div>
							</div>
							
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">Onderdeel aan klus toevoegen</button>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="_footer.jsp"%>