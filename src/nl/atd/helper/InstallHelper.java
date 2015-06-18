package nl.atd.helper;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.servlet.ServletContext;

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
	
	public boolean doInstall() {
		try{
			return this.installDatabase();
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	private boolean installDatabase() throws Exception {
		// Connection maken
		Connection connection = DatabaseHelper.getDatabaseConnection();
		
		// Laadt SQL In.
		File file = new File(this.context.getResource("/WEB-INF/install.sql").toURI());
		
		// Uitvoeren van SQL
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
		
		return true;
	}
	

	private boolean isDatabaseInstalled() {
		try{
			Connection connection = DatabaseHelper.getDatabaseConnection();
			// TODO tabelvalidatie
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
}
