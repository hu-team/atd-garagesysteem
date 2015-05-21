<%@page import="nl.atd.service.ServiceProvider"%>
<%@page import="nl.atd.model.Artikel"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@ include file="_header.jsp" %>
<% if(!AuthHelper.isAdmin(session)) response.sendRedirect(application.getContextPath() + "/secure/");
if(request.getParameter("code") == null || request.getParameter("code").trim().isEmpty()) {response.sendRedirect(application.getContextPath()+"/secure/artikellijst.jsp"); return; }

Artikel artikel = ServiceProvider.getArtikelService().getArtikelByCode(request.getParameter("code"));

if(artikel == null) { response.sendRedirect(application.getContextPath()+"/secure/artikellijst.jsp"); return; }

%>
<!-- start: Content -->
<div id="content" class="span10">
	<ul class="breadcrumb">
	</ul>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon white edit"></i><span class="break"></span>Artikel bewerken
				</h2>
				<div class="box-icon">
				</div>
			</div>
			<div class="box-content">
			
				<c:if test="${error == true}">
				<div class="alert alert-danger">
					<c:out value="${errorString }" escapeXml="false"></c:out>
				</div>
				</c:if>
			
				<form class="form-horizontal" method="post" action="editartikel">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="naam">Naam: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="naam" name="naam" value="<%=artikel.getNaam() %>">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="code">Artikelcode: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="code" name="code" readonly="readonly" value="<%=artikel.getCode() %>">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="aantal">Aantal: </label>
							<div class="controls">
								<input class="input-xlarge" id="aantal" type="number" name="aantal" value="<%=artikel.getAantal() %>">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="prijs">Prijs: </label>
							<div class="controls">
								<input class="input-xlarge" id="prijs" type="text" name="prijs" value="<%=artikel.getPrijs() %>">
							</div>
						</div>
						
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Artikel wijzigen</button>
							<a href="artikellijst.jsp" class="btn btn-default">Annuleren</a>
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>

</div>
<%@ include file="_footer.jsp" %>