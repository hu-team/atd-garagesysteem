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
import nl.atd.model.Klus;
import nl.atd.model.Monteur;
import nl.atd.service.ServiceProvider;

public class KlusDAO {
	
	/**
	 * Get autos, convert to list met klussen
	 * @param query
	 * @return array met klussen
	 */
	private ArrayList<Klus> getKlussen(String query) {
		ArrayList<Klus> klussen = new ArrayList<Klus>();
		
		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery(query);
			
			while(set.next()) {
				
				// TODO: KOPPEL AAN SERVICE!!
				Klant klant = null; 
				
				Auto auto = null;
				Monteur monteur = null;
				
				if(set.getString("klant") != null) {
					// TODO: service
					klant = null;
				}
				
				if(set.getInt("auto") > 0) {
					auto = ServiceProvider.getAutoService().getAuto(set.getInt("auto"));
				}
				
				if(set.getString("monteur") != null) {
					monteur = ServiceProvider.getMonteurService().getMonteurByGebruikersnaam(set.getString("monteur"));
				}
				
				
				Klus klus = new Klus(klant, auto);
				klus.setMonteur(monteur);
				
				// Calendar
				Calendar datum = Calendar.getInstance();
				try{
					datum.setTimeInMillis(set.getInt("datumtimestamp") * 1000);
				}catch(SQLException se) {
					datum = null;
				}
				
				// TODO: Onderdelen toevoegen
				
				
				klus.setCalendar(datum);
				klus.setKlaar(set.getBoolean("klaar"));
				klus.setOmschrijving(set.getString("omschrijving"));
				klus.setType(set.getString("type"));
				klus.setUren(set.getInt("uren"));
				
				klussen.add(klus);
			}
			
			connection.close();
			
		} catch (Exception e) {
		}
		
		return klussen;
	}
	
	/**
	 * Get alle klussen inclusief klant, auto e.d.
	 * @return array met klussen
	 */
	public ArrayList<Klus> getAlleKlussen() {
		return this.getKlussen("SELECT *, UNIX_TIMESTAMP(klus.datum) as datumtimestamp FROM klus");
	}
	
}
