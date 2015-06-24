package nl.atd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Parkeerplek;
import nl.atd.service.ServiceProvider;

/**
 * Servlet implementation class AjaxPlekkenServlet
 */
@WebServlet("/AjaxPlekkenServlet")
public class AjaxPlekkenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxPlekkenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!AuthHelper.isAdmin(req.getSession())) {return;}
		
		String vanDatum = req.getParameter("vanDatum");
		String vanTijdstip = req.getParameter("vanTijdstip");
		
		String totDatum = req.getParameter("totDatum");
		String totTijdstip = req.getParameter("totTijdstip");
		
		Calendar van = Calendar.getInstance();
		Calendar tot = Calendar.getInstance();
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		df.setTimeZone(TimeZone.getDefault());
		try{
			van.setTime(df.parse(vanDatum + " " + vanTijdstip));
			tot.setTime(df.parse(totDatum + " " + totTijdstip));
		}catch(ParseException pe) {
			pe.printStackTrace();
			
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();  
			out.print("[]");
			out.flush();
			
			return;
		}
		
		
		ArrayList<Parkeerplek> parkeerplekken = ServiceProvider.getParkeerplekService().getAlleVrijePlekkenTussenVanTot(van, tot);
		
		String response = "";
		JSONArray plekkenArray = new JSONArray();
		
		for(Parkeerplek p: parkeerplekken) {
			JSONObject plekObject = new JSONObject();
			String rij = String.valueOf(p.getRij());
			String plek = String.valueOf(p.getPlek());
			
			try {
				plekObject.put("rij", rij);
				plekObject.put("plek", plek);
				plekkenArray.put(plekObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
							
		}
		
		response = plekkenArray.toString();
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();  
		out.print(response);
		out.flush();
	}

}
