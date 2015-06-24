package nl.atd.helper;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Scanner;

import javax.servlet.ServletContext;

import nl.atd.model.Klus;
import nl.atd.service.ServiceProvider;

public class InstallHelper {
	private ServletContext context;
	
	public InstallHelper(ServletContext context) {
		this.context = context;
	}
	
	public boolean isInstalled() {
		// Try to load config
		try {
			File configFile = new File(this.context.getResource("/WEB-INF/config.xml").toURI());
			
			if(!ConfigHelper.loadConfigFile(configFile)) {
				return false;
			}
			
			if(!ConfigHelper.isValidConfig()) {
				return false;
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean doInstall(boolean structure) {
		try{
			return this.installDatabase(structure);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Install database structuur en basis
	 * @param structure
	 * @return
	 * @throws Exception
	 */
	private boolean installDatabase(boolean data) throws Exception {
		// Connection maken
		Connection connection = DatabaseHelper.getDatabaseConnection();
		
		// Laadt SQL In.
		File file = new File(this.context.getResource("/WEB-INF/install.sql").toURI());
		
		// Uitvoeren van SQL
		this.runSqlFile(connection, file);
		
		// Data genereren
		if(data) {
			File sFile = new File(this.context.getResource("/WEB-INF/base.sql").toURI());
			this.runSqlFile(connection, sFile);
			
			// Nu nog de klussen
			Klus klus = new Klus(ServiceProvider.getKlantService().getKlantByGebruikersnaam("e.oegema"), ServiceProvider.getAutoService().getAutoOpKenteken("43LSF3"));
			klus.setCalendar(Calendar.getInstance());
			klus.setUren(3);
			klus.setMonteur(ServiceProvider.getMonteurService().getMonteurByGebruikersnaam("aarnoudboekema"));
			klus.setKlaar(false);
			klus.setType("APK Controle");
			klus.setOmschrijving("APK Controle, nog niet eerder uitgevoerd.");
			ServiceProvider.getKlusService().addKlus(klus);
		}
		
		connection.close();
		
		return true;
	}
	
	/**
	 * Run SQL from file (jaman)
	 * @param connection
	 * @param file
	 * @throws Exception
	 */
	private void runSqlFile(Connection connection, File file) throws Exception {
		Scanner scanner = new Scanner(file);
		scanner.useDelimiter(";");
		
		Statement statement = null;
		while(scanner.hasNext()) {
			// SQL Lijn
			String sqlString = scanner.next() + ";";
			if(sqlString.isEmpty()) continue;
			
			// Uitvoeren
			try{
				statement = connection.createStatement();
				statement.execute(sqlString);
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				try{
					if(statement != null) statement.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				statement = null;
			}
		}
		
		scanner.close();
	}
	
}
