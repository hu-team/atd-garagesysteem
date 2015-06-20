package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import sun.launcher.resources.launcher;
import nl.atd.model.Auto;
import nl.atd.model.Klant;

public class KlantDAO extends BaseDAO {
	
	/**
	 * Get klanten
	 * @param query
	 * @param metAutos
	 * @return array met klanten
	 */
	private ArrayList<Klant> getKlanten(PreparedStatement statement, boolean metAutos) {
		ArrayList<Klant> klanten = new ArrayList<Klant>();
		
		try {
			ResultSet set = statement.executeQuery();
			
			while(set.next()) {
				Klant klant = new Klant(set.getString("naam"));
				klant.setGebruikersnaam(set.getString("gebruikersnaam"));
				klant.setWachtwoord(set.getString("wachtwoord"));
				
				Calendar laatst = Calendar.getInstance();
				
				try {
					Timestamp ts = set.getTimestamp("laatste_bezoek");
					if(ts != null) {
						laatst.setTimeInMillis(ts.getTime());
						klant.setLaatsteBezoek(laatst);
					}else{
						klant.setLaatsteBezoek(null);
					}
				}catch(NumberFormatException | SQLException | NullPointerException e) {
					klant.setLaatsteBezoek(null);
				}
				
				klant.setEmail(set.getString("email"));
				
				/**
				 * @since 16-06-2015, sprint 3
				 */
				klant.setAdres(set.getString("adres"));
				klant.setPostcode(set.getString("postcode"));
				klant.setWoonplaats(set.getString("woonplaats"));
				klant.setTelefoonnummer(set.getString("telefoonnummer"));
				
				if(metAutos){
					AutoDAO autoDAO = new AutoDAO();
					ArrayList<Auto> klantAutos = autoDAO.getAutosVanKlant(set.getString("gebruikersnaam"));
					klant.getAutos().addAll(klantAutos);
				}
				
				klanten.add(klant);
			}
			
			statement.getConnection().close();
			statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return klanten;
	}
	
	/**
	 * Old style query building (SQL INJECTIE!!)
	 * @param query
	 * @param metAutos
	 * @deprecated SQL Injectie mogelijk
	 * @return
	 */
	@Deprecated
	private ArrayList<Klant> getKlanten(String query, boolean metAutos) {
		try{
			return this.getKlanten(this.getPreparedStatement(query), metAutos);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Klant>();
	}
	
	/**
	 * Get alle klanten in database
	 * @param autos boolean, moeten autos ook uit db gehaald worden?
	 * @return array met klanten
	 */
	public ArrayList<Klant> getAlleKlanten(boolean autos) {
		try{
			return this.getKlanten(this.getPreparedStatement("SELECT * FROM klant"), autos);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Klant>();
	} 
	
	/**
	 * Get klant
	 * @param gebruikersnaam
	 * @return klant of null
	 */
	public Klant getKlant(String gebruikersnaam) {
		ArrayList<Klant> klanten = new ArrayList<Klant>();
		//ArrayList<Klant> klanten = this.getKlanten("SELECT * FROM klant WHERE gebruikersnaam LIKE '" + gebruikersnaam + "'", true);
		
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM klant WHERE gebruikersnaam LIKE ?");
			ps.setString(1, gebruikersnaam);
			
			klanten = this.getKlanten(ps, true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(klanten.size() > 0) {
			return klanten.get(0);
		}
		return null;
	}
	
	/**
	 * Get klant door op auto te zoeken
	 * @param auto
	 * @return klant of null
	 */
	public Klant getKlantMetAuto(Auto auto) {
		ArrayList<Klant> klanten = new ArrayList<Klant>();
		
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM klant WHERE gebruikersnaam IN ("
					+ "SELECT klant FROM auto WHERE kenteken LIKE ?"
					+ ")");
			
			ps.setString(1, auto.getKenteken());
			
			klanten = this.getKlanten(ps, true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(klanten.size() >= 1) {
			return klanten.get(0);
		}
		return null;
	}
	
	
	/**
	 * Add Klant
	 * @param klant
	 * @return gelukt?
	 */
	public boolean addKlant(Klant klant){
		
		try{
			PreparedStatement st = this.getPreparedStatement("INSERT INTO klant(gebruikersnaam, wachtwoord, email, naam, adres, postcode, woonplaats, telefoonnummer) values(?, ?, ?, ?, ?, ?, ?, ?)");
			
			st.setString(1, klant.getGebruikersnaam());
			st.setString(2, klant.getWachtwoord());
			st.setString(3, klant.getEmail());
			st.setString(4, klant.getNaam());
			st.setString(5, klant.getAdres());
			st.setString(6, klant.getPostcode());
			st.setString(7, klant.getWoonplaats());
			st.setString(8, klant.getTelefoonnummer());
			
			st.execute();
			
			st.getConnection().close();
			st.close();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * Edit klant, zorg dat gebruikersnaam niet aangepast wordt!!
	 * @param klant klant, originele gebruikersnaam, de rest aangepast.
	 */
	public void editKlant(Klant klant) {
		try {
			PreparedStatement statement = this.getPreparedStatement("UPDATE klant "
					+ "SET email = ?, "
					+ "naam = ?, "
					+ "adres = ?, "
					+ "postcode = ?,"
					+ "woonplaats = ?, "
					+ "telefoonnummer = ?, "
					+ "wachtwoord = ? "
					+ "WHERE gebruikersnaam = ? LIMIT 1;");
			
			statement.setString(1, klant.getEmail());
			statement.setString(2, klant.getNaam());
			statement.setString(3, klant.getAdres());
			statement.setString(4, klant.getPostcode());
			statement.setString(5, klant.getWoonplaats());
			statement.setString(6, klant.getTelefoonnummer());
			statement.setString(7, klant.getWachtwoord());
			statement.setString(8, klant.getGebruikersnaam());
			
			statement.executeUpdate();
			
			statement.getConnection().close();
			
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verwijder alle data in tabel, let op!! Alleen voor testen!
	 */
	public void deleteAlles() {
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
			statement.executeUpdate("TRUNCATE klant;");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
			
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
