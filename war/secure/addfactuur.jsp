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
				klant, datum, onderdelen (+ add, verwijzing naar klus OF reservering van KLANT die eerder is ingevuld, ajax denk ik)
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<%@ include file="_footer.jsp"%>