package nl.atd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.helper.DatabaseHelper;
import nl.atd.model.Auto;

public class AutoDAO {
	
	/**
	 * Get autos, convert to list met autos
	 * @param query
	 * @return array met autos
	 */
	private ArrayList<Auto> getAutos(String query) {
		ArrayList<Auto> autos = new ArrayList<Auto>();
		
		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery(query);
			
			while(set.next()) {
				Calendar laatste = Calendar.getInstance();
				try{
					laatste.setTimeInMillis(set.getInt("laatstebeurt") * 1000);
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
			
			connection.close();
			
		} catch (Exception e) {
		}
		
		return autos;
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
		return this.getAutos("SELECT *, UNIX_TIMESTAMP(auto.laatste_beurt) as laatstebeurt FROM auto WHERE klant LIKE " + klantGebruikersnaam);
	}
}
