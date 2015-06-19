<%@page import="nl.atd.helper.AuthHelper"%>
<%@ include file="_header.jsp" %>
<% if(!AuthHelper.isAdmin(session)) response.sendRedirect(application.getContextPath() + "/secure/"); %>
<!-- start: Content -->
<div id="content" class="span10">
	<ul class="breadcrumb">
	</ul>
	<div class="row-fluid sortable">
		<div class="box span12">
			<div class="box-header" data-original-title>
				<h2>
					<i class="halflings-icon white edit"></i><span class="break"></span>Parkeerplek eserveren
				</h2>
				<div class="box-icon">
				</div>
			</div>
			<div class="box-content">
				<form class="form-horizontal" method="post" action="addartikel">
					<input type="hidden" name="rij" id="inputRij">
					<input type="hidden" name="plek" id="inputPlek">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="van">Van: </label>
							<div class="controls">
								<input type="text" class="input-xlarge datepicker" id="van" name="vanDatum" value='<c:out value="${artikel.naam }" />'>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="tot">Tot: </label>
							<div class="controls">
								<input type="text" class="input-xlarge datepicker" id="tot" name="totDatum" value='<c:out value="${artikel.code }" />'>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="vantijd">Van tijd: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="vantijd" name="vanTijdstip" value='<c:out value="${artikel.naam }" />'>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="tottijd">Tot tijd: </label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="tottijd" name="totTijdstip" value='<c:out value="${artikel.naam }" />'>
							</div>
						</div>
						<div class="control-group">
						<div class="controls">
						<button class="btn btn-primary" id="zoekPlek">Vrije plekken zoeken</button>	
						</div>
						</div>											
						<div class="control-group" id="hide-parkeerplek">
							<label class="control-label" for="aantal">Plek: </label>
							<div class="controls">
								<span id="aantal-plekken"></span><br/>
								<select name="plek" id="parkeerplek-select">
									
								</select>
							</div>
						</div>
						<div class="control-group" id="hide-error">
							<div class="controls">
							</div>
						</div>	
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">Parkeerplek reserveren</button>
							<a href="<%=application.getContextPath()%>/secure/" class="btn btn-danger promise-me">Annuleren</a>
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>

</div>
<%@ include file="_footer.jsp" %>