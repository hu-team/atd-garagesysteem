package nl.atd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.service.ServiceProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AjaxKlantAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 6787699084181467169L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String klantGebruikersnaam = req.getParameter("klant");
		
		Klant klant = ServiceProvider.getKlantService().getKlantByGebruikersnaam(klantGebruikersnaam);
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		if(klantGebruikersnaam == null || klantGebruikersnaam.trim().isEmpty() || klant == null) {
			out.print(new JSONArray());
			out.flush();
			return;
		}
		
		// Alle autos ophalen
		ArrayList<Auto> autos = ServiceProvider.getAutoService().getAutosVanKlant(klant.getGebruikersnaam());
		
		JSONArray autoArray = new JSONArray();
		
		for(Auto auto: autos) {
			JSONObject autoObject = new JSONObject();
			
			try {
				autoObject.put("merk", auto.getMerk());
				autoObject.put("model", auto.getModel());
				autoObject.put("bouwjaar", auto.getBouwjaar());
				autoObject.put("kenteken", auto.getKenteken());
				
				autoArray.put(autoObject);
			}catch(JSONException je) {
				
			}
		}
		
		out.print(autoArray);
		out.flush();
	}
}
