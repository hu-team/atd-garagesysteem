<%@page import="java.util.Calendar"%>
<%@page import="nl.atd.service.ServiceProvider"%>
<%@page import="nl.atd.model.Factuur"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@ include file="_header.jsp"%>
<%
if (request.getParameter("nummer") == null) { response.sendRedirect(application.getContextPath() + "/secure/factuuroverzicht.jsp"); return; }

int factuurnummer = 0;
try{
	factuurnummer = Integer.parseInt(request.getParameter("nummer"));
}catch(NumberFormatException nfe) {
	response.sendRedirect(application.getContextPath() + "/secure/factuuroverzicht.jsp");
	return;
}

Factuur factuur = ServiceProvider.getFactuurService().getFactuurOpNummer(factuurnummer);
if(factuur == null) {response.sendRedirect(application.getContextPath() + "/secure/factuuroverzicht.jsp"); return;}

boolean bedrijfseigenaar = AuthHelper.isAdmin(session);

// AUTH CONTROLE!!!!
if (!AuthHelper.isAdmin(session)) { 
	// Mogelijk wel eigenaar van de factuur?
	if(AuthHelper.isKlant(session) && factuur.getKlant().getGebruikersnaam().equals(AuthHelper.getGebruikersnaam(session))) {
		// Goed, het is de eigenaar van de factuur, die mag er bij!!
		
	}else{
		response.sendRedirect(application.getContextPath() + "/secure/index.jsp"); return;		
	}
}


if(request.getParameter("betaald") != null && request.getParameter("betaald").equals("1")) {
	// Set op betaald, dan reload
	factuur.setBetaald(true);
	ServiceProvider.getFactuurService().setFactuurBetaald(factuur);
	
	response.sendRedirect(application.getContextPath() + "/secure/factuur.jsp?nummer=" + factuurnummer);
	return;
}


pageContext.setAttribute("factuur", factuur);

Calendar vervaldatum = (Calendar)factuur.getDatum().clone();
vervaldatum.add(Calendar.MONTH, 1);

pageContext.setAttribute("bedrijfseigenaar", bedrijfseigenaar);
pageContext.setAttribute("vervaldatum", vervaldatum);

%>
<fmt:setLocale value="nl_NL"/>
<div id="content" class="span10">
	<ul class="breadcrumb">
	</ul>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon white edit"></i><span class="break"></span>Factuur <c:out value="${factuur.factuurnummer }"></c:out>
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<div class="factuur-actie">
					<button class="btn btn-primary" id="factuur-voorbeeld">Print</button>
					<c:if test="${bedrijfseigenaar }">
					<c:if test="${not factuur.betaald }">
					<a href="factuur.jsp?nummer=${factuur.factuurnummer }&betaald=1" class="btn btn-warning">Factuur op betaald zetten</a>
					<a href="stuurfactuurherinnering.jsp?nummer=${factuur.factuurnummer }" class="btn btn-warning">Herinnering sturen</a>
					</c:if>
					<c:if test="${factuur.betaald }">
					<button type="button" readonly="readonly" disabled="disabled" class="btn btn-default disabled" title="Factuur is al betaald!">Herinnering sturen</button>
					(Factuur is betaald)
					</c:if>
					</c:if>
					<hr>
				</div>
				<div id="factuur-model">
					<div class="container" style="width: 100%">
						<div class="row">
							<div class="col-md-5"><img src="../img/atd.png" alt="logo atd" class="logo"></div>
							<div class="col-md-3"></div>
							<div class="col-md-4">
								<ul>
									<li>AutoTotaalDienst B.V.</li>
									<li>Oudenoord 137</li>
									<li>3513 KS Utrecht</li>
								</ul>
								<ul>
									<li>030-6873459</li>
									<li>info@atd.nl</li>
								</ul>
								<ul>
									<li>KvK: 0123456</li>
									<li>BTW: NL1234.56.789.B01</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<div class="klant-gegevens">
								<ul>
									<li>${factuur.klant.naam }</li>
									<li>${factuur.klant.adres }</li>
									<li>${factuur.klant.postcode } ${factuur.klant.woonplaats }</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<ul class="factuur-gegevens">
								<li>Factuurnummer: ${factuur.factuurnummer }</li>
								<li>Factuurdatum: <fmt:formatDate value="${factuur.datum.time }" dateStyle="short" /></li>
								<li>Vervaldatum: <fmt:formatDate value="${vervaldatum.time }" dateStyle="short" /></li>
							</ul>
						</div>
						<div class="row onderdeel-padding">
							<table class="onderdelen">
								<thead>
									<th class="tbl-omschrijving">Omschrijvig</th>
									<th class="tbl-aantal">Aantal</th>
									<th class="tbl-prijs">Prijs</th>
									<th class="tbl-totaal">Totaal</th>
								</thead>
								<tbody>
									<c:forEach var="onderdeel" items="${factuur.onderdelen }">
									<tr>
										<td class="tbl-omschrijving">
											<p>${onderdeel.omschrijving }</p>
										</td>
										<td class="tbl-aantal">1</td>
										<td class="tbl-prijs"><fmt:formatNumber type="currency" value="${onderdeel.totaalprijs }" /></td>
										<td class="tbl-totaal"><fmt:formatNumber type="currency" value="${onderdeel.totaalprijs }" /></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<table class="totaal-bedrag">
								<tbody>
									<tr>
										<td>Subtotaal: <fmt:formatNumber type="currency" value="${factuur.totaalPrijs - factuur.totaalBTW}" /></td>
										<td></td>
									</tr>
									<tr>
										<td>BTW Hoog(21%): <fmt:formatNumber type="currency" value="${factuur.totaalBTW}" /></td>
										<td></td>
									</tr>
									<tr>
										<td>Totaal: <fmt:formatNumber type="currency" value="${factuur.totaalPrijs}" /></td>
										<td></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="row factuur-afsluiting onderdeel-padding">
							<p>We vragen u vriendelijk doch dringend het totaalbedrag van
								genoemde facturen binnen 7 dagen op onze rekening NL44 RABO 0123
								4567 89 over te maken.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</div>
<%@ include file="_footer.jsp"%>