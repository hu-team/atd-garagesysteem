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
	<li>
		<a href="<%=application.getContextPath()%>/secure/autooverzicht.jsp">
			<i class="fa fa-car"></i>
			<span class="hidden-tablet"> Auto overzicht</span>
		</a>
	</li>
	<li>
		<a href="<%=application.getContextPath()%>/secure/bezetteparkeerplek.jsp">
			<i class="parking-style bgtje">P</i>
			<span class="hidden-tablet"> Parkeerplekken overzicht</span>
		</a>
	</li>
	<li>
		<a href="<%=application.getContextPath()%>/secure/factuuroverzicht.jsp">
			<i class="fa fa-money"></i>
			<span class="hidden-tablet"> Factuur overzicht</span>
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
<li>
	<a href="<%=application.getContextPath()%>/secure/reserveerparkeerplekklant.jsp">
		<i class="fa fa-car"></i>
		<span class="hidden-tablet">Plek reserveren</span>
	</a>
</li>
<li>
	<a href="<%=application.getContextPath()%>/secure/editklantklant.jsp">
		<i class="fa fa-user"></i>
		<span class="hidden-tablet">Mijn gegevens wijzigen</span>
	</a>
</li>
<% } %>
</ul>