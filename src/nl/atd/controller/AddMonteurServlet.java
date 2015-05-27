package nl.atd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Monteur;
import nl.atd.service.ServiceProvider;

public class AddMonteurServlet extends HttpServlet {
	private static final long serialVersionUID = -4075180201516812836L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String voornaam = req.getParameter("voornaam");
		String achternaam = req.getParameter("achternaam");
		String salarisnummer = req.getParameter("salarisnummer");
		String gebruikersnaam = req.getParameter("gebruikersnaam");
		String wachtwoord = req.getParameter("wachtwoord");
		String wachtwoord2 = req.getParameter("wachtwoord2");
		
		int salarisnummerNumeriek = 0;
		
		boolean error = false;
		String errorString = "";
		
		if(voornaam.trim().isEmpty() || achternaam.trim().isEmpty() || salarisnummer.trim().isEmpty() ||
				gebruikersnaam.trim().isEmpty() || wachtwoord.trim().isEmpty() || wachtwoord2.trim().isEmpty()){
			error = true;
			errorString += "Vul alle velden in!<br />";
		}else if(!wachtwoord.equals(wachtwoord2)) {
			error = true;
			errorString += "Wachtwoorden komen niet overeen!<br />";
		}
		
		try{
			salarisnummerNumeriek = Integer.parseInt(salarisnummer);
		}catch(NumberFormatException nfe) {
			error = true;
			errorString += "Het salarisnummer moet numeriek zijn!<br />";
		}
		
		if(error) {
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("addmonteur.jsp");
			rd.forward(req, resp);
			
			// Zorgen dat wij ook stoppen met verwerken van post!
			return;
		}
		
		
		Monteur monteur = new Monteur(voornaam + " " + achternaam, salarisnummerNumeriek);
		
		monteur.setGebruikersnaam(gebruikersnaam);
		monteur.setWachtwoord(AuthHelper.encryptWachtwoord(wachtwoord));
		
		if(ServiceProvider.getMonteurService().addMonteur(monteur)) {
			resp.sendRedirect(req.getContextPath() + "/secure/addmonteur.jsp?done=1");
		}else{
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Opslaan is mislukt, mogelijk bestaat monteur al.");
			
			req.setAttribute("monteur", monteur);
			
			RequestDispatcher rd = req.getRequestDispatcher("addmonteur.jsp");
			
			rd.forward(req, resp);
		}
		
	}
}
