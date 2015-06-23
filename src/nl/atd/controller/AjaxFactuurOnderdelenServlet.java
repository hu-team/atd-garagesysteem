package nl.atd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.atd.service.ServiceProvider;
import nl.atd.helper.AuthHelper;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.model.Reservering;

/**
 * Servlet implementation class AjaxFactuurOnderdelenServlet
 */
@WebServlet("/AjaxFactuurOnderdelenServlet")
public class AjaxFactuurOnderdelenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tmpklant = request.getParameter("klant");
		Klant klant = ServiceProvider.getKlantService().getKlantByGebruikersnaam(tmpklant);
		List<Klus> klussen = ServiceProvider.getKlusService().getKlussenVanKlant(klant);
		List<Reservering> reservering = ServiceProvider.getReserveringService().getReserveringenVanKlant(klant);
		JSONArray factuurArray = new JSONArray();
		JSONArray klussenArray = new JSONArray();
		JSONArray reserveringArray = new JSONArray();
		String resp = "";
		
		if(AuthHelper.isAdmin(request.getSession())) {
			for(Klus k : klussen) {
				JSONObject klusObject = new JSONObject();
				
				if(k.isKlaar()) {
					int klusid = ServiceProvider.getKlusService().getKlusIdOpKlus(k);
					try {
						klusObject.put("id", klusid);
						klusObject.put("type", k.getType());
						klusObject.put("uren", k.getUren());
						klusObject.put("prijs", k.getTotaalPrijs());
						klussenArray.put(klusObject);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
			}
			
			for(Reservering r : reservering) {
				JSONObject reserveringObject = new JSONObject();
				int reserveringid = ServiceProvider.getReserveringService().getReserveringId(r.getVan(), r.getParkeerplek());
				try {
					reserveringObject.put("id", reserveringid);
					reserveringObject.put("prijs", r.getTotaalPrijs());
					reserveringArray.put(reserveringObject);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			factuurArray.put(klussenArray);
			factuurArray.put(reserveringArray);
			
			resp = factuurArray.toString();
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();  
			out.print(resp);
			out.flush();
			
		}
		
	}
}
