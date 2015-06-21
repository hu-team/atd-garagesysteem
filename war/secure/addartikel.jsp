<%@page import="nl.atd.helper.AuthHelper"%>
<%@ include file="_header.jsp"%>
<%
	if (!AuthHelper.isAdmin(session))
		response.sendRedirect(application.getContextPath() + "/secure/");
%>
<!-- start: Content -->
<div id="content" class="span10">
	<ul class="breadcrumb">
	</ul>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon white edit"></i><span class="break"></span>Nieuwe
					Artikel toevoegen
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" method="post" action="addartikel">
					<c:if test="${error}">
						<div class="alert alert-error">${errorString}</div>
					</c:if>
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="naam">Naam: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="naam" name="naam"
									value='<c:out value="${artikel.naam }" />'>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="code">Artikelcode: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="code" name="code"
									value='<c:out value="${artikel.code }" />'>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="aantal">Aantal: </label>
							<div class="controls">
								<input class="input-xlarge" id="aantal" type="number"
									name="aantal" value='<c:out value="${artikel.aantal }" />'>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="prijs">Prijs: </label>
							<div class="controls">
								<input class="input-xlarge" id="prijs" type="text" name="prijs"
									value='<c:out value="${artikel.prijs }" />'>
							</div>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Artikel
								toevoegen</button>
							<a
								href="<%=application.getContextPath()%>/secure/artikeloverzicht.jsp"
								class="btn btn-danger promise-me">Annuleren</a>
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>

</div>
<%@ include file="_footer.jsp"%>