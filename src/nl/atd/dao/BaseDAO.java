package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import nl.atd.helper.DatabaseHelper;

public abstract class BaseDAO {
	/**
	 * Get connection
	 * @return connection
	 * @throws Exception
	 */
	protected Connection getConnection() throws Exception {
		return DatabaseHelper.getDatabaseConnection();
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
}
