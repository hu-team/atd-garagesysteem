<%@page import="nl.atd.service.ServiceProvider"%>
<%@ include file="_header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
if(!AuthHelper.isAdmin(session)) { response.sendRedirect(request.getContextPath() + "/secure/"); return; };


pageContext.setAttribute("facturen", ServiceProvider.getFactuurService().getAlleFacturen());

%>
<div id="content" class="span10">
	<div class="row-fluid sortable ui-sortable">
		<div class="box span12">
			<div class="box-header" data-original-title="">
				<h2>
					<i class="halflings-icon white th-list"></i><span class="break"></span>Factuur maken
				</h2>
			</div>
			<div class="box-content">
				<form class="form-horizontal" method="post" action="addfactuur">
					<c:if test="${error}">
						<div class="alert alert-error">${errorString}</div>
					</c:if>
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="naam">Klant: </label>
							<div class="controls">
								<select name="klant" id="factuur-klant">
									<c:forEach var="klant"
										items="${ServiceProvider.getKlantService().getAlleKlantenAuto()}">
										<option value="${klant.gebruikersnaam}">${klant.naam}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="datum">Datum: </label>
							<div class="controls">
								<input type="text" name="datum" class="datepicker">
							</div>
						</div>
						<div class="control-group" id="factuur-onderdeel-search">
							<div class="controls">
								<button class="btn btn-primary" id="factuur-search-items">Onderdelen zoeken</button>
							</div>
						</div>
						<div class="control-group" id="hide-error">
							<div class="controls">
								<p class="alert alert-error"></p>
							</div>
						</div>
						<div class="control-group" id="factuur-onderdeel-klus">
							<label class="control-label" for="datum">Klus: </label>
							<div class="controls">
								<select name="klusid" id="factuur-items-klus">
									
								</select>
							</div>
						</div>
						<div class="control-group" id="factuur-onderdeel-reservering">
							<label class="control-label" for="datum">Reservering: </label>
							<div class="controls">
								<select name="reserveringid" id="factuur-items-reservering">
									
								</select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<label>
								<input type="checkbox" class="checkbox" value="ja" name="mailversturen" checked="checked" />
								 Stuur een e-mail naar de klant met een melding dat er een factuur beschikbaar is.
								</label>
							</div>
						</div>
					</fieldset>
					<div class="form-actions">
						<button type="submit" id="factuur-add" class="btn btn-primary" disabled>Factuur
							aanmaken</button>
						<a
							href="<%=application.getContextPath()%>/secure/factuuroverzicht.jsp"
							class="btn btn-danger promise-me">Annuleren</a>
					</div>
				</form>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<%@ include file="_footer.jsp"%>