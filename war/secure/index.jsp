<%@ include file="_header.jsp" %>

<div id="content" class="span10">
<%
if(AuthHelper.isAdmin(session)) {
	%><%@ include file="_dashboard-bedrijfsleider.jsp" %><%	
}else if(AuthHelper.isMonteur(session)) {
	%><%@ include file="_dashboard-monteur.jsp" %><%
}else if(AuthHelper.isKlant(session)) {
	%><%@ include file="_dashboard-klant.jsp" %><% 
}
%>
</div>
<%@ include file="_footer.jsp" %>