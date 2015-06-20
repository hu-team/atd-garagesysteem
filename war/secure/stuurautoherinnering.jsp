<%@page import="nl.atd.model.Auto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="nl.atd.model.Klant"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.ArtikelService"%>
<%@page import="nl.atd.model.Artikel"%>
<%@page import="nl.atd.service.ServiceProvider"%>
<%@ include file="_header.jsp"%>
<fmt:setLocale value="nl_NL"/>
<%
if(!AuthHelper.isAdmin(session)) { response.sendRedirect(request.getContextPath() + "/secure/"); return; }
if(request.getParameter("auto") == null) { response.sendRedirect(request.getContextPath() + "/secure/autooverzicht.jsp"); return; }

Auto auto = ServiceProvider.getAutoService().getAutoOpKenteken(request.getParameter("auto"));
if(auto == null) { response.sendRedirect(request.getContextPath() + "/secure/autooverzicht.jsp"); return; }

Klant klant = ServiceProvider.getKlantService().getKlantMetAuto(auto);
if(klant == null) { response.sendRedirect(request.getContextPath() + "/secure/autooverzicht.jsp"); return; }

pageContext.setAttribute("auto", auto);
pageContext.setAttribute("klant", klant);
%>

<div id="content" class="span10">
	<div class="row-fluid sortable ui-sortable">
		<div class="box span12">
			<div class="box-header" data-original-title="">
				<h2>
					<i class="halflings-icon white th-list"></i><span class="break"></span>Herinnering voor autonderhoud sturen
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
						E-mail is verzonden <a href="autooverzicht.jsp" class="btn btn-success">Terug naar auto overzicht</a>
					</div>
				</c:when>
				<c:otherwise>
				<form action="stuurautoherinnering" method="post" class="form-horizontal">
					<fieldset>
						<input type="hidden" name="klant" value='<c:out value="${klant.gebruikersnaam }" />' />
						<input type="hidden" name="auto" value='<c:out value="${auto.kenteken }" />' />

						<div class="control-group">
							<label class="control-label" for="bericht">Bericht: </label>
							<div class="controls">
								<h3>Stuur herinneringsmail naar:</h3>
								<h5><c:out value="${klant.naam }" /> - <c:out value="${klant.email }" /></h5>
							
								<textarea class="cleditor" id="bericht" name="bericht">
									<strong>Beste ${klant.naam },</strong><br /><br />
									Uw ${auto.merk } ${auto.model } (${auto.kenteken }) is nodig toe aan onderhoud, laatste onderhoudsbeurt is op <c:choose><c:when test="${not empty auto.laatsteBeurt }"><fmt:formatDate dateStyle="long" value="${auto.laatsteBeurt }"/></c:when><c:otherwise>een onbekende datum</c:otherwise></c:choose> geweest.<br />
									Wij bevelen u aan om elke 6 maanden een onderhoudsbeurt te laten doen. <br /><br />
									U kunt zelf een afspraak inplannen via onze Klanten Portaal op <a href="http://www.atd.nl/app">ATD.nl/App</a><br /><br />
									Neem gerust contact op bij vragen of opmerkingen.<br /><br />
									Met vriendelijke groet,<br />
									Henk<br />
									ATD Manager
								</textarea>
							</div>
						</div>

						<hr>

						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Verstuur e-mail</button>
							<a href="autooverzicht.jsp" class="btn btn-danger promise-me">Annuleren</a>
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