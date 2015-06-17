package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import nl.atd.helper.DatabaseHelper;
import nl.atd.model.Artikel;
import nl.atd.model.Klus;
import nl.atd.model.Onderdeel;
import nl.atd.service.ServiceProvider;

public class OnderdeelDAO extends BaseDAO {
	
	/**
	 * Get onderdelen
	 * @param query
	 * @return array
	 */
	private ArrayList<Onderdeel> getOnderdelen(String query) {
		ArrayList<Onderdeel> onderdelen = new ArrayList<Onderdeel>();
		
		try{
			Connection connection = this.getConnection();
			Statement st = connection.createStatement();
			
			ResultSet set = st.executeQuery(query);
			
			while(set.next()) {
				
				// Artikel
				Artikel artikel = ServiceProvider.getArtikelService().getArtikelByCode(set.getString("code"));
				
				Onderdeel onderdeel = new Onderdeel(artikel, set.getInt("aantal"));
				
				onderdelen.add(onderdeel);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return onderdelen;
	}
	
	/**
	 * Onderdeel toevoegen
	 * @param onderdeel
	 * @param klus
	 * @return gelukt?
	 */
	public boolean addOnderdeel(Onderdeel onderdeel, Klus klus){
		try{
			Connection connection = DatabaseHelper.getDatabaseConnection();
			PreparedStatement st = connection.prepareStatement("INSERT INTO onderdeel (idklus, code, aantal) VALUES(?, ?, ?);");
			
			st.setInt(1, ServiceProvider.getKlusService().getKlusIdOpKlus(klus));
			st.setString(2, onderdeel.getArtikel().getCode());
			st.setInt(3, onderdeel.getAantal());
			
			st.execute();
			connection.close();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Get alle onderdelen
	 * @return array met onderdelen
	 */
	public ArrayList<Onderdeel> getAlleOnderdelen() {
		return this.getOnderdelen("SELECT * FROM onderdeel");
	}
	
	/**
	 * Get alle onderdelen van bepaalde klus
	 * @param klusid
	 * @return array met onderdelen van klus
	 */
	public ArrayList<Onderdeel> getAlleOnderdelenVanKlus(int klusid) {
		return this.getOnderdelen("SELECT * FROM onderdeel WHERE idklus = " + klusid);
	}

	public void deleteAlles() {
		try {
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
			statement.executeUpdate("TRUNCATE onderdeel;");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
