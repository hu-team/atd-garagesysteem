package nl.atd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	
	public ArrayList<Factuur> getAlleFacturen() {
		ArrayList<Factuur> facturen = new ArrayList<Factuur>();
		
		try{
			facturen = this.getFacturen(this.getPreparedStatement("SELECT * FROM factuur ORDER BY datum"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return facturen;
	}
	
}
