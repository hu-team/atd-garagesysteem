package nl.atd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import nl.atd.model.Factuur;
import nl.atd.model.Factuuronderdeel;

public class FactuuronderdeelDAO extends BaseDAO {
	
	private ArrayList<Factuuronderdeel> getFactuuronderdelen(PreparedStatement ps) {
		ArrayList<Factuuronderdeel> onderdelen = new ArrayList<Factuuronderdeel>();
		
		try{
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				Factuuronderdeel onderdeel = new Factuuronderdeel();
				onderdeel.setOmschrijving(set.getString("omschrijving"));
				onderdeel.setTotaalprijs(set.getDouble("totaalprijs"));
				
				// TODO, mapping van welk onderdeel
				
				
				onderdelen.add(onderdeel);
			}
			
			ps.closeOnCompletion();
		}catch(Exception e) {
			
		}
		
		return onderdelen;
	}
	
	/**
	 * Get factuuronderdelen voor een factuur
	 * @param factuur
	 * @return onderdelen
	 */
	public ArrayList<Factuuronderdeel> getFactuuronderdelenVanFactuur(Factuur factuur) {
		ArrayList<Factuuronderdeel> onderdelen = new ArrayList<Factuuronderdeel>();
		
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM factuuronderdeel WHERE factuurid = ?");
			ps.setInt(1, factuur.getFactuurnummer());
			
			onderdelen = this.getFactuuronderdelen(ps);
		}catch(Exception e){}
		
		return onderdelen;
	}
	
	
}
