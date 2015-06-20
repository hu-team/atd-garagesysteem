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

import nl.atd.model.Factuur;
import nl.atd.model.Klant;
import nl.atd.service.ServiceProvider;

public class AddFactuurServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		String klantGebruikersnaam = req.getParameter("klant");
		String datum = req.getParameter("datum");
		
		
		boolean error = false;
		String errorString = "";
		
		if(klantGebruikersnaam == null || datum == null ||
				klantGebruikersnaam.isEmpty() || datum.isEmpty()) {
			error = true;
			errorString += "Vul alle velden in! <br />";
		}
		
		Calendar datumCalendar = null;
		if(datum != null) {
			datumCalendar = Calendar.getInstance();
			
			// Formatting datum en tijd
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			df.setTimeZone(TimeZone.getDefault());
			try{
				datumCalendar.setTime(df.parse(datum));
			}catch(ParseException pe) {
				error = true;
				errorString += "Datum is niet in een geldige notatie, gebruik DD-MM-YYYY. Of tijdstip is niet in 24-uurs HH:MM notatie <br />";
			}
			
		}
		
		Klant klant = ServiceProvider.getKlantService().getKlantByGebruikersnaam(klantGebruikersnaam);
		if(klant == null) {
			error = true;
			errorString += "Selecteer een klant <br />";
		}
		
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("addfactuur.jsp");
			
			rd.forward(req, resp);
			
			return;
		}
		
		
		
		Factuur factuur = new Factuur();
		factuur.setKlant(klant);
		factuur.setDatum(datumCalendar);
		factuur.setBetaald(false);
		
		
		int factuurnummer = ServiceProvider.getFactuurService().addFactuur(factuur);
		
		if(factuurnummer > 0) {
			// Gelukt, nu onderdelen
			
		}
		
		
	}

}
