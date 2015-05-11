<%

// Als er mogelijk al iemand ingelogd is, gaan wij alvast naar backend
if(request.getSession().getAttribute("gebruikersnaam") != null) {
	response.sendRedirect(application.getContextPath() + "/secure/index.jsp");
}else{
	// Anders redirecten naar login pagina
	response.sendRedirect(application.getContextPath() + "/login.jsp");
}

%>