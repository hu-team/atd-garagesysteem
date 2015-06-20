<%@page import="nl.atd.model.Factuur"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.ServiceProvider"%>
<%@ include file="_header.jsp"%>
<fmt:setLocale value="nl_NL"/>
<%
if(!AuthHelper.isAdmin(session)) { response.sendRedirect(request.getContextPath() + "/secure/index.jsp"); return; }
if(request.getParameter("nummer") == null) { response.sendRedirect(request.getContextPath() + "/secure/factuuroverzicht.jsp"); return; }

int factuurnummer = 0;
try{
	factuurnummer = Integer.parseInt(request.getParameter("nummer"));
}catch(NumberFormatException nfe) {
	response.sendRedirect(application.getContextPath() + "/secure/factuuroverzicht.jsp");
	return;
}

Factuur factuur = ServiceProvider.getFactuurService().getFactuurOpNummer(factuurnummer);
if(factuur == null) {response.sendRedirect(application.getContextPath() + "/secure/factuuroverzicht.jsp"); return;}

pageContext.setAttribute("factuur", factuur);

%>

<div id="content" class="span10">
	<div class="row-fluid sortable ui-sortable">
		<div class="box span12">
			<div class="box-header" data-original-title="">
				<h2>
					<i class="halflings-icon white th-list"></i><span class="break"></span>Betalingsherinnering
				</h2>
			</div>
			<div class="box-content">
			
				<c:if test="${error == true}">
				<div class="alert alert-danger">
					<c:out value="${errorString }" escapeXml="false"></c:out>
				</div>
				</c:if>
				<c:choose>
				<c:when test="${not empty param.done}">
					<div class="alert alert-success">
						E-mail is verzonden <a href="factuuroverzicht.jsp" class="btn btn-success">Terug naar factuur overzicht</a>
					</div>
				</c:when>
				<c:otherwise>
				<form action="stuurfactuurherinnering" method="post" class="form-horizontal">
					<fieldset>
						<input type="hidden" name="factuur" value='<c:out value="${factuur.factuurnummer }" />' />

						<div class="control-group">
							<label class="control-label" for="bericht">Bericht: </label>
							<div class="controls">
								<h3>Stuur herinneringsmail naar:</h3>
								<h5><c:out value="${factuur.klant.naam }" /> - <c:out value="${factuur.klant.email }" /></h5>
							
								<textarea class="cleditor" id="bericht" name="bericht">
									<strong>Beste ${factuur.klant.naam },</strong><br /><br />
									Er staat nog een open factuur die nog niet betaald is!<br />
									Factuurnummer: ${factuur.factuurnummer }<br />
									Totaalbedrag: <fmt:formatNumber type="currency" value="${factuur.totaalPrijs }" /><br /><br />
									U dient de factuur zo snel mogelijk te betalen om zo juridische stappen te voorkomen. Bekijk uw factuur op <a href="http://www.atd.nl/app">ATD.nl/App</a>.<br />
									De betaling moet worden voldaan op bankrekening NL44 RABO 0123 4567 89, Onder vermelding van uw factuurnummer<br /><br />
									
									Met vriendelijke groet,<br />
									Henk<br />
									ATD Manager
								</textarea>
							</div>
						</div>

						<hr>

						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Verstuur e-mail</button>
							<a href="factuur.jsp?nummer=${factuur.factuurnummer }" class="btn btn-danger promise-me">Annuleren</a>
						</div>
					</fieldset>
				</form>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<%@ include file="_footer.jsp"%>