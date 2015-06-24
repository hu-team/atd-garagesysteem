package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import nl.atd.model.Artikel;

public class ArtikelDAO extends BaseDAO {
	
	/**
	 * Get Artikelen
	 * @param query
	 * @return artikelen
	 */
	private ArrayList<Artikel> getArtikelen(String query){
		ArrayList<Artikel> artikelen = new ArrayList<Artikel>();
		
		try{
			Connection connection = this.getConnection();
			Statement st = connection.createStatement();
			
			ResultSet set = st.executeQuery(query);
			
			while(set.next()){
				Artikel artikel = new Artikel(set.getString("naam"), set.getInt("aantal"));
				artikel.setCode(set.getString("code"));
				artikel.setPrijs(set.getDouble("prijs"));
				
				artikelen.add(artikel);
			}
			
			st.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return artikelen;
	}
	
	/**
	 * Get alle artikelen
	 * @return artikelen
	 */
	public ArrayList<Artikel> getAlleArtikelen(){
		return this.getArtikelen("SELECT * FROM artikel");
	}
	
	/**
	 * Get Artikel met code
	 * @param code
	 * @return artikel of null
	 */
	public Artikel getArtikelByCode(String code){
		ArrayList<Artikel> artikelen = this.getArtikelen("SELECT * FROM artikel WHERE code LIKE '" + code + "'");
		return artikelen.size() >=1 ? artikelen.get(0) : null;
	}
	
	/**
	 * Zoek artikel(en) op naam
	 * @param naam
	 * @return artikelen of null
	 */
	public ArrayList<Artikel> getArtikelByName(String naam){
		ArrayList<Artikel> artikelen = this.getArtikelen("SELECT * FROM artikel WHERE naam LIKE '" + naam + "'");
		return artikelen;
	}
	
	/**
	 * Wijzig artikel
	 * @param artikel artikel, laat code ongewijzigd!
	 * @return gelukt?
	 */
	public boolean editArtikel(Artikel artikel){
		try{
			PreparedStatement st = this.getPreparedStatement("UPDATE artikel SET naam=? , aantal=? , prijs=? WHERE code=?");
			
			st.setString(1, artikel.getNaam());
			st.setInt(2, artikel.getAantal());
			st.setDouble(3, artikel.getPrijs());
			st.setString(4, artikel.getCode());
			
			st.execute();
			
			st.getConnection().close();
			st.close();
			
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
	/**
	 * Voeg artikel toe
	 * @param artikel
	 * @return gelukt?
	 */
	public boolean addArtikel(Artikel artikel){
		try{
			PreparedStatement st = this.getPreparedStatement("INSERT INTO artikel (code, naam, aantal, prijs) VALUES(?, ?, ?, ?);");
			
			st.setString(1, artikel.getCode());
			st.setString(2, artikel.getNaam());
			st.setInt(3, artikel.getAantal());
			st.setDouble(4, artikel.getPrijs());
			
			st.execute();
			
			st.getConnection().close();
			st.close();
			
			return true;
		}catch(Exception e){
			return false;
		}
	}

	/**
	 * Delete alle artikelen
	 * ALLEEN VOOR TESTS
	 */
	public void deleteAlles() {
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
			statement.executeUpdate("TRUNCATE artikel;");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");

			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
