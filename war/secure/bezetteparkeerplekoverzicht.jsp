<%@page import="nl.atd.model.Auto"%>
<%@page import="nl.atd.model.Parkeerplek" %>
<%@page import="nl.atd.model.Reservering" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar" %>
<%@page import="nl.atd.model.Klant"%>
<%@page import="nl.atd.helper.AuthHelper"%>
<%@page import="nl.atd.service.ParkeerplekService"%>
<%@page import="nl.atd.service.ReserveringService" %>
<%@page import="nl.atd.service.KlantService" %>
<%@page import="nl.atd.service.AutoService" %>
<%@page import="nl.atd.service.ServiceProvider"%>
<%@ include file="_header.jsp"%>
<fmt:setLocale value="nl_NL"/>
<%
if(!AuthHelper.isMonteur(session) && !AuthHelper.isAdmin(session)) response.sendRedirect(request.getContextPath() + "/secure/");
if(request.getParameter("datum") == null && !AuthHelper.isAdmin(session)) { response.sendRedirect(request.getContextPath() + "/secure/betteparkeerplek.jsp"); return; }


ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
Calendar datum = (Calendar) request.getAttribute("datum");

if(request.getParameter("datum") != null) { 
	reserveringen = ServiceProvider.getReserveringService().getReserveringOpMaand(datum);
}

pageContext.setAttribute("reserveringen", reserveringen);
%>

<div id="content" class="span10">
	<div class="row-fluid sortable ui-sortable">
		<div class="box span12">
			<div class="box-header" data-original-title="">
				<h2>
					<i class="halflings-icon white th-list"></i><span class="break"></span>Bezette Parkeerplekken Overzicht
				</h2>
			</div>
			<div class="box-content">
				<table
					class="table table-striped table-bordered bootstrap-datatable datatable dataTable">
					<thead>
						<tr role="row">
							<td>Rij</td>
							<td>Plek</td>
							<td>Klant</td>
							<td>Auto</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reservering" items="${reserveringen}">
							<tr>
								<td>${reservering.parkeerplek.rij}</td>
								<td>${reservering.parkeerplek.plek}</td>
								<td>${reservering.klant.naam }</td>
								<td>${reservering.auto.merk } - ${reservering.auto.model } ( ${reservering.auto.kenteken } )</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<%@ include file="_footer.jsp"%>