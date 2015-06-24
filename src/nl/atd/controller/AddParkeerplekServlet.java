package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Parkeerplek;
import nl.atd.service.ServiceProvider;

public class AddParkeerplekServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(!AuthHelper.isAdmin(req.getSession())) {return;}
		
		String rij = req.getParameter("rij");
		String plek = req.getParameter("plek");
		
		boolean error = false;
		String errorString = "";

		int plekNumeriek = 0;
		char rijChar = 'A';
		
		if(rij == null || plek == null || rij.trim().isEmpty() || plek.trim().isEmpty()){
			error = true;
			errorString += "Niet alle velden zijn ingevuld. <br />";
		}
		
		try{
			plekNumeriek = Integer.parseInt(plek);
		}catch(NumberFormatException nfe) {
			error = true;
			errorString += "Plek mag alleen maar nummer bevatten. <br />";
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
			
			RequestDispatcher rd = req.getRequestDispatcher("addparkeerplek.jsp");
			
			rd.forward(req, resp);
			
			return;
		}
		
		Parkeerplek parkeerplek = new Parkeerplek(rijChar, plekNumeriek);
		
		if(ServiceProvider.getParkeerplekService().addParkeerplek(parkeerplek)){
			resp.sendRedirect(req.getContextPath() + "/secure/parkeerplekoverzicht.jsp?done=1");
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Opslaan is mislukt.");
			
			RequestDispatcher rd = req.getRequestDispatcher("addparkeerplek.jsp");
			
			rd.forward(req, resp);	
		}
			
	}
}
