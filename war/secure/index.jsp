<%@ include file="_header.jsp" %>
<div id="content" class="span10">
	<h1>
<%
if(AuthHelper.isAdmin(session)) {
	out.println("WELKOM BEDRIJFSLEIDER");
}else if(AuthHelper.isMonteur(session)) {
	out.println("WELKOM MONTEUR");
}else if(AuthHelper.isKlant(session)) {
	out.println("WELKOM KLANT");
}
%>
	</h1>
</div>
<%@ include file="_footer.jsp" %>