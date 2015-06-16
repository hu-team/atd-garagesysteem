<%@page import="nl.atd.helper.AuthHelper"%>
<%@ include file="_header.jsp" %>
<% if(!AuthHelper.isAdmin(session) && !AuthHelper.isMonteur(session)) response.sendRedirect(application.getContextPath() + "/secure/"); %>
<!-- start: Content -->
<div id="content" class="span10">
	<ul class="breadcrumb">
	</ul>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon white edit"></i><span class="break"></span>Nieuwe
					klant toevoegen
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
				<c:if test="${not empty param.done}">
					<div class="alert alert-success">
						Opslaan gelukt!
					</div>
				</c:if>
			
				<form class="form-horizontal" method="post" action="addklant">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="voornaam">Voornaam: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="voornaam" name="voornaam" value="${param.voornaam}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="achternaam">Achternaam: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="achternaam" name="achternaam" value="${param.achternaam}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="email">E-mail: </label>
							<div class="controls">
								<input class="input-xlarge" id="email" type="email" name="email" value='<c:out value="${klant.email }" />'>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="telefoonnr">Telefoorn nr: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="telefoonnr" name="telefoonnummer" value="${param.telefoonnummer}">
							</div>
						</div>						
						<div class="control-group">
							<label class="control-label" for="adres">Adres: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="adres" name="adres" value="${param.adres}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="postcode">Postcode: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="postcode" name="postcode" value="${param.postcode}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="woonplaats">Woonplaats: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="woonplaats" name="woonplaats" value="${param.woonplaats}">
							</div>
						</div>												
						<div class="control-group">
							<label class="control-label" for="gebruikersnaam">Gebruikersnaam: </label>
							<div class="controls">
								<input class="input-xlarge" id="gebruikersnaam" type="text" name="gebruikersnaam"  value='<c:out value="${klant.gebruikersnaam }" />'>
							</div>
						</div>					
						<div class="control-group">
							<label class="control-label" for="wachtwoord">Wachtwoord: </label>
							<div class="controls">
								<input class="input-xlarge" id="wachtwoord" type="password" name="wachtwoord">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="wachtwoord2">Wachtwoord: <br />(herhaling)</label>
							<div class="controls">
								<input class="input-xlarge" id="wachtwoord2" type="password" name="wachtwoord2">
							</div>
						</div>
						
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Klant toevoegen</button>
							<a href="<%=application.getContextPath()%>/secure/klantoverzicht.jsp" class="btn btn-danger promise-me">Annuleren</a>
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>

</div>
<%@ include file="_footer.jsp" %>