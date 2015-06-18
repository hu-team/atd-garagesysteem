package nl.atd.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseHelper {
	
	// Statische gegevens in geval van nood!
	private static final String DB_URL		= "jdbc:mysql://localhost/atd";
	private static final String DB_USER		= "root";
	private static final String DB_PASS		= "";
	private static final String DB_DRIVER	= "com.mysql.jdbc.Driver";
	
	// Productie server
	// Gebuikersnaam: atd
	// Wachtwoord: hvxDS5UfyQvZ7GZm
	// Database: atd
	
	public static Connection getDatabaseConnection() throws Exception {
		String url = DB_URL;
		String username = DB_USER;
		String password = DB_PASS;
		
		Properties p = ConfigHelper.getProperties();
		if(p.containsKey("mysql.host") && p.containsKey("mysql.username") && p.containsKey("mysql.password") && p.containsKey("mysql.database")) {
			// Valide informatie in config bestand
			url = "jdbc:mysql://" + p.getProperty("mysql.host") + "/" + p.getProperty("mysql.database");
			username = p.getProperty("mysql.username");
			password = p.getProperty("mysql.password");
		}
		
		Class.forName(DB_DRIVER).newInstance();
		return DriverManager.getConnection(url, username, password);
	}
	
	public static Connection getDatabaseConnectionWith(String host, String database, String username, String password) throws Exception {
		String url = "jdbc:mysql://" + host + "/" + database;
		Class.forName(DB_DRIVER).newInstance();
		return DriverManager.getConnection(url, username, password);
	}
}
