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
					<i class="halflings-icon white edit"></i><span class="break"></span>Factuur
					voorbeeld
				</h2>
				<div class="box-icon"></div>
			</div>
			<div class="box-content">
				<div class="factuur-actie">
					<button class="btn btn-primary" id="factuur-voorbeeld">Print</button>
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
									<li>John Doe</li>
									<li>Doebieweg 42</li>
									<li>2390 KL Dussen</li>
								</ul>
							</div>
						</div>
						<div class="row">
							<ul class="factuur-gegevens">
								<li>Factuurnummer: 20150001</li>
								<li>Factuurdatum: 18-06-2015</li>
								<li>Vervaldatum: 18-07-2015</li>
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
									<td class="tbl-omschrijving">
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing
											elit. Quos explicabo beatae eius voluptas quia similique
											veritatis, incidunt</p>
									</td>
									<td class="tbl-aantal">2</td>
									<td class="tbl-prijs">30,00</td>
									<td class="tbl-totaal">60</td>
								</tbody>
							</table>
							<table class="totaal-bedrag">
								<tbody>
									<tr>
										<td>Subtotaal: $000</td>
										<td></td>
									</tr>
									<tr>
										<td>BTW Hoog(21%): $000</td>
										<td></td>
									</tr>
									<tr>
										<td>Totaal: $000</td>
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