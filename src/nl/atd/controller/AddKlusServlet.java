package nl.atd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddKlusServlet extends HttpServlet{
	private static final long serialVersionUID = 4939994110043865017L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String type = req.getParameter("type");
		String uren = req.getParameter("uren");
		String omschrijving = req.getParameter("omschrijving");
		
	}
}
