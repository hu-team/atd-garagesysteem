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
					<i class="halflings-icon white th-list"></i><span class="break"></span>Facturen
				</h2>
			</div>
			<div class="box-content">
			
				<c:if test="${not empty param.done}">
					<div class="alert alert-success">
						Factuur gemaakt!
					</div>
				</c:if>
			
				<table
					class="table table-striped table-bordered bootstrap-datatable datatable dataTable">
					<thead>
						<tr role="row">
							<td>Nummer</td>
							<td>Datum</td>
							<td>Klant</td>
							<td>Totaal</td>
							<td>Status</td>
							<td>*</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="factuur"
							items="${facturen}">
							<tr>
							<fmt:setLocale value="nl_NL" />
								<td>${factuur.factuurnummer }</td>
								<td><fmt:formatDate type="date" value="${factuur.datum.time }" /></td>
								<td>${factuur.klant.naam }</td>
								<td><fmt:formatNumber type="currency" value="${factuur.totaalPrijs }" /></td>
								<td>
								<c:choose>
								<c:when test="${factuur.betaald }">
								<span class="text-success">Betaald</span>
								</c:when>
								<c:otherwise>
								<span class="text-warning">Onbetaald!</span>
								</c:otherwise>
								</c:choose>
								</td>
								<td>
								<a class="btn btn-success" href="factuur.jsp?nummer=${factuur.factuurnummer}"> 
								<i class="fa fa-file-text"></i>
								</a>
								<c:if test="${not factuur.betaald }">
								<a class="btn btn-warning" href="stuurfactuurherinnering.jsp?nummer=${factuur.factuurnummer}" title="Verstuur factuur herinneringsmail"> 
								<i class="fa fa-envelope"></i>
								</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				
				<a href="addfactuur.jsp" class="btn btn-lg btn-primary">Nieuwe Factuur</a>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<%@ include file="_footer.jsp"%>