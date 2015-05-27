package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Klant;
import nl.atd.service.ServiceProvider;

public class AddKlantServlet extends HttpServlet{
	private static final long serialVersionUID = 2258393709794248113L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = req.getParameter("gebruikersnaam");
		String wachtwoord = req.getParameter("wachtwoord");
		String wachtwoord2 = req.getParameter("wachtwoord2");
		String voornaam = req.getParameter("voornaam");
		String achternaam = req.getParameter("achternaam");
		String email = req.getParameter("email");
		
		String errorString = "";
		boolean error = false;
		
		if(username.trim().isEmpty() || wachtwoord.trim().isEmpty() || wachtwoord2.trim().isEmpty() ||
				voornaam.trim().isEmpty() || achternaam.trim().isEmpty() || email.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in <br />";
		}else if(!wachtwoord.equals(wachtwoord2)){
			error = true;
			errorString += "Wachtwoorden komen niet overeen. <br />";
		}
		
		if(!email.contains("@") && !email.contains(".")) {
			error = true;
			errorString += "E-mail is onjuist! <br />";
		}
		
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("addklant.jsp");
				
			rd.forward(req, resp);
			
			return;
		}
		
		Klant klant = new Klant(voornaam + " " + achternaam);
		klant.setEmail(email);
		klant.setGebruikersnaam(username);
		
		klant.setWachtwoord(AuthHelper.encryptWachtwoord(wachtwoord));
		
		if(ServiceProvider.getKlantService().addKlant(klant)) {
			resp.sendRedirect(req.getContextPath() + "/secure/klantoverzicht.jsp?done=1");
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Opslaan is mislukt, mogelijk bestaat klant al.");
			
			req.setAttribute("klant", klant);
			
			RequestDispatcher rd = req.getRequestDispatcher("addklant.jsp");
			
			rd.forward(req, resp);
		}
	}
}
