package nl.atd.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.model.Monteur;
import nl.atd.service.ServiceProvider;

public class AddKlusServlet extends HttpServlet{
	private static final long serialVersionUID = 4939994110043865017L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String type = req.getParameter("type");
		String uren = req.getParameter("uren");
		String omschrijving = req.getParameter("omschrijving");
		
		String datum = req.getParameter("datum");
		String tijdstip = req.getParameter("tijdstip");
		
		String klant = req.getParameter("klant");
		String auto = req.getParameter("auto");
		String monteur = req.getParameter("monteur");
		
		
		
		boolean error = false;
		String errorString = "";
		
		int urenNumeriek = 0;
		
		
		if(type == null || omschrijving == null || datum == null || tijdstip == null || 
				type.trim().isEmpty() || omschrijving.trim().isEmpty() || datum.trim().isEmpty() || tijdstip.trim().isEmpty() ){
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
		
		Calendar datumCalendar = Calendar.getInstance();
		
		// Formatting datum en tijd
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		df.setTimeZone(TimeZone.getDefault());
		try{
			datumCalendar.setTime(df.parse(datum + " " + tijdstip));
		}catch(ParseException pe) {
			error = true;
			errorString += "Datum is niet in een geldige notatie, gebruik DD-MM-YYYY. Of tijdstip is niet in 24-uurs HH:MM notatie <br />";
		}
		
		if(monteur == null || monteur.trim().isEmpty()){
			// Monteur kan nu nog leeg zijn, geen probleem
			monteur = null;
		}else if(!monteur.trim().isEmpty() && ServiceProvider.getMonteurService().getMonteurByGebruikersnaam(monteur) == null){
			error = true;
			errorString += "De selecteerde monteur is niet geldig <br />";
		}
		
		if(uren == null || uren.trim().isEmpty()){
			uren = "0";
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
		Monteur mont = (monteur == null ? null : ServiceProvider.getMonteurService().getMonteurByGebruikersnaam(monteur));
		
		Klus klus = new Klus(kl, aut);
		
		klus.setMonteur(mont);
		klus.setOmschrijving(omschrijving);
		klus.setType(type);
		klus.setUren(urenNumeriek);
		klus.setCalendar(datumCalendar);
		
		if(ServiceProvider.getKlusService().addKlus(klus)) {
			resp.sendRedirect(req.getContextPath() + "/secure/index.jsp");
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Opslaan is mislukt.");
			
			RequestDispatcher rd = req.getRequestDispatcher("addklus.jsp");
			
			rd.forward(req, resp);
		}
		
	}
}
