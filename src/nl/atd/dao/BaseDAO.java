package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import nl.atd.helper.DatabaseHelper;

public abstract class BaseDAO {
	private static Connection connection;
	
	/**
	 * Get connection
	 * @return connection
	 * @throws Exception
	 */
	protected Connection getConnection() throws Exception {
		if(connection == null) connection = DatabaseHelper.getDatabaseConnection();
		return connection;
	}
	
	/**
	 * Get prepared statement
	 * @param sql
	 * @return prepared statement
	 * @throws Exception
	 */
	protected PreparedStatement getPreparedStatement(String sql) throws Exception {
		return this.getConnection().prepareStatement(sql);
	}
	
	/**
	 * Close recent connection
	 */
	protected void closeConnection() {
		try{
			if(connection != null) connection.close();
		}catch(Exception e){} 
	}
}
