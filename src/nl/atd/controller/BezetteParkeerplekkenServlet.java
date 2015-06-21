package nl.atd.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BezetteParkeerplekkenServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String datum = req.getParameter("datum");
		
		boolean error = false;
		String errorString = "";
		
		if(datum == null || datum.trim().isEmpty()){
			error = true;
			errorString += "";
		}
		
		Calendar newDatum = Calendar.getInstance();
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		df.setTimeZone(TimeZone.getDefault());
		try{
			newDatum.setTime(df.parse(datum));
		}catch(ParseException pe) {
			error = true;
			errorString += "Datum is niet in een geldige notatie, gebruik DD-MM-YYYY. <br />";
		}
		
		if(error){
			req.setAttribute("error", error);
			req.setAttribute("errorString", errorString);
			
			RequestDispatcher rd = req.getRequestDispatcher("bezetteparkeerplek.jsp");
			
			rd.forward(req, resp);
			
			return;
		}
		
		req.getSession().setAttribute("datum", newDatum);
		resp.sendRedirect(req.getContextPath() + "/secure/bezetteparkeerplekkenoverzicht.jsp");
		
		//TODO ForEach in de JSTL pagina
		//in bezetteparkeerplekkenoverzicht.jsp
		//Eerst moet de Calendar object worden opgehaald van session. Calendar datum = (Calendar)req.getSession().getAttribute("datum");
		//daarna komt dan een JSTL: <forEach items="<%=ServiceProvider.getParkeerplekService().getParkeerplekOpDatum(datum); %>" var="parkeerplek"></forEach>
	}
}
