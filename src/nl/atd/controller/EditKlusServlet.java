package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.service.ServiceProvider;

public class EditKlusServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String klaar = req.getParameter("klaar");
		String omschrijving = req.getParameter("omschrijving");
		String monteur = req.getParameter("monteur");
		String uren = req.getParameter("uren");
		String klant = req.getParameter("klant");
		String auto = req.getParameter("auto");
		
		boolean error = false;
		String errorString = "";
		
		int urenNumeriek = 0;
		
		if(monteur == null){
			
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
			
			RequestDispatcher rd = req.getRequestDispatcher("editklus.jsp");
			
			rd.forward(req, resp);
			
			return;
		}
		
		//TODO Klus ophalen en aanpassing methodes maken in KlusDAO
		
	}
	
}
