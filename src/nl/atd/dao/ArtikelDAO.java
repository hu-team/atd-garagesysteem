package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import nl.atd.helper.DatabaseHelper;
import nl.atd.model.Artikel;

public class ArtikelDAO {
	
	
	private ArrayList<Artikel> getArtikelen(String query){
		ArrayList<Artikel> artikelen = new ArrayList<Artikel>();
		
		try{
			Connection connection = DatabaseHelper.getDatabaseConnection();
			Statement st = connection.createStatement();
			
			ResultSet set = st.executeQuery(query);
			
			while(set.next()){
				Artikel artikel = new Artikel(set.getString("naam"), set.getInt("aantal"));
				artikel.setCode(set.getString("code"));
				artikel.setPrijs(set.getDouble("prijs"));
				
				artikelen.add(artikel);
			}
			
		}catch(Exception e){
			
		}

		return artikelen;
	}
	
	public ArrayList<Artikel> getAlleArtikelen(){
		return this.getArtikelen("SELECT * FROM artikel");
	}
	
	public Artikel getArtikelByCode(String code){
		ArrayList<Artikel> artikelen = this.getArtikelen("SELECT * FROM artikel WHERE code LIKE '" + code + "'");
		return artikelen.size() >=1 ? artikelen.get(0) : null;
	}
	
	public ArrayList<Artikel> getArtikelByName(String naam){
		ArrayList<Artikel> artikelen = this.getArtikelen("SELECT * FROM artikel WHERE naam LIKE '" + naam + "'");
		return artikelen;
	}
	
	public boolean editArtikel(Artikel artikel){
		try{
			Connection connection = DatabaseHelper.getDatabaseConnection();
			PreparedStatement st = connection.prepareStatement("UPDATE artikel SET naam=? , aantal=? , prijs=? WHERE code=?");
			
			st.setString(1, artikel.getNaam());
			st.setInt(2, artikel.getAantal());
			st.setDouble(3, artikel.getPrijs());
			st.setString(4, artikel.getCode());
			
			return st.execute();
			
		}catch(Exception e){
			return false;
		}
		
	}
	
	public boolean addArtikel(Artikel artikel){
		try{
			Connection connection = DatabaseHelper.getDatabaseConnection();
			PreparedStatement st = connection.prepareStatement("INSERT INTO artikel (code, naam, aantal, prijs) VALUES(?, ?, ?, ?);");
			
			st.setString(1, artikel.getCode());
			st.setString(2, artikel.getNaam());
			st.setInt(3, artikel.getAantal());
			st.setDouble(4, artikel.getPrijs());
			
			return st.execute();
		}catch(Exception e){
			return false;
		}
	}
}
