<%@page import="nl.atd.helper.AuthHelper"%>
<ul class="nav nav-tabs nav-stacked main-menu">
<% if(AuthHelper.isAdmin(session) || AuthHelper.isMonteur(session)) { %>
	<li>
		<a href="index.jsp">
			<i class="icon-dashboard"></i>
			<span class="hidden-tablet"> Dashboard</span>
		</a>
	</li>
	<li>
		<a href="klantoverzicht.jsp">
			<i class="fa fa-users"></i>
			<span class="hidden-tablet">Klanten overzicht</span>
		</a>
	</li>
	<li>
		<a href="artikeloverzicht.jsp">
			<i class="fa fa-shopping-cart"></i>
			<span class="hidden-tablet">Artikelen overzicht</span>
		</a>
	</li>
<% } %>
</ul>