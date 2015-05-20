package nl.atd.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import nl.atd.helper.DatabaseHelper;
import nl.atd.model.Artikel;
import nl.atd.model.Onderdeel;
import nl.atd.service.ServiceProvider;

public class OnderdeelDAO {
	
	/**
	 * Get onderdelen
	 * @param query
	 * @return array
	 */
	private ArrayList<Onderdeel> getOnderdelen(String query) {
		ArrayList<Onderdeel> onderdelen = new ArrayList<Onderdeel>();
		
		try{
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement st = connection.createStatement();
			
			ResultSet set = st.executeQuery(query);
			
			while(set.next()) {
				
				// Artikel
				Artikel artikel = ServiceProvider.getArtikelService().getArtikelByCode(set.getString("code"));
				
				Onderdeel onderdeel = new Onderdeel(artikel, set.getInt("aantal"));
				
				onderdelen.add(onderdeel);
			}
			
		}catch(Exception e){
			
		}
		
		return onderdelen;
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
	
}
