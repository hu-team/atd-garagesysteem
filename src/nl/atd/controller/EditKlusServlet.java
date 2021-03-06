package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Auto;
import nl.atd.model.Klus;
import nl.atd.model.Monteur;
import nl.atd.service.ServiceProvider;

public class EditKlusServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(!AuthHelper.isAdmin(req.getSession()) && !AuthHelper.isMonteur(req.getSession())) {return;}
	
		String type = req.getParameter("type");
		String uren = req.getParameter("uren");
		String omschrijving = req.getParameter("omschrijving");
		String klaar = req.getParameter("klaar");
		
		
		String monteur = req.getParameter("monteur");
		
		int id = (int) req.getSession().getAttribute("klusid");
		
		boolean error = false;
		String errorString = "";
		
		int urenNumeriek = 0;
		
		if(type == null || omschrijving == null || type.trim().isEmpty() || omschrijving.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in! <br />";
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
			
			RequestDispatcher rd = req.getRequestDispatcher("editklus.jsp?id=" + id);
			
			rd.forward(req, resp);
			
			return;
		}
		
		Monteur mont = (monteur == null ? null : ServiceProvider.getMonteurService().getMonteurByGebruikersnaam(monteur));
		
		Klus klus = ServiceProvider.getKlusService().getKlusOpId(id);
		
		klus.setMonteur(mont);
		klus.setOmschrijving(omschrijving);
		klus.setType(type);
		klus.setUren(urenNumeriek);
		klus.setKlaar(klaar == null ? false : true);
		
		if(klus.isKlaar()) {
			// Zet auto onderhoud voltooid
			Auto auto = klus.getAuto();
			auto.setLaatsteBeurt(klus.getCalendar());
			// Laatste beurt bijwerken
			ServiceProvider.getAutoService().setAutoLaatsteBeurt(auto);
		}
		
		ServiceProvider.getKlusService().editKlus(klus);
		
		req.getSession().removeAttribute("klusid");
		
		resp.sendRedirect(req.getContextPath() + "/secure/index.jsp");
	}
	
}
