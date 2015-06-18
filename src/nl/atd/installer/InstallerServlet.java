package nl.atd.installer;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.ConfigHelper;
import nl.atd.helper.DatabaseHelper;
import nl.atd.helper.InstallHelper;

public class InstallerServlet extends HttpServlet {
	private static final long serialVersionUID = 4204707720442475234L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("save") != null) {
			try{
				File configFile = new File(req.getServletContext().getResource("/WEB-INF/config.xml").toURI());
				ConfigHelper.getProperties().put("installed", "true");
				ConfigHelper.saveConfigFile(configFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		resp.sendRedirect(req.getContextPath() + "/");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		String mysqlServer = req.getParameter("mysql_server");
		String mysqlUsername = req.getParameter("mysql_username");
		String mysqlPassword = req.getParameter("mysql_password");
		String mysqlDatabase = req.getParameter("mysql_database");
		
		InstallHelper installHelper = new InstallHelper(req.getServletContext());
		
		if(mysqlServer == null || mysqlUsername == null || mysqlPassword == null || mysqlDatabase == null) {
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Niet alle velden zijn ingevuld!");
			
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
			return;
		}
		
		// Start met connectie maken
		try{
			Connection c = DatabaseHelper.getDatabaseConnectionWith(mysqlServer, mysqlDatabase, mysqlUsername, mysqlPassword);
			
			// Lijkt nog goed te gaan
			// Settings in config zetten
			Properties props = ConfigHelper.getProperties();
			props.put("mysql.host", mysqlServer);
			props.put("mysql.username", mysqlUsername);
			props.put("mysql.password", mysqlPassword);
			props.put("mysql.database", mysqlDatabase);
			
			c.close();
			
			// Nu laten wij database installeren
			boolean gelukt = installHelper.doInstall();
			
			if(gelukt) {
				// Ziet er goed uit
				// Toon voltooid scherm
				req.setAttribute("output", true);
				req.setAttribute("outputString", "Database installeren voltooid!");
				req.setAttribute("installed", true);
			}else{
				req.setAttribute("error", true);
				req.setAttribute("errorString", "Fout bij installatie van tabellen, mogelijk geen permissie? Bekijk de Tomcat Console!");
			}
		}catch(Exception e) {
			// Niet gelukt
			req.setAttribute("error", true);
			req.setAttribute("errorString", "Fout met verbinden MySQL Server: <br />" + e.getMessage() + "<br />Stacktrace:<br /><small>" +  Arrays.toString(e.getStackTrace()) + "</small>");
			
			
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, resp);
		return;
	}
}
