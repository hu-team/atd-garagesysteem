<%@ include file="_header.jsp" %>
<div id="content" class="span10">
<%
if(AuthHelper.isAdmin(session)) {
	out.println("<h1>WELKOM BEDRIJFSLEIDER</h1>"); %>
	<%@ include file="_dashboard-bedrijfsleider.jsp" %>
<%	
}else if(AuthHelper.isMonteur(session)) {
	out.println("<h1>WELKOM MONTEUR</h1>");
}else if(AuthHelper.isKlant(session)) {
	out.println("<h1>WELKOM KLANT</h1>");
	%>
	<%@ include file="_dashboard-klant.jsp" %>
<% 
}
%>
</div>
<%@ include file="_footer.jsp" %>