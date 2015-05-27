package nl.atd.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.model.Auto;
import nl.atd.service.ServiceProvider;

public class AddAutoServlet extends HttpServlet {
	private static final long serialVersionUID = -6098825510769745864L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String klant = req.getParameter("klant");
		String merk = req.getParameter("merk");
		String model = req.getParameter("model");
		String bouwjaar = req.getParameter("bouwjaar");
		String kenteken = req.getParameter("kenteken");
		
		boolean error = false;
		String errorString = "";
		
		int bouwjaarNumeriek = 0;
		
		if(merk == null || model == null || bouwjaar == null || kenteken == null || 
				merk.trim().isEmpty() || model.trim().isEmpty() || bouwjaar.trim().isEmpty() || kenteken.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in! <br />";
		}
		
		// Eerst - vervangen, spaties vewijderen, en uppercase
		kenteken = kenteken.trim().replaceAll(Pattern.quote("-"), "").toUpperCase();
		
		// Kenteken controleren
		if(kenteken.length() != 6) {
			error = true;
			errorString += "Kenteken moet bestaan uit 6 karakters! <br />";
		}
		
		// Als de klant niet opgegeven is, of niet bestaat
		if(klant == null || klant.trim().isEmpty() || ServiceProvider.getKlantService().getKlantByGebruikersnaam(klant) == null) {
			resp.sendRedirect(req.getContextPath() + "/secure/klantoverzicht.jsp");
		}
		
		try{
			bouwjaarNumeriek = Integer.parseInt(bouwjaar);
		}catch(NumberFormatException nfe) {
			error = true;
			errorString += "Het aantal moet numeriek zijn!<br />";
		}
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("autooverzicht.jsp?klant=" + klant);
			
			rd.forward(req, resp);
			
			return;
		}
		
		Auto auto = new Auto(merk, model, bouwjaarNumeriek, null);
		auto.setKenteken(kenteken);
		
		if(ServiceProvider.getAutoService().addAuto(klant, auto)) {
			resp.sendRedirect(req.getContextPath() + "/secure/autooverzicht.jsp?klant=" + klant + "&done=1");
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Opslaan is mislukt, mogelijk bestaat auto al.");
			
			RequestDispatcher rd = req.getRequestDispatcher("autooverzicht.jsp?klant=" + klant);
			
			rd.forward(req, resp);
			
			return;
		}
	}
	
}
