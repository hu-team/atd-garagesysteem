package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Artikel;
import nl.atd.model.Klus;
import nl.atd.model.Onderdeel;
import nl.atd.service.ServiceProvider;

public class AddOnderdeelServlet extends HttpServlet{
	private static final long serialVersionUID = 2216519323795325903L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(!AuthHelper.isAdmin(req.getSession()) && !AuthHelper.isMonteur(req.getSession())) {return;}
		
		String artikel = req.getParameter("artikel");
		String aantal = req.getParameter("aantal");

		boolean error = false;
		String errorString = "";
		
		int aantalNumeriek = 0;
		
		if(aantal == null || aantal.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in! <br />";
		}
		
		if(artikel == null || artikel.trim().isEmpty() || ServiceProvider.getArtikelService().getArtikelByCode(artikel) == null){
			error = true;
			errorString += "Selecteer een geldig artikel. <br />";
		}
		
		try{
			aantalNumeriek = Integer.parseInt(aantal);
		}catch(NumberFormatException nfe) {
			error = true;
			errorString += "Aantal moet numeriek zijn!<br />";
		}
		
		if(aantalNumeriek < 1) {
			error = true;
			errorString += "Aantal meer dan 0 zijn! En mag niet negatief zijn!<br />";
		}
		
		
		int id = (int)req.getSession().getAttribute("klusid");
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("onderdeeloverzicht.jsp?id=" + id);
			
			rd.forward(req, resp);
			
			return;
		}
		
		Artikel art = ServiceProvider.getArtikelService().getArtikelByCode(artikel);
		
		if(!art.kanAantalGebruiken(aantalNumeriek)){
			error = true;
			errorString += "U heeft niet genoeg " + art.getNaam() + " in voorraad.";
		}
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("onderdeeloverzicht.jsp?id=" + id);
			
			rd.forward(req, resp);
			
			return;
		}
		
		Klus klus = ServiceProvider.getKlusService().getKlusOpId(id);
		Onderdeel onderdeel = new Onderdeel(art, aantalNumeriek);
		
		art.gebruikAantal(aantalNumeriek);
		
		if(ServiceProvider.getOnderdeelService().addOnderdeel(onderdeel, klus)) {
			ServiceProvider.getArtikelService().editArtikel(art);
			resp.sendRedirect(req.getContextPath() + "/secure/onderdeeloverzicht.jsp?id=" + id);
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Opslaan is mislukt.");
			
			RequestDispatcher rd = req.getRequestDispatcher("onderdeeloverzicht.jsp?id=" + id);
			
			rd.forward(req, resp);
		}

	}
	
}
