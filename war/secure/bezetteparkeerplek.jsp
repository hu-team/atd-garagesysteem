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
					<i class="halflings-icon white edit"></i><span class="break"></span>Bezette Parkeerplek
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
			
				<form class="form-horizontal" method="post" action="bezetteparkeerplek">
					<fieldset>
						<div class="control-group">
							<label class="control-label">Datum: </label>
							<div class="controls">
								<input type="text" class="span6 datepicker" name="datum" value="${param.datum}">
							</div>
						</div>						
						
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Overzicht maken</button>
							<a href="<%=application.getContextPath()%>/secure/" class="btn btn-danger promise-me">Annuleren</a>
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>

</div>
<%@ include file="_footer.jsp" %>