package nl.atd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.helper.DatabaseHelper;
import nl.atd.model.Auto;
import nl.atd.model.Klant;

public class AutoDAO {
	
	public AutoDAO() {
		
	}
	
	public ArrayList<Auto> getAutos(String klantGebruikersnaam) {
		ArrayList<Auto> autos = new ArrayList<Auto>();
		
		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();
			
			String sql = "SELECT *, UNIX_TIMESTAMP(auto.laatste_beurt) as laatstebeurt FROM auto";
			if(klantGebruikersnaam != null) {
				sql += " WHERE klant = " + klantGebruikersnaam;
			}
			
			ResultSet set = statement.executeQuery(sql);
			
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
		} catch (Exception e) {
		}
		
		return autos;
	}
}
