package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Klant;

@SuppressWarnings("serial")
public class AddKlantServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String username = req.getParameter("username");
		String wachtwoord1 = req.getParameter("wachtwoord1");
		String wachtwoord2 = req.getParameter("wachtwoord2");
		String naam = req.getParameter("naam");
		String email = req.getParameter("email");
		
		String errorString = "";
		boolean error = false;
		
		if(username.trim().isEmpty() || wachtwoord1.trim().isEmpty() || wachtwoord2.trim().isEmpty() ||
				naam.trim().isEmpty() || email.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in <br />";
		}else if(!wachtwoord1.equals(wachtwoord2)){
			error = true;
			errorString += "Wachtwoorden komen niet overeen. <br />";
		}
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("addklant.jsp");
				
			rd.forward(req, resp);
			
			return;
		}
		
		Klant klant = new Klant(naam);
		klant.setEmail(email);
		klant.setGebruikersnaam(username);
		
		klant.setWachtwoord(AuthHelper.encryptWachtwoord(wachtwoord1));
		
	}
}
