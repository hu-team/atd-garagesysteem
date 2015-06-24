package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.helper.MailHelper;
import nl.atd.model.Klant;
import nl.atd.service.ServiceProvider;

public class AddKlantServlet extends HttpServlet{
	private static final long serialVersionUID = 2258393709794248113L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(!AuthHelper.isAdmin(req.getSession()) && !AuthHelper.isMonteur(req.getSession())) {return;}
		
		String username = req.getParameter("gebruikersnaam");
		String wachtwoord = req.getParameter("wachtwoord");
		String wachtwoord2 = req.getParameter("wachtwoord2");
		String voornaam = req.getParameter("voornaam");
		String achternaam = req.getParameter("achternaam");
		String adres = req.getParameter("adres");
		String postcode = req.getParameter("postcode");
		String woonplaats = req.getParameter("woonplaats");
		String telefoonnummer = req.getParameter("telefoonnummer");
		String email = req.getParameter("email");
		
		String welkomsmail = req.getParameter("welkomsmail");
		
		String errorString = "";
		boolean error = false;
		if(		username == null || wachtwoord == null || wachtwoord2 == null ||
				voornaam == null || achternaam == null || email == null ||
				adres == null || postcode == null || woonplaats == null ||
				telefoonnummer == null ||
				username.trim().isEmpty() || wachtwoord.trim().isEmpty() || wachtwoord2.trim().isEmpty() ||
				voornaam.trim().isEmpty() || achternaam.trim().isEmpty() || email.trim().isEmpty() || adres.trim().isEmpty() ||
				postcode.trim().isEmpty() || woonplaats.trim().isEmpty() || telefoonnummer.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in <br />";
		}else if(!wachtwoord.equals(wachtwoord2)){
			error = true;
			errorString += "Wachtwoorden komen niet overeen. <br />";
		}
		
		if(email != null &&(!email.contains("@") && !email.contains("."))) {
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
		klant.setAdres(adres);
		klant.setPostcode(postcode.replace(" ", ""));
		klant.setWoonplaats(woonplaats);
		klant.setTelefoonnummer(telefoonnummer);
		
		klant.setGebruikersnaam(username);
		klant.setWachtwoord(AuthHelper.encryptWachtwoord(wachtwoord));
		
		if(ServiceProvider.getKlantService().addKlant(klant)) {
			if(welkomsmail != null && welkomsmail.equals("ja")) {
				MailHelper.sendWelkomMailNaarKlant(klant, wachtwoord);
			}
			
			resp.sendRedirect(req.getContextPath() + "/secure/klantoverzicht.jsp?done=1");
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Opslaan is mislukt, mogelijk bestaat de klant al.");
			
			req.setAttribute("klant", klant);
			
			RequestDispatcher rd = req.getRequestDispatcher("addklant.jsp");
			
			rd.forward(req, resp);
		}
	}
}
