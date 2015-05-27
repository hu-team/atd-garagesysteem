package nl.atd.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {
	private static final String DB_URL		= "jdbc:mysql://localhost/atd";
	private static final String DB_USER		= "root";
	private static final String DB_PASS		= "";
	private static final String DB_DRIVER	= "com.mysql.jdbc.Driver";
	
	// Productie server
	// Gebuikersnaam: atd
	// Wachtwoord: hvxDS5UfyQvZ7GZm
	// Database: atd
	
	public static Connection getDatabaseConnection() throws Exception {		
		Class.forName(DB_DRIVER).newInstance();
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
}
