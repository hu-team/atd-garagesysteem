package nl.atd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import nl.atd.model.Reservering;

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
}
