package nl.atd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Klus;
import nl.atd.service.ServiceProvider;

public class AjaxKlussenServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long start = 0l;
		long end = 0l;
		
		String startString = req.getParameter("start");
		String endString = req.getParameter("end");
		
		String response = "";
		
		if(startString != null || endString != null) {
			try{
				start = Long.parseLong(startString);
				end = Long.parseLong(endString);
			}catch(NumberFormatException nfe) {
			}
		}
		
		JSONArray klussenArray = new JSONArray();
		
		// Als het goed is
		if(start > 0 && end > 0 && (AuthHelper.isAdmin(req.getSession()) || AuthHelper.isMonteur(req.getSession()))) {
			// Dan halen wij klussen op
			ArrayList<Klus> klussen = ServiceProvider.getKlusService().getKlussen();
			
			for(int i = 0; i < klussen.size(); i++) {
				JSONObject klusObject = new JSONObject();
				
				try {
					klusObject.put("title", klussen.get(i).getType() + " - " + klussen.get(i).getAuto().getMerk());
					klusObject.put("start", (klussen.get(i).getCalendar().getTimeInMillis() / 1000));
					klusObject.put("end", (klussen.get(i).getCalendar().getTimeInMillis() / 1000) + (60 * 60));
					
					klussenArray.put(klusObject);
				} catch (JSONException e) {
					// Negeer
				}
			}
			
			response = klussenArray.toString();
		}
		
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();  
		out.print(response);
		out.flush();
	}
	
}
