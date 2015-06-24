package nl.atd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import nl.atd.model.Factuur;
import nl.atd.model.Factuuronderdeel;
import nl.atd.service.ServiceProvider;

public class FactuuronderdeelDAO extends BaseDAO {
	
	/**
	 * Get factuur onderdelen
	 * @param ps
	 * @return onderdelen van factuur
	 */
	private ArrayList<Factuuronderdeel> getFactuuronderdelen(PreparedStatement ps) {
		ArrayList<Factuuronderdeel> onderdelen = new ArrayList<Factuuronderdeel>();
		
		try{
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				Factuuronderdeel onderdeel = new Factuuronderdeel();
				onderdeel.setOmschrijving(set.getString("omschrijving"));
				onderdeel.setTotaalprijs(set.getDouble("totaalprijs"));
				
				// Mapping van klus OF reservering
				int klusid = set.getInt("klusid");
				if(set.wasNull()) {
					onderdeel.setKlus(null);
				}else{
					onderdeel.setKlus(ServiceProvider.getKlusService().getKlusOpId(klusid));
				}
				
				int reserveringid = set.getInt("reserveringid");
				if(set.wasNull()) {
					onderdeel.setReservering(null);
				}else{
					onderdeel.setReservering(ServiceProvider.getReserveringService().getReserveringOpId(reserveringid));
				}
				
				onderdelen.add(onderdeel);
			}
			
			ps.getConnection().close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return onderdelen;
	}
	
	/**
	 * add factuuronderdelen voor een factuur
	 * @param factuuronderdeel
	 * @param factuurid
	 * @return boolean
	 */
	public boolean addFactuurOnderdeel(Factuuronderdeel fo, int factuurid) {
		Integer klusid = null;
		Integer reserveringid = null;
		
		if(fo.getKlus() != null) {
			klusid = ServiceProvider.getKlusService().getKlusIdOpKlus(fo.getKlus());
		}else if(fo.getReservering() != null) {
			reserveringid = ServiceProvider.getReserveringService().getReserveringId(fo.getReservering().getVan(), fo.getReservering().getParkeerplek());
		}else{
			return false;
		}
		
		try {
			PreparedStatement st = this.getPreparedStatement("INSERT INTO factuuronderdeel(factuurid, reserveringid, klusid, totaalprijs, omschrijving) values(?, ?, ?, ?, ?)");
			st.setInt(1, factuurid);
			if(reserveringid == null) {
				st.setObject(2, null);
			}else{
				st.setInt(2, reserveringid);
			}
			
			if(klusid == null) {
				st.setObject(3, null);
			}else{
				st.setInt(3, klusid);
			}
			
			st.setDouble(4, fo.getTotaalprijs());
			st.setString(5, fo.getOmschrijving());
			
			st.execute();
			st.getConnection().close();
			st.close();
				
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
		}catch(Exception e){e.printStackTrace();}
		
		return onderdelen;
	}
	
	
}
