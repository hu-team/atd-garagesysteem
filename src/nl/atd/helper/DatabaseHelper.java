package nl.atd.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;

public class DatabaseHelper {
	private static Connection connection;
	
	public static Connection getDatabaseConnection() {
		return connection;
	}

	public static void startup(ServletContext servletContext) throws SQLException, ClassNotFoundException {
		// Eerst class laden
		Class.forName("org.sqlite.JDBC");
		
		String dbPath = servletContext.getRealPath("/database.db");
		
		connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		
		System.out.println("JA, GESTART");
		System.out.println(connection.toString());
	}
	
	public static void shutdown() throws SQLException {
		connection.close();
	}
}
