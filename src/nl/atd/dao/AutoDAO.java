package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.model.Auto;

public class AutoDAO extends BaseDAO {
	
	/**
	 * Get autos, convert to list met autos
	 * @param query
	 * @return array met autos
	 */
	private ArrayList<Auto> getAutos(String query) {
		ArrayList<Auto> autos = new ArrayList<Auto>();
		
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery(query);
			
			while(set.next()) {
				Calendar laatste = Calendar.getInstance();
				try{
					Timestamp ts = set.getTimestamp("laatste_beurt");
					if(ts != null) {
						laatste.setTimeInMillis(ts.getTime());
					}else{
						laatste = null;
					}
				}catch(SQLException se) {
					laatste = null;
				}
				
				Auto auto = new Auto(set.getString("merk"), set.getString("model"), set.getInt("bouwjaar"), laatste);
				
				try{
					auto.setKenteken(set.getString("kenteken"));
				}catch(SQLException se) {
					auto.setKenteken(null);
				}
				
				autos.add(auto);
			}
			
			this.closeConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return autos;
	}
	
	/**
	 * Get autoid met query
	 * @param query
	 * @return int autoid
	 */
	private int getAutoId(String query) {
		int nr = 0;
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery(query);
			
			while(set.next()) {
				nr = set.getInt("autoid");
			}
			
			this.closeConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nr;
	}
	
	/**
	 * Get alle autos
	 * @return array met autos
	 */
	public ArrayList<Auto> getAlleAutos() {
		return this.getAutos("SELECT *, UNIX_TIMESTAMP(auto.laatste_beurt) as laatstebeurt FROM auto");
	}
	
	/**
	 * Get alle autos van klant
	 * @param klantGebruikersnaam geef string om te filteren op auto's van bepaalde klant, null voor alle autos
	 * @return array met autos
	 */
	public ArrayList<Auto> getAutosVanKlant(String klantGebruikersnaam) {
		return this.getAutos("SELECT *, UNIX_TIMESTAMP(auto.laatste_beurt) as laatstebeurt FROM auto WHERE klant LIKE '" + klantGebruikersnaam + "'");
	}
	
	/**
	 * Get auto op id
	 * @param id
	 * @return auto of null
	 */
	public Auto getAutoOpId(int id) {
		ArrayList<Auto> autos = this.getAutos("SELECT *, UNIX_TIMESTAMP(auto.laatste_beurt) as laatstebeurt FROM auto WHERE autoid = " + id);
		if(autos.size() >= 1) return autos.get(0);
		return null;
	}
	
	/**
	 * Get auto op kenteken
	 * @param kenteken
	 * @return auto of null
	 */
	public Auto getAutoOpKenteken(String kenteken) {
		ArrayList<Auto> autos = this.getAutos("SELECT *, UNIX_TIMESTAMP(auto.laatste_beurt) as laatstebeurt FROM auto WHERE kenteken LIKE '"+kenteken+"'");
		if(autos.size() >= 1) return autos.get(0);
		return null;
	}
	
	/**
	 * Get AutoId op kenteken
	 * @param kenteken
	 * @return autoid
	 */
	public int getAutoIdOpKenteken(String kenteken) {
		return this.getAutoId("SELECT autoid FROM auto WHERE kenteken LIKE '"+kenteken+"'");
	}
	
	/**
	 * Add Auto
	 * @param klant
	 * @param auto
	 * @return gelukt
	 */
	public boolean addAuto(String klant, Auto auto) {
		try {
			String sql = "INSERT INTO auto (merk, model, bouwjaar, laatste_beurt, kenteken, klant) VALUES(?, ?, ?, null, ?, ?);";
			
			PreparedStatement statement = this.getPreparedStatement(sql);
			
			statement.setString(1, auto.getMerk());
			statement.setString(2, auto.getModel());
			statement.setInt(3, auto.getBouwjaar());
			statement.setString(4, auto.getKenteken());
			statement.setString(5, klant);
			
			statement.execute();
			this.closeConnection();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void deleteAlles() {
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
			statement.executeUpdate("TRUNCATE auto;");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
			this.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
