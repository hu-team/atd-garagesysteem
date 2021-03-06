package nl.atd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.model.Factuur;
import nl.atd.service.ServiceProvider;

public class FactuurDAO extends BaseDAO {
	
	/**
	 * Get facturen met prepared statement
	 * @param ps
	 * @return list facturen
	 */
	private ArrayList<Factuur> getFacturen(PreparedStatement ps) {
		ArrayList<Factuur> facturen = new ArrayList<Factuur>();
		
		try{
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				Factuur factuur = new Factuur();
				factuur.setBetaald(set.getBoolean("betaald"));
				factuur.setFactuurnummer(set.getInt("factuurid"));
				
				Calendar datum = Calendar.getInstance();
				datum.setTimeInMillis(set.getTimestamp("datum").getTime());
				factuur.setDatum(datum);
				
				factuur.setKlant(ServiceProvider.getKlantService().getKlantByGebruikersnaam(set.getString("klant")));
				
				facturen.add(factuur);
			}
			
			ps.getConnection().close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return facturen;
	}
	
	/**
	 * Get alle facturen
	 * @return alle facturen
	 */
	public ArrayList<Factuur> getAlleFacturen() {
		ArrayList<Factuur> facturen = new ArrayList<Factuur>();
		
		try{
			facturen = this.getFacturen(this.getPreparedStatement("SELECT * FROM factuur ORDER BY datum"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return facturen;
	}
	
	/**
	 * Get facturen van gebruiker
	 * @param gebruikersnaam
	 * @return facturen
	 */
	public ArrayList<Factuur> getFacturenVanKlant(String gebruikersnaam) {
		ArrayList<Factuur> facturen = new ArrayList<Factuur>();
		
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM factuur WHERE klant LIKE ? ORDER BY betaald, datum LIMIT 20");
			ps.setString(1, gebruikersnaam);
			facturen = this.getFacturen(ps);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return facturen;
	}
	
	/**
	 * Get factuur op nummer
	 * @param nummer
	 * @return factuur of null
	 */
	public Factuur getFactuurOpNummer(int nummer) {
		ArrayList<Factuur> facturen = new ArrayList<Factuur>();
		
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM factuur WHERE factuurid = ? ORDER BY datum");
			ps.setInt(1, nummer);
			
			facturen = this.getFacturen(ps);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(facturen.size() > 0) return facturen.get(0);
		return null;
	}

	/**
	 * Set factuur betaal status (update)
	 * @param factuur
	 * @return gelukt
	 */
	public boolean setFactuurBetaald(Factuur factuur) {
		try{
			PreparedStatement ps = this.getPreparedStatement("UPDATE factuur "
					+ "SET betaald = ? "
					+ "WHERE factuurid = ? "
					+ "LIMIT 1");
			
			ps.setBoolean(1, factuur.isBetaald());
			ps.setInt(2, factuur.getFactuurnummer());
			
			ps.executeUpdate();
			
			ps.getConnection().close();
			ps.close();
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Add factuur
	 * @param factuur
	 * @return gelukt
	 */
	public boolean addFactuur(Factuur factuur) {
		try{
			
			//PreparedStatement ps = this.getPreparedStatement("INSERT INTO factuur (betaald, datum, klant) VALUES(?, ?, ?)");
			PreparedStatement ps = this.getConnection().prepareStatement("INSERT INTO factuur (betaald, datum, klant) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			ps.setBoolean(1, factuur.isBetaald());
			ps.setTimestamp(2, new Timestamp(factuur.getDatum().getTimeInMillis()));
			ps.setString(3, factuur.getKlant().getGebruikersnaam());
			
			ps.executeUpdate();
		
			
			ResultSet rs = ps.getGeneratedKeys();
			
			boolean gelukt = false;
			if(rs.next()) {
				factuur.setFactuurnummer(rs.getInt(1));
				gelukt = true;
			}
			
			rs.close();
			ps.getConnection().close();
			ps.close();
			
			return gelukt;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
