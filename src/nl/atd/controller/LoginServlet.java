package nl.atd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.helper.AuthType;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 5093250906413964686L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if(!req.getParameter("username").isEmpty() && !req.getParameter("password").isEmpty()) {
			String gebruikersnaam = req.getParameter("username");
			String wachtwoord = req.getParameter("password");
			
			String typeString = req.getParameter("user-type2");
			AuthType type = null;
			if(typeString.equals("bedrijfsleider")) type = AuthType.ADMIN;
			if(typeString.equals("monteur")) type = AuthType.MONTEUR;
			if(typeString.equals("klant")) type = AuthType.KLANT;
			
			if(type != null) {
				if(AuthHelper.executeLogin(req.getSession(), gebruikersnaam, wachtwoord, type)) {
					resp.sendRedirect(req.getContextPath() + "/secure/");
					return;
				}
			}
		}
		
		resp.sendRedirect(req.getContextPath() + "/login.jsp?error=1");
	}
}
