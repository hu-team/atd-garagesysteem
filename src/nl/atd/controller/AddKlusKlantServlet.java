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

import nl.atd.helper.AuthHelper;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.service.ServiceProvider;

public class AddKlusKlantServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String auto = req.getParameter("auto");
		String type = req.getParameter("type");
		String omschrijving = req.getParameter("omschrijving");
		
		String datum = req.getParameter("datum");
		String tijdstip = req.getParameter("tijdstip");
		
		boolean error = false;
		String errorString = "";
		
		if(type == null || omschrijving == null || datum == null || tijdstip == null || 
				type.trim().isEmpty() || omschrijving.trim().isEmpty() || datum.trim().isEmpty() || tijdstip.trim().isEmpty() ){
			error = true;
			errorString += "Vul alle velden in! <br />";
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
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("addklusklant.jsp");
			
			rd.forward(req, resp);
			
			return;
		}
		
		Klant klant = ServiceProvider.getKlantService().getKlantByGebruikersnaam(AuthHelper.getGebruikersnaam(req.getSession()));
		Auto aut = ServiceProvider.getAutoService().getAutoOpKenteken(auto);
		
		Klus klus = new Klus(klant, aut);
		
		klus.setOmschrijving(omschrijving);
		klus.setType(type);
		klus.setCalendar(datumCalendar);
		
		if(ServiceProvider.getKlusService().addKlus(klus)) {
			resp.sendRedirect(req.getContextPath() + "/secure/index.jsp");
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Opslaan is mislukt.");
			
			RequestDispatcher rd = req.getRequestDispatcher("addklusklant.jsp");
			
			rd.forward(req, resp);
		}
	}
	
}
