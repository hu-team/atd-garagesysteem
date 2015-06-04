<%@page import="nl.atd.helper.AuthHelper"%>
<ul class="nav nav-tabs nav-stacked main-menu">
<% if(AuthHelper.isAdmin(session) || AuthHelper.isMonteur(session)) { %>
	<li>
		<a href="<%=application.getContextPath()%>/secure/index.jsp">
			<i class="icon-dashboard"></i>
			<span class="hidden-tablet"> Dashboard</span>
		</a>
	</li>
	<% if(AuthHelper.isAdmin(session)) { %>
	<li>
		<a href="<%=application.getContextPath()%>/secure/monteuroverzicht.jsp">
			<i class="fa fa-users"></i>
			<span class="hidden-tablet"> Monteurs overzicht</span>
		</a>
	</li>
	<% } %>
	<li>
		<a href="<%=application.getContextPath()%>/secure/klantoverzicht.jsp">
			<i class="fa fa-users"></i>
			<span class="hidden-tablet">Klanten overzicht</span>
		</a>
	</li>
	<li>
		<a href="<%=application.getContextPath()%>/secure/artikeloverzicht.jsp">
			<i class="fa fa-shopping-cart"></i>
			<span class="hidden-tablet">Artikelen overzicht</span>
		</a>
	</li>
<% } %>
<% if(AuthHelper.isKlant(session)) { %>
<li>
		<a href="<%=application.getContextPath()%>/secure/index.jsp">
			<i class="icon-dashboard"></i>
			<span class="hidden-tablet"> Dashboard</span>
		</a>
</li>
<li>
	<a href="<%=application.getContextPath()%>/secure/addklusklant.jsp">
		<i class="fa fa-tasks"></i>
		<span class="hidden-tablet">Klus inplannen</span>
	</a>
</li>
<% } %>
</ul>