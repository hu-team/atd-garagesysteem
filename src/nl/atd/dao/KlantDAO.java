package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.helper.DatabaseHelper;
import nl.atd.model.Auto;
import nl.atd.model.Klant;

public class KlantDAO {
	
	/**
	 * Get klanten
	 * @param query
	 * @param metAutos
	 * @return array met klanten
	 */
	private ArrayList<Klant> getKlanten(String query, boolean metAutos) {
		ArrayList<Klant> klanten = new ArrayList<Klant>();
		
		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			
			while(set.next()) {
				Klant klant = new Klant(set.getString("naam"));
				klant.setGebruikersnaam(set.getString("gebruikersnaam"));
				klant.setWachtwoord(set.getString("wachtwoord"));
				
				Calendar laatst = Calendar.getInstance();
				
				try {
					laatst.setTimeInMillis(set.getInt("laatstebezoek") * 1000);
					klant.setLaatsteBezoek(laatst);
				}catch(NumberFormatException | SQLException e) {
					klant.setLaatsteBezoek(null);
				}
				
				klant.setEmail(set.getString("email"));
				
				if(metAutos){
					AutoDAO autoDAO = new AutoDAO();
					ArrayList<Auto> klantAutos = autoDAO.getAutosVanKlant(set.getString("gebruikersnaam"));
					klant.getAutos().addAll(klantAutos);
				}
				
				klanten.add(klant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return klanten;
	}
	
	/**
	 * Get alle klanten in database
	 * @param autos boolean, moeten autos ook uit db gehaald worden?
	 * @return array met klanten
	 */
	public ArrayList<Klant> getAlleKlanten(boolean autos) {
		return this.getKlanten("SELECT *, UNIX_TIMESTAMP(klant.laatste_bezoek) as laatstebezoek FROM klant", autos);
	}

	/**
	 * Get klant
	 * @param gebruikersnaam
	 * @return klant of null
	 */
	public Klant getKlant(String gebruikersnaam) {
		ArrayList<Klant> klanten = this.getKlanten("SELECT *, UNIX_TIMESTAMP(klant.laatste_bezoek) as laatstebezoek FROM klant WHERE gebruikersnaam LIKE '" + gebruikersnaam + "'", true);
		if(klanten.size() >= 1) {
			return klanten.get(0);
		}
		return null;
	}
	
	public boolean addKlant(Klant klant){
		
		try{
			Connection connection = DatabaseHelper.getDatabaseConnection();
			PreparedStatement st = connection.prepareCall("INSERT INTO klant(gebruikersnaam, wachtwoord, email, naam) values(?, ?, ?, ?)");
			
			st.setString(1, klant.getGebruikersnaam());
			st.setString(2, klant.getWachtwoord());
			st.setString(3, klant.getEmail());
			st.setString(4, klant.getNaam());
			
			st.execute();
			
			connection.close();
			
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
}
