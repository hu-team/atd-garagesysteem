package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
	 * 
	 * @param query
	 * @return array met klussen
	 */
	private ArrayList<Klus> getKlussen(String query) {
		ArrayList<Klus> klussen = new ArrayList<Klus>();

		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();

			ResultSet set = statement.executeQuery(query);

			while (set.next()) {

				Klant klant = null;
				Auto auto = null;
				Monteur monteur = null;

				if (set.getString("klant") != null) {
					klant = ServiceProvider.getKlantService()
							.getKlantByGebruikersnaam(set.getString("klant"));
				}

				if (set.getInt("auto") > 0) {
					auto = ServiceProvider.getAutoService().getAuto(
							set.getInt("auto"));
				}

				if (set.getString("monteur") != null) {
					monteur = ServiceProvider.getMonteurService()
							.getMonteurByGebruikersnaam(
									set.getString("monteur"));
				}

				Klus klus = new Klus(klant, auto);
				klus.setMonteur(monteur);

				// Calendar
				Calendar datum = Calendar.getInstance();
				try {
					datum.setTimeInMillis(set.getTimestamp("datum").getTime());
				} catch (SQLException se) {
					datum = null;
				}

				klus.setCalendar(datum);
				klus.setKlaar(set.getBoolean("klaar"));
				klus.setOmschrijving(set.getString("omschrijving"));
				klus.setType(set.getString("type"));
				klus.setUren(set.getInt("uren"));
				
				klus.setOnderdelen(ServiceProvider.getOnderdeelService()
						.getAlleOnderdelenVanKlus(klus));

				klussen.add(klus);
			}

			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return klussen;
	}

	/**
	 * Get alle klussen inclusief klant, auto e.d.
	 * 
	 * @return array met klussen
	 */
	public ArrayList<Klus> getAlleKlussen() {
		return this.getKlussen("SELECT * FROM klus");
	}

	/**
	 * Get klus op id
	 * @param id
	 * @return klus of null
	 */
	public Klus getKlusOpId(int id){
		ArrayList<Klus> klussen = this.getKlussen("SELECT * FROM klus WHERE idklus = " + id);
		return klussen.size() >= 1 ? klussen.get(0) : null;
	}

	/**
	 * Klus toevoegen
	 * @param klus
	 * @return gelukt?
	 */
	public boolean addKlus(Klus klus) {
		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			PreparedStatement st = connection
					.prepareCall("INSERT INTO klus (type, klaar, datum, omschrijving, monteur, klant, auto, uren) VALUES(?, 0, ?, ?, ?, ?, ?, ?);");

			st.setString(1, klus.getType());
			st.setTimestamp(2, new Timestamp(klus.getCalendar()
					.getTimeInMillis()));
			st.setString(3, klus.getOmschrijving());
			if (klus.getMonteur() == null) {
				st.setObject(4, null);
			} else {
				st.setString(4, klus.getMonteur().getGebruikersnaam());
			}
			st.setString(5, klus.getKlant().getGebruikersnaam());
			st.setInt(
					6,
					ServiceProvider.getAutoService().getAutoIdOpKenteken(
							klus.getAuto().getKenteken()));
			st.setInt(7, klus.getUren());

			st.execute();

			connection.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * KlusID opzoeken met query
	 * @param query
	 * @return id of 0
	 */
	private int getKlusIdOpQuery(String query) {
		int nr = 0;

		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();

			ResultSet set = statement.executeQuery(query);

			while (set.next()) {
				nr = set.getInt("idklus");
			}

			connection.close();

		} catch (Exception e) {
		}

		return nr;
	}
	
	/**
	 * Klus aanpassen in database
	 * @param klus
	 * @return gelukt?
	 */
	public boolean editKlus(Klus klus){
		try{
			
			Connection connection = DatabaseHelper.getDatabaseConnection();
			PreparedStatement st = connection.prepareStatement("UPDATE klus SET type=? , klaar=? , datum=? , omschrijving=? "
					+ ", monteur=? , klant=? , auto=? , uren=? WHERE idklus=?");
			
			st.setString(1, klus.getType());
			st.setInt(2, (klus.isKlaar() == true ? 1 : 0));
			st.setTimestamp(3, new Timestamp(klus.getCalendar().getTimeInMillis()));
			st.setString(4, klus.getOmschrijving());
			
			if(klus.getMonteur() == null) {
				st.setObject(5, null);
			}else{
				st.setString(5, klus.getMonteur().getGebruikersnaam());
				
			}
			st.setString(6, klus.getKlant().getGebruikersnaam());
			st.setInt(7, ServiceProvider.getAutoService().getAutoIdOpKenteken(klus.getAuto().getKenteken()));
			st.setInt(8, klus.getUren());
			st.setInt(9, this.getKlusId(klus.getCalendar(), klus.getKlant(), klus.getAuto()));
			
			st.execute();
			connection.close();
			
			return true;
			
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Get klusid, zoeken van klusid op unieke parameters.
	 * @param datum
	 * @param klant
	 * @param auto
	 * @return KlusID of 0
	 */
	public int getKlusId(Calendar datum, Klant klant, Auto auto) {
		return this.getKlusIdOpQuery("SELECT idklus FROM klus WHERE datum = FROM_UNIXTIME("
						+ (datum.getTimeInMillis() / 1000)
						+ ")"
						+ " AND klant LIKE '"
						+ klant.getGebruikersnaam()
						+ "'"
						+ " AND auto LIKE '"
						+ ServiceProvider.getAutoService().getAutoIdOpKenteken(
								auto.getKenteken()) + "';");

	}

	/**
	 * Get alle klussen tussen bepaalde tijd/datum
	 * @param start
	 * @param end
	 * @return klussen of null
	 */
	public ArrayList<Klus> getAlleKlussenTussen(Calendar start, Calendar end) {
		return this
				.getKlussen("SELECT *, UNIX_TIMESTAMP(klus.datum) as datumtimestamp FROM klus "
						+ "WHERE datum BETWEEN FROM_UNIXTIME("
						+ (start.getTimeInMillis() / 1000)
						+ ") AND FROM_UNIXTIME("
						+ (end.getTimeInMillis() / 1000) + ");");
	}

	/**
	 * Verwijder klus
	 * @param klus
	 * @return gelukt?
	 */
	public boolean delete(Klus klus) {
		boolean b = false;
		int klusID = getKlusId(klus.getCalendar(), klus.getKlant(),
				klus.getAuto());

		if (klusID != 0) {
			String query = "DELETE FROM `atd`.`klus` WHERE `klus`.`idklus` = "
					+ klusID;

			try {
				Connection connection = DatabaseHelper.getDatabaseConnection();
				Statement statement = connection.createStatement();

				if (statement.executeUpdate(query) == 1) {
					b = true;
				}
				connection.close();
			} catch (Exception e) {
			}
		}
		return b;
	}

	/**
	 * Verwijder alle klussen
	 */
	public void deleteAlles() {
		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM klus;");
			statement.executeUpdate("ALTER TABLE klus AUTO_INCREMENT = 1;");

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
