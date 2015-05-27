package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.service.ServiceProvider;

public class AddKlusServlet extends HttpServlet{
	private static final long serialVersionUID = 4939994110043865017L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String type = req.getParameter("type");
		String uren = req.getParameter("uren");
		String omschrijving = req.getParameter("omschrijving");
		
		String klant = req.getParameter("klant");
		String auto = req.getParameter("auto");
		String monteur = req.getParameter("");
		
		boolean error = false;
		String errorString = "";
		
		int urenNumeriek = 0;
		
		if(type == null || uren == null || omschrijving == null || 
				type.trim().isEmpty() || uren.trim().isEmpty() || omschrijving.trim().isEmpty() ){
			error = true;
			errorString += "Vul alle velden in! <br />";
		}
		
		if(klant == null || klant.trim().isEmpty() || ServiceProvider.getKlantService().getKlantByGebruikersnaam(klant) == null) {
			error = true;
			errorString += "Selecteer een geldig klant. <br />";
		}
		
		if(auto == null || auto.trim().isEmpty() || ServiceProvider.getAutoService().getAutoOpKenteken(auto) == null){
			error = true;
			errorString += "Selecteer een geldig auto. <br />";
		}
		
		if(monteur == null){
			resp.sendRedirect(req.getContextPath() + "monteuroverzicht.jsp");
		}else if(!monteur.trim().isEmpty() && ServiceProvider.getMonteurService().getMonteurByGebruikersnaam(monteur) == null){
			error = true;
			errorString += "De selecteerde monteur is niet geldig <br />";
		}
		
		try{
			urenNumeriek = Integer.parseInt(uren);
		}catch(NumberFormatException nfe) {
			error = true;
			errorString += "Uren moet numeriek zijn!<br />";
		}
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("addklus.jsp");
			
			rd.forward(req, resp);
			
			return;
		}
		Klant kl = ServiceProvider.getKlantService().getKlantByGebruikersnaam(klant);
		Auto aut = ServiceProvider.getAutoService().getAutoOpKenteken(auto);
		
		Klus klus = new Klus(kl, aut);
		
		klus.setMonteur(monteur.trim().isEmpty() ? null : ServiceProvider.getMonteurService().getMonteurByGebruikersnaam(monteur));
		klus.setOmschrijving(omschrijving);
		klus.setType(type);
		klus.setUren(urenNumeriek);
		
		//TODO KlusService addKlus() toevoegen en bij KlusDAO ook.
	}
}
