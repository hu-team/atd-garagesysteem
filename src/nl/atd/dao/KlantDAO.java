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

public class KlantDAO {
	
	/**
	 * Get alle klanten in database
	 * @param autos boolean, moeten autos ook uit db gehaald worden?
	 * @return array met klanten
	 */
	public ArrayList<Klant> getKlanten(boolean autos) {
		ArrayList<Klant> klanten = new ArrayList<Klant>();
		
		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery("SELECT *, UNIX_TIMESTAMP(klant.laatste_bezoek) as laatstebezoek FROM klant");
			
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
				
				if(autos){
					AutoDAO autoDAO = new AutoDAO();
					ArrayList<Auto> klantAutos = autoDAO.getAutos(set.getString("gebruikersnaam"));
					klant.getAutos().addAll(klantAutos);
				}
				
				klanten.add(klant);
			}
		} catch (Exception e) {
			
		}
		
		return klanten;
	}

	public Klant getKlant(String gebruikersnaam) {
		Klant klant = null;
		
		try{
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();
			
			ResultSet set = statement.executeQuery("SELECT *, UNIX_TIMESTAMP(klant.laatste_bezoek) as laatstebezoek FROM klant WHERE gebruikersnaam = " + gebruikersnaam);
			
			if(set.next()) {
				klant = new Klant(set.getString("naam"));
				klant.setEmail(set.getString("email"));
				klant.setGebruikersnaam(set.getString("gebruikersnaam"));
				klant.setWachtwoord(set.getString("wachtwoord"));
				
				Calendar laatst = Calendar.getInstance();
				try {
					laatst.setTimeInMillis(set.getInt("laatstebezoek") * 1000);
					klant.setLaatsteBezoek(laatst);
				}catch(NumberFormatException | SQLException e) {
					klant.setLaatsteBezoek(null);
				}
			}
		}catch(Exception e) {
		}
		
		return klant;
	}
}
