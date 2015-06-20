package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.helper.MailHelper;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.service.ServiceProvider;

public class StuurAutoHerinneringServlet extends HttpServlet {
	private static final long serialVersionUID = -7467236184038600900L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!AuthHelper.isAdmin(req.getSession())) {
			resp.sendRedirect(req.getContextPath()+"/secure/index.jsp"); return;
		}
		
		String klantGebruikersnaam = req.getParameter("klant");
		String autoKenteken = req.getParameter("auto");
		String bericht = req.getParameter("bericht");
		
		Klant klant = null;
		Auto auto = null;
		
		boolean error = false;
		String errorString = "";
		
		if(klantGebruikersnaam == null || autoKenteken == null || bericht == null ||
				klantGebruikersnaam.trim().isEmpty() || autoKenteken.trim().isEmpty() || bericht.trim().isEmpty()) {
			error = true;
			errorString += "Vul alle velden in! <br />";
		}else{
			klant = ServiceProvider.getKlantService().getKlantByGebruikersnaam(klantGebruikersnaam);
			auto = ServiceProvider.getAutoService().getAutoOpKenteken(autoKenteken);
		}
		
		if(klant == null || auto == null) {
			/* Loop ons aub niet te hacken, bedankt, met vriendelijke groet, meneer de clown */
			/* OJA, NSA is nu onderweg! */
			/* Nee, is geen grap! */
			/* Wel leuk dat dit gelezen wordt! */
			/* Oke, genoeg nu, verder met code dan */
			/* Bijna... */
			/* CantStopException */
			error = true;
			errorString += "Er is een overige fout opgetreden! <br />";
		}
		
		if(klant.getEmail() == null || klant.getEmail().trim().isEmpty()) {
			error = true;
			errorString += "Er is nog geen e-mail adres bekend bij de klant! <br />";
		}
		
		
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("stuurautoherinnering.jsp?auto=" + autoKenteken);
			
			rd.forward(req, resp);
			
			return;
		}
		
		// Versturen
		boolean status = MailHelper.sendEmailNaarKlant(klant, "Herinnering autonderhoud " + auto.getMerk() + " " + auto.getModel(), bericht);
		
		if(status) { 
			resp.sendRedirect(req.getContextPath() + "/secure/stuurautoherinnering.jsp?done=1&auto=" + autoKenteken);
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Fout met verzenden, mogelijk is er een probleem op de server <br />");
			
			RequestDispatcher rd = req.getRequestDispatcher("stuurautoherinnering.jsp?auto=" + autoKenteken);
			
			rd.forward(req, resp);
		}
	}
}
