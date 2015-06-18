<%@page import="nl.atd.helper.ConfigHelper"%>
<%

// Installatie nodig?
if(!ConfigHelper.isValidConfig()) {
	// Ja, installatie is nodig
	response.sendRedirect(application.getContextPath() + "/installer/index.jsp");
	return;
}

// Als er mogelijk al iemand ingelogd is, gaan wij alvast naar backend
if(request.getSession().getAttribute("gebruikersnaam") != null) {
	response.sendRedirect(application.getContextPath() + "/secure/index.jsp");
	return;
}else{
	// Anders redirecten naar login pagina
	response.sendRedirect(application.getContextPath() + "/login.jsp");
	return;
}

%>