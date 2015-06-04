package nl.atd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
	private static final long serialVersionUID = 114389710325697925L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String startString = req.getParameter("start");
		String endString = req.getParameter("end");
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		// Als er iets niet klopt, direct stoppen met inhoud geven.
		if(start == null || end == null) return;
		try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			start.setTime(df.parse(startString));
			end.setTime(df.parse(endString));
		}catch(ParseException pe) {
			return;
		}
		
		String response = "";
		
		JSONArray klussenArray = new JSONArray();
		
		// Als het goed is
		if((AuthHelper.isAdmin(req.getSession()) || AuthHelper.isMonteur(req.getSession()))) {
			// Dan halen wij klussen op
			ArrayList<Klus> klussen = ServiceProvider.getKlusService().getKlussenTussen(start, end);
			
			for(int i = 0; i < klussen.size(); i++) {
				JSONObject klusObject = new JSONObject();
				
				Klus klus = klussen.get(i);
				
				try {
					String titel = klus.getKlant().getNaam() + "<br />";
					titel += "<span class='kenteken kentekenagenda'>" + klus.getAuto().getKenteken().toUpperCase() + "</span><br />";
					
					if(klus.getUren() == 0) {
						titel += "<i>Uren nog onbekend</i>";
					}
					
					klusObject.put("title", titel);
					
					// Formatting tijd in RFC 3339
					String klusStartString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(klus.getCalendar().getTime());
					
					Calendar klusEnd = (Calendar)klus.getCalendar().clone();
					if(klus.getUren() == 0) {
						klusEnd.add(Calendar.HOUR, 1);
					}else{
						klusEnd.add(Calendar.HOUR, klus.getUren());
					}
					
					String klusEndString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(klusEnd.getTime());
					
					klusObject.put("start", klusStartString);
					klusObject.put("end", klusEndString);
					klusObject.put("url", req.getContextPath() + "/secure/editklus.jsp?id=" + ServiceProvider.getKlusService().getKlusIdOpKlus(klus));
					
					// Kleuren bepalen
					klusObject.put("textColor", "#ffffff");
					String backgroundColor = "#C2B52D";
					
					if(klus.getCalendar().before(Calendar.getInstance())) {
						if(!klus.isKlaar()) {
							// Als het voor NU is, en nog niet afgerond is, dan rood!
							backgroundColor = "#DB753D";
						}else{
							// Anders groen
							backgroundColor = "#4CBA43";
						}
					}
					
					klusObject.put("backgroundColor", backgroundColor);
					
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
