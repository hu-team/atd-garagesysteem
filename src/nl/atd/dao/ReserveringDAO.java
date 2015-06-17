package nl.atd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.model.Reservering;
import nl.atd.service.ServiceProvider;

public class ReserveringDAO extends BaseDAO {
	
	/**
	 * Get reserveringen, prepared statement
	 * @param ps Prepared statement
	 * @return array met reserveringen
	 */
	private ArrayList<Reservering> getReserveringen(PreparedStatement ps) {
		ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
		try{
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				Reservering reservering = new Reservering(ServiceProvider.getKlantService().getKlantByGebruikersnaam(set.getString("klant")), ServiceProvider.getAutoService().getAuto(set.getInt("auto")));
				Calendar van = Calendar.getInstance();
				Calendar tot = Calendar.getInstance();
				
				van.setTimeInMillis(set.getTimestamp("van").getTime());
				van.setTimeInMillis(set.getTimestamp("tot").getTime());
				
				reservering.setVan(van);
				reservering.setTot(tot);
				
				reserveringen.add(reservering);
			}
			
			ps.closeOnCompletion();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return reserveringen;
	}
	
	/**
	 * Get reserveringen met query (object mapping)
	 * @param query
	 * @return array
	 */
	private ArrayList<Reservering> getReserveringen(String query) {
		ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
		
		try{
			PreparedStatement st = this.getPreparedStatement(query);
			reserveringen = this.getReserveringen(st);
			
			st.closeOnCompletion();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return reserveringen;
	}
	
	/**
	 * Get alle reserveringen
	 * @return reserveringen in array
	 */
	public ArrayList<Reservering> getAlleReserveringen() {
		return this.getReserveringen("SELECT * FROM reservering");
	}
	
	/**
	 * Get reserveringen op parkeerplek
	 * @param parkeerplekid
	 * @return lijst met reserveringen op plek
	 */
	public ArrayList<Reservering> getReserveringenOpPlek(int parkeerplekid) {
		ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM reservering WHERE parkeerplek = ?");
			ps.setInt(1, parkeerplekid);
			
			reserveringen = this.getReserveringen(ps);
		}catch(Exception e) {}
		return reserveringen;
	}
}
