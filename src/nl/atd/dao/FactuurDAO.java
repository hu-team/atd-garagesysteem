package nl.atd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.model.Factuur;
import nl.atd.service.ServiceProvider;

public class FactuurDAO extends BaseDAO {
	
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
	 * Add factuur
	 * @param factuur
	 * @return gelukt
	 */
	public boolean addFactuur(Factuur factuur) {
		try{
			PreparedStatement ps = this.getPreparedStatement("INSERT INTO factuur (betaald, datum, klant) VALUES(?, ?, ?)");
			
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
