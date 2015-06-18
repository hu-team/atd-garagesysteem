package nl.atd.helper;

import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
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
		
		// Config loader
		if(!ConfigHelper.isValidConfig()) {
			try{
				File configFile = new File(sce.getServletContext().getResource("/WEB-INF/config.xml").toURI());
				ConfigHelper.loadConfigFile(configFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// FileHandler toevoegen
		/*
		try{
			FileHandler fh = new FileHandler(sce.getServletContext().getRealPath("/log.txt"));
			
			logger.addHandler(fh);
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		*/
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Logger logger = Logger.getLogger("to4");
		
		// Alle handlers sluiten
		for(Handler handler: logger.getHandlers()) {
			handler.close();
		}
	}
	
}
