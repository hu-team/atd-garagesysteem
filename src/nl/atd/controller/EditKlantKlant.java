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

public class EditKlantKlant extends HttpServlet{
	private static final long serialVersionUID = 2258393709794248113L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String gebruikersnaam = req.getParameter("gebruikersnaam");
		String wachtwoord = req.getParameter("wachtwoord");
		String wachtwoord2 = req.getParameter("wachtwoord2");
		String naam = req.getParameter("naam");
		String adres = req.getParameter("adres");
		String postcode = req.getParameter("postcode");
		String woonplaats = req.getParameter("woonplaats");
		String telefoonnummer = req.getParameter("telefoonnummer");
		String email = req.getParameter("email");
		
		String errorString = "";
		boolean error = false;
		if(		gebruikersnaam == null || wachtwoord == null || wachtwoord2 == null ||
				naam == null || email == null ||
				adres == null || postcode == null || woonplaats == null ||
				telefoonnummer == null ||
				gebruikersnaam.trim().isEmpty() ||
				naam.trim().isEmpty() || email.trim().isEmpty() || adres.trim().isEmpty() ||
				postcode.trim().isEmpty() || woonplaats.trim().isEmpty() || telefoonnummer.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in <br />";
		}else if(!wachtwoord.trim().isEmpty() && !wachtwoord.equals(wachtwoord2)){
			error = true;
			errorString += "Wachtwoorden komen niet overeen. Laat wachtwoord velden leeg om wachtwoord niet te wijzigen. <br />";
		}
		
		if(email != null &&(!email.contains("@") && !email.contains("."))) {
			error = true;
			errorString += "E-mail is onjuist! <br />";
		}
		
		Klant klant = ServiceProvider.getKlantService().getKlantByGebruikersnaam(gebruikersnaam);
		
		
		if(klant == null || !AuthHelper.getGebruikersnaam(req.getSession()).equals(gebruikersnaam)) {
			// Iemand probeert wat leuks uit!
			error = true;
			errorString += "Onbekende fout, probeer opnieuw! <br />";
		}
		
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("editklantklant.jsp");
			
			rd.forward(req, resp);
			
			return;
		}
		
		klant.setNaam(naam);
		klant.setEmail(email);
		klant.setAdres(adres);
		klant.setPostcode(postcode.replace(" ", ""));
		klant.setWoonplaats(woonplaats);
		klant.setTelefoonnummer(telefoonnummer);
		
		if(!wachtwoord.trim().isEmpty()) {
			klant.setWachtwoord(AuthHelper.encryptWachtwoord(wachtwoord));
		}
		
		ServiceProvider.getKlantService().editKlant(klant);
		
		resp.sendRedirect(req.getContextPath() + "/secure/klantoverzicht.jsp?done=1");
	}
}
