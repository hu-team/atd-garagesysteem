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
import nl.atd.model.Parkeerplek;
import nl.atd.model.Reservering;
import nl.atd.service.ServiceProvider;

public class ReserveerParkeerplekKlantServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String rij = req.getParameter("rij");
		String plek = req.getParameter("plek");
		
		String vanDatum = req.getParameter("vanDatum");
		String vanTijdstip = req.getParameter("vanTijdstip");
		
		String totDatum = req.getParameter("totDatum");
		String totTijdstip = req.getParameter("totTijdstip");
		
		String auto = req.getParameter("auto");
		
		boolean error = false;
		String errorString = "";
		
		int plekNumeriek = 0;
		char rijChar = 'A';
		
		if(rij == null || plek == null || vanDatum == null || totDatum == null || vanTijdstip == null || totTijdstip == null
				|| rij.trim().isEmpty() || plek.trim().isEmpty() || vanDatum.trim().isEmpty() || totDatum.trim().isEmpty()
				|| vanTijdstip.trim().isEmpty() || totTijdstip.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in! <br />";
		}
		
		if(auto == null || auto.trim().isEmpty() || ServiceProvider.getAutoService().getAutoOpKenteken(auto) == null){
			error = true;
			errorString += "Selecteer een geldige auto! <br />";
		}
		
		Calendar vanCalendar = Calendar.getInstance();
		Calendar totCalendar = Calendar.getInstance();
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		df.setTimeZone(TimeZone.getDefault());
		try{
			vanCalendar.setTime(df.parse(vanDatum + " " + vanTijdstip));
			totCalendar.setTime(df.parse(totDatum + " " + totTijdstip));
		}catch(ParseException pe) {
			error = true;
			errorString += "Datum is niet in een geldige notatie, gebruik DD-MM-YYYY. Of tijdstip is niet in 24-uurs HH:MM notatie <br />";
		}
		
		try{
			plekNumeriek = Integer.parseInt(plek);
		}catch(NumberFormatException nfe) {
			error = true;
			errorString += "<br />";
		}
		
		if(rij.matches(".*\\d+.*")){
			error = true;
			errorString += "Rij bevat een nummer. <br />";
		}else{
			rijChar = rij.charAt(0);
		}
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("reserveerparkeerplekklant.jsp");
			
			rd.forward(req, resp);
			
			return;
		}
		
		Klant kl = ServiceProvider.getKlantService().getKlantByGebruikersnaam(AuthHelper.getGebruikersnaam(req.getSession()));
		Auto au = ServiceProvider.getAutoService().getAutoOpKenteken(auto);
		
		Parkeerplek parkeerplek = ServiceProvider.getParkeerplekService().getParkeerplekOpPlekEnRij(rijChar, plekNumeriek);
		
		Reservering reservering = new Reservering(kl, au);
		reservering.setVan(vanCalendar);
		reservering.setTot(totCalendar);
		if(ServiceProvider.getReserveringService().addReservering(reservering, parkeerplek)){
			resp.sendRedirect(req.getContextPath() + "/secure/index.jsp");
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Opslaan is mislukt.");
			
			RequestDispatcher rd = req.getRequestDispatcher("reserveerparkeerplekklant.jsp");
			
			rd.forward(req, resp);
		}
	}
}
