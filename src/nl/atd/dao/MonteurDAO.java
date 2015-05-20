package nl.atd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import nl.atd.helper.DatabaseHelper;
import nl.atd.model.Monteur;

public class MonteurDAO {
	
	/**
	 * Get monteuren
	 * @param query
	 * @return array
	 */
	private ArrayList<Monteur> getMonteuren(String query) {
		ArrayList<Monteur> monteuren = new ArrayList<Monteur>();
		
		try{
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement st = connection.createStatement();
			
			ResultSet set = st.executeQuery(query);
			
			while(set.next()) {
				Monteur monteur = new Monteur(set.getString("naam"), set.getInt("salarisnummer"));
				monteur.setGebruikersnaam(set.getString("gebruikersnaam"));
				monteur.setWachtwoord(set.getString("wachtwoord"));
				
				monteuren.add(monteur);
			}
			
		}catch(Exception e){
			
		}
		
		return monteuren;
	}
	
	/**
	 * Get monteuren in database
	 * @return array met monteuren
	 */
	public ArrayList<Monteur> getAlleMonteuren() {
		return this.getMonteuren("SELECT * FROM monteur");
	}
	
	/**
	 * Get monteur door te zoeken op gebruikersnaam
	 * @param gebruikersnaam
	 * @return monteur object of null
	 */
	public Monteur getMonteur(String gebruikersnaam) {
		ArrayList<Monteur> monteuren = this.getMonteuren("SELECT * FROM monteur WHERE gebruikersnaam = " + gebruikersnaam);
		if(monteuren.size() >= 1) {
			return monteuren.get(0);
		}
		return null;
	}
}
