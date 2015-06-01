<%@page import="nl.atd.service.ArtikelService"%>
<%@page import="nl.atd.model.Artikel"%>
<%@page import="nl.atd.service.ServiceProvider"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@ include file="_header.jsp"%>

<%
	if (!AuthHelper.isAdmin(session) && !AuthHelper.isMonteur(session))
		response.sendRedirect(application.getContextPath() + "/secure/");
%>

<div id="content" class="span10">
	<div class="row-fluid sortable ui-sortable">
		<div class="box span12">
			<div class="box-header" data-original-title="">
				<h2>
					<i class="halflings-icon white th-list"></i><span class="break"></span>Artikelen
					overzicht
				</h2>
			</div>
			<div class="box-content">
				<c:if test="${error == true}">
					<div class="alert alert-danger">
						<c:out value="${errorString }" escapeXml="false"></c:out>
					</div>
				</c:if>
				<c:if test="${not empty param.done}">
					<div class="alert alert-success">Opslaan gelukt!</div>
				</c:if>
				<table
					class="table table-striped table-bordered bootstrap-datatable datatable dataTable">
					<thead>
						<tr role="row">
							<td>Naam</td>
							<td>Code</td>
							<td>Aantal</td>
							<td>Prijs</td>
							<td>Actie's</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="artikel"
							items="${ServiceProvider.getArtikelService().getAlleArtikelen()}">
							<tr>
								<td>${artikel.naam}</td>
								<td>${artikel.code}</td>
								<td>${artikel.aantal}</td>
								<td>${artikel.prijs}</td>
								<td><a class="btn btn-success"
									href="editartikel.jsp?code=${artikel.code }"> <i
										class="halflings-icon white edit"></i>
								</a>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

				<%
					if (AuthHelper.isAdmin(session)) {
				%>
				<a href="addartikel.jsp" class="btn btn-primary">Artikel
					toevoegen</a>
				<%
					}
				%>

			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<%@ include file="_footer.jsp"%>