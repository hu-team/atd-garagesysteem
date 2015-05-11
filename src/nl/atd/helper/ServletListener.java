package nl.atd.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Start logger
		Logger logger = Logger.getLogger("to4");
		
		ConsoleHandler ch = new ConsoleHandler();
		logger.addHandler(ch);
		
		try{
			FileHandler fh = new FileHandler(sce.getServletContext().getRealPath("/log.txt"));
			
			logger.addHandler(fh);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		/*
		 * Database startup
		 */
		try {
			DatabaseHelper.startup(sce.getServletContext());
		} catch(SQLException se) {
			logger.warning("Cannot connect to DB! " + se.toString());
			System.exit(1);
		} catch(ClassNotFoundException cnfe) {
			logger.warning("SQLite class not found, not in buildpath????");
			System.exit(1);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			DatabaseHelper.shutdown();
			Thread.sleep(1500);
		} catch (SQLException | InterruptedException e) {
			Logger.getLogger("to4").warning("Cannot close DB!");
		}
	}
	
}
