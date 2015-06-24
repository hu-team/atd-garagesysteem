<%@page import="nl.atd.model.Factuur"%>
<%@page import="nl.atd.model.Reservering"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="nl.atd.model.Klant"%>
<%@page import="nl.atd.service.ServiceProvider"%>
<%@page import="nl.atd.model.Klus"%>
<%@page import="java.util.ArrayList"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<fmt:setLocale value="nl_NL" />
<%
String gebruikersnaam = AuthHelper.getGebruikersnaam(session);

Klant klant = ServiceProvider.getKlantService().getKlantByGebruikersnaam(gebruikersnaam);
ArrayList<Klus> klussen = new ArrayList<Klus>();
ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
ArrayList<Factuur> facturen = new ArrayList<Factuur>();

if(klant != null) {
	klussen = ServiceProvider.getKlusService().getKlussenVanKlant(klant);
	reserveringen = ServiceProvider.getReserveringService().getReserveringenVanKlant(klant);
	facturen = ServiceProvider.getFactuurService().getFacturenVanKlant(klant);
}

pageContext.setAttribute("klant", klant);
pageContext.setAttribute("klussen", klussen);
pageContext.setAttribute("reserveringen", reserveringen);
pageContext.setAttribute("facturen", facturen);

%>
<div class="row mm">
<div class="box span6" ontablet="span6" ondesktop="span6">
	<div class="box-header">
		<h2>
			<i class="halflings-icon white list"></i><span class="break"></span>Klussen
			overzicht
		</h2>
	</div>
	<div class="box-content">
		<table class="table">
			<thead>
				<tr>
					<th>Auto</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="klus" items="${klussen}">
					<tr>
						<td>
							<span class="kenteken">${klus.auto.kenteken}</span>
							${klus.auto.merk } ${klus.auto.model }
						</td>
						<c:choose>
							<c:when test="${klus.klaar}">
								<td><span class="green">Gereed</span></td>
							</c:when>
							<c:otherwise>
								<td><span class="yellow">In behandeling</span></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 
		<ul class="dashboard-list">
			<li>Status: <span class="green">[ Gereed ]</span> Audi A3
			</li>
			<li>Status: <span class="blue">[ In onderhoud ]</span> Audi S4
			</li>
			<li>Status: <span class="red">[ afgewezen ]</span> BMW i8
			</li>
			<li>Status: <span class="yellow">[ wachtrij ]</span> Gazelle
				fiets
			</li>
		</ul>
		 -->
	</div>
</div>

<div class="box span6" ontablet="span6" ondesktop="span6">
	<div class="box-header">
		<h2>
			<i class="halflings-icon white user"></i><span class="break"></span>Mijn Gegevens
		</h2>
	</div>
	<div class="box-content">
		<c:choose>
		<c:when test="${empty klant }">
		<h1>Er is iets mis gegaan, neem aub contact met ons op.</h1>
		</c:when>
		<c:otherwise>
		<table class="table">
			<tbody>
				<tr>
					<td>Naam</td>
					<td><c:out value="${klant.naam }"></c:out></td>
				</tr>
				<tr>
					<td>E-mail</td>
					<td><c:out value="${klant.email }"></c:out></td>
				</tr>
				<tr>
					<td>Adres</td>
					<td><c:out value="${klant.adres }"></c:out></td>
				</tr>
				<tr>
					<td>Postcode en plaats</td>
					<td><c:out value="${klant.postcode }"></c:out>, <c:out value="${klant.woonplaats }"></c:out></td>
				</tr>
				<tr>
					<td>Telefoonnummer</td>
					<td><c:out value="${klant.telefoonnummer }"></c:out></td>
				</tr>
			</tbody>
		</table>
		
		<a href="<%=application.getContextPath()%>/secure/editklantklant.jsp" class="btn btn-primary">Mijn gegevens aanpassen</a>
		</c:otherwise>
		</c:choose>
	</div>
</div>
</div>

<div class="row mm">
<div class="box span6" ontablet="span6" ondesktop="span6">
	<div class="box-header">
		<h2>
			<i class="halflings-icon white user"></i><span class="break"></span>Facturen (laatste 20)
		</h2>
	</div>
	<div class="box-content">
		<table class="table">
			<thead>
				<tr>
					<th>Factuurdatum</th>
					<th>Nummer</th>
					<th>Bedrag</th>
					<th>Status</th>
					<th>Actie</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="factuur" items="${facturen }">
				<tr>
					<td><fmt:formatDate type="date" value="${factuur.datum.time }" /></td>
					<td>${factuur.factuurnummer }</td>
					<td><fmt:formatNumber value="${factuur.totaalPrijs }" type="currency" /></td>
					<td>
					<c:choose>
					<c:when test="${factuur.betaald }">
					<span class="text-success">Betaald!</span>
					</c:when>
					<c:otherwise>
					<span class="text-warning">Openstaand</span>
					</c:otherwise>
					</c:choose>
					</td>
					<td>
					<a class="btn btn-success" href="factuur.jsp?nummer=${factuur.factuurnummer}"> 
					<i class="fa fa-file-text"></i>
					</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="box span6" ontablet="span6" ondesktop="span6">
	<div class="box-header">
		<h2>
			<i class="halflings-icon white user"></i><span class="break"></span>Reserveringen parkeerplekken
		</h2>
	</div>
	<div class="box-content">
		<table class="table">
			<thead>
				<tr>
					<th>Auto</th>
					<th>Parkeerplek</th>
					<th>Van</th>
					<th>Tot</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="reservering" items="${reserveringen }">
				<tr>
					<td>
					<span class="kenteken">${reservering.auto.kenteken}</span>
							${reservering.auto.merk } ${reservering.auto.model }
					</td>
					<td>${reservering.parkeerplek.rij } - ${reservering.parkeerplek.plek }</td>
					<td><fmt:formatDate dateStyle="short" timeStyle="short" type="both" value="${reservering.van.time }" /></td>
					<td><fmt:formatDate dateStyle="short" timeStyle="short" type="both" value="${reservering.tot.time }" /></td>	
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>