package nl.atd.helper;

import java.io.IOException;
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
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
	
}
