package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Artikel;
import nl.atd.service.ServiceProvider;

public class EditArtikelServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(!AuthHelper.isAdmin(req.getSession())) {return;}
		
		String code = req.getParameter("code");
		String naam = req.getParameter("naam");
		String aantal = req.getParameter("aantal");
		String prijs = req.getParameter("prijs");
		
		boolean error = false;
		String errorString = "";
		
		int aantalNumeriek = 0;
		double prijsNumeriek = 0.0;
		
		if(naam == null || aantal == null || prijs == null || 
				naam.trim().isEmpty() || aantal.trim().isEmpty() || prijs.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in! <br />";
		}else{
			try{
				aantalNumeriek = Integer.parseInt(aantal);
			}catch(NumberFormatException nfe) {
				error = true;
				errorString += "Het aantal moet numeriek zijn!<br />";
			}
			
			if(aantalNumeriek < 0) {
				error = true;
				errorString += "Het aantal moet positief zijn!<br />";
			}
			
			try{
				prijsNumeriek = Double.parseDouble(prijs);
			}catch(NumberFormatException nfe) {
				error = true;
				errorString += "Het prijs moet numeriek zijn! <br />";
			}
		}
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("editartikel.jsp");
			
			rd.forward(req, resp);
			
			return;
		}
		
		Artikel artikel = new Artikel(naam, aantalNumeriek);
		
		artikel.setCode(code);
		artikel.setPrijs(prijsNumeriek);
		
		ServiceProvider.getArtikelService().editArtikel(artikel);
		
		resp.sendRedirect(req.getContextPath() + "/secure/artikeloverzicht.jsp");
		
	}
}
