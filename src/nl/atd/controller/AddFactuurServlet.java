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
import nl.atd.model.Factuuronderdeel;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.model.Reservering;
import nl.atd.service.ServiceProvider;

public class AddFactuurServlet extends HttpServlet {
	private static final long serialVersionUID = 1368099967044309586L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		String klantGebruikersnaam = req.getParameter("klant");
		String datum = req.getParameter("datum");
		String klusid = req.getParameter("klusid");
		String reserveringid = req.getParameter("reserveringid");
		
		
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
			Factuuronderdeel factuuronderdeel = new Factuuronderdeel();
			Klus factuurklus = ServiceProvider.getKlusService().getKlusOpId(Integer.parseInt(klusid));
			Reservering factuurreservering = ServiceProvider.getReserveringService().getReserveringOpId(Integer.parseInt(reserveringid));
			
			if(factuurklus != null) {
				factuuronderdeel.setKlus(factuurklus);
			}
			
			if(factuurreservering != null) {
				factuuronderdeel.setReservering(factuurreservering);
			}
			
			
		}
		
		
	}  

}
