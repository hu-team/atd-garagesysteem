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
import nl.atd.model.Factuur;
import nl.atd.model.Klant;
import nl.atd.service.ServiceProvider;

public class StuurFactuurHerinneringServlet extends HttpServlet {
	private static final long serialVersionUID = -7467236184038600900L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!AuthHelper.isAdmin(req.getSession())) {
			resp.sendRedirect(req.getContextPath()+"/secure/index.jsp"); return;
		}
		
		String factuurnummerString = req.getParameter("factuur");
		String bericht = req.getParameter("bericht");
		
		int factuurnummer = 0;
		
		Factuur factuur = null;
		
		boolean error = false;
		String errorString = "";
		
		if(factuurnummerString == null || bericht == null || factuurnummerString.trim().isEmpty() || bericht.trim().isEmpty()) {
			error = true;
			errorString += "Vul alle velden in! <br />";
		}else{
			try{
				factuurnummer = Integer.parseInt(factuurnummerString);
				
				factuur = ServiceProvider.getFactuurService().getFactuurOpNummer(factuurnummer);
			}catch(NumberFormatException nfe) {
				error = true;
				errorString += "Ga terug en selecteer de factuur opnieuw! <br />";				
			}
		}
		
		if(factuur == null) {
			error = true;
			errorString += "Er is een overige fout opgetreden! <br />";
		}
		
		if(factuur.getKlant() == null || factuur.getKlant().getEmail() == null || factuur.getKlant().getEmail().trim().isEmpty()) {
			error = true;
			errorString += "Er is nog geen e-mail adres bekend bij de klant! <br />";
		}
		
		
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("stuurfactuurherinnering.jsp?nummer=" + factuurnummerString);
			
			rd.forward(req, resp);
			
			return;
		}
		
		// Versturen
		boolean status = MailHelper.sendEmailNaarKlant(factuur.getKlant(), "Betalingsherinnering voor factuur #" + factuurnummer, bericht);
		
		if(status) { 
			resp.sendRedirect(req.getContextPath() + "/secure/stuurfactuurherinnering.jsp?done=1&nummer=" + factuurnummer);
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Fout met verzenden, mogelijk is er een probleem op de server <br />");
			
			RequestDispatcher rd = req.getRequestDispatcher("stuurfactuurherinnering.jsp?nummer=" + factuurnummer);
			
			rd.forward(req, resp);
		}
	}
}
