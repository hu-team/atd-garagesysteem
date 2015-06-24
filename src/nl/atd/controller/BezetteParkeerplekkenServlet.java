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

import nl.atd.helper.AuthHelper;


public class BezetteParkeerplekkenServlet extends HttpServlet{
	private static final long serialVersionUID = 1762750467936644308L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(!AuthHelper.isAdmin(req.getSession())) {return;}
		
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
			newDatum.setTime(df.parse("01-" + datum));
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
		
		req.setAttribute("datum", newDatum);
		req.getRequestDispatcher("bezetteparkeerplekoverzicht.jsp").forward(req, resp);
		
	}
}
