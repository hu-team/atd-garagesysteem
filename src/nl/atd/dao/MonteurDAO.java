package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import nl.atd.model.Monteur;

public class MonteurDAO extends BaseDAO {
	
	/**
	 * Get monteuren
	 * @param query
	 * @return array
	 */
	private ArrayList<Monteur> getMonteuren(String query) {
		ArrayList<Monteur> monteuren = new ArrayList<Monteur>();
		
		try{
			Connection connection = this.getConnection();
			Statement st = connection.createStatement();
			
			ResultSet set = st.executeQuery(query);
			
			while(set.next()) {
				Monteur monteur = new Monteur(set.getString("naam"), set.getInt("salarisnummer"));
				monteur.setGebruikersnaam(set.getString("gebruikersnaam"));
				monteur.setWachtwoord(set.getString("wachtwoord"));
				
				monteuren.add(monteur);
			}
			
			st.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
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
		ArrayList<Monteur> monteuren = this.getMonteuren("SELECT * FROM monteur WHERE gebruikersnaam LIKE '" + gebruikersnaam + "'");
		if(monteuren.size() >= 1) {
			return monteuren.get(0);
		}
		return null;
	}
	
	/**
	 * Add Monteur
	 * @param monteur
	 * @return gelukt?
	 */
	public boolean addMonteur(Monteur monteur) {
		try{
			PreparedStatement st = this.getPreparedStatement("INSERT INTO monteur (gebruikersnaam, wachtwoord, naam, salarisnummer) VALUES(?, ?, ?, ?);");

			st.setString(1, monteur.getGebruikersnaam());
			st.setString(2, monteur.getWachtwoord());
			st.setString(3, monteur.getNaam());
			st.setInt(4, monteur.getSalarisnummer());
			
			st.execute();
			
			st.getConnection().close();
			st.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Delete alle monteur data
	 * ALLEEN VOOR TESTS
	 */
	public void deleteAlles() {
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
			statement.executeUpdate("TRUNCATE monteur;");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");

			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
