<%@page import="nl.atd.service.ServiceProvider"%>
<%@page import="nl.atd.model.Klant"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@ include file="_header.jsp" %>
<% 
if(!AuthHelper.isKlant(session)) {response.sendRedirect(application.getContextPath() + "/secure/"); return; } 

String gebruikersnaam = AuthHelper.getGebruikersnaam(session);
Klant klant = ServiceProvider.getKlantService().getKlantByGebruikersnaam(gebruikersnaam);

if(klant == null) {response.sendRedirect(application.getContextPath() + "/secure/"); return;}

pageContext.setAttribute("klant", klant);
%>
<!-- start: Content -->
<div id="content" class="span10">
	<ul class="breadcrumb">
	</ul>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon white edit"></i><span class="break"></span>Gegevens wijzigen
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
						Wijzigen gelukt!
					</div>
				</c:if>
			
				<form class="form-horizontal" method="post" action="editklantklant">
					<fieldset>
						<div class="control-group disabled">
							<label class="control-label" for="gebruikersnaam">Gebruikersnaam: </label>
							<div class="controls">
								<input type="text" class="input-xlarge disabled" readonly="" id="gebruikersnaam" name="gebruikersnaam" value="${klant.gebruikersnaam}">
								<p class="help-block">U kunt uw gebruikersnaam niet wijzigen!</p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="naam">Naam: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="naam" name="naam" value="${klant.naam}">
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
								<input type="text" class="input-xlarge" id="telefoonnr" name="telefoonnummer" value="${klant.telefoonnummer}">
							</div>
						</div>						
						<div class="control-group">
							<label class="control-label" for="adres">Adres: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="adres" name="adres" value="${klant.adres}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="postcode">Postcode: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="postcode" name="postcode" value="${klant.postcode}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="woonplaats">Woonplaats: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="woonplaats" name="woonplaats" value="${klant.woonplaats}">
							</div>
						</div>												
						
						<div class="control-group">
							<label class="control-label" for="wachtwoord">Wachtwoord: </label>
							<div class="controls">
								<input class="input-xlarge" id="wachtwoord" type="password" name="wachtwoord">
								<p class="help-block">Indien u uw wachtwoord niet wilt wijzigen moet u niks invullen!</p>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="wachtwoord2">Wachtwoord: <br />(herhaling)</label>
							<div class="controls">
								<input class="input-xlarge" id="wachtwoord2" type="password" name="wachtwoord2">
								<p class="help-block">Indien u uw wachtwoord niet wilt wijzigen moet u niks invullen!</p>
							</div>
						</div>
						
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Gegevens aanpassen</button>
							<a href="<%=application.getContextPath()%>/secure/index.jsp" class="btn btn-danger promise-me">Annuleren</a>
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>

</div>
<%@ include file="_footer.jsp" %>