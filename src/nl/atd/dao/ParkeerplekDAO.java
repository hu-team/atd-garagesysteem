package nl.atd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.model.Parkeerplek;

public class ParkeerplekDAO extends BaseDAO {
	
	/**
	 * Get plekken, prepared statement
	 * @param ps Prepared statement
	 * @return array met plekken
	 */
	private ArrayList<Parkeerplek> getPlekken(PreparedStatement ps) {
		ArrayList<Parkeerplek> plekken = new ArrayList<Parkeerplek>();
		try{
			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				Parkeerplek parkeerplek = new Parkeerplek(set.getString("rij").charAt(0), set.getInt("plek"));
				plekken.add(parkeerplek);
			}
			
			ps.getConnection().close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return plekken;
	}
	
	/**
	 * Get plekken met query (object mapping)
	 * @param query
	 * @return array
	 */
	private ArrayList<Parkeerplek> getPlekken(String query) {
		ArrayList<Parkeerplek> plekken = new ArrayList<Parkeerplek>();
		
		try{
			PreparedStatement st = this.getPreparedStatement(query);
			plekken = this.getPlekken(st);
			
			st.getConnection().close();
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return plekken;
	}
	
	/**
	 * Get parkeerplek op id
	 * @param id
	 * @return parkeerplek of null
	 */
	public Parkeerplek getParkeerplekOpId(int id) {
		ArrayList<Parkeerplek> plekken = new ArrayList<Parkeerplek>();
		
		try{
			PreparedStatement st = this.getPreparedStatement("SELECT * FROM parkeerplek WHERE parkeerplekid = ?");
			st.setInt(1, id);
			
			plekken = this.getPlekken(st);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(plekken.size() > 0) return plekken.get(0);
		return null;
	}
	
	
	/**
	 * Get alle parkeerplekken
	 * @return plekken in array
	 */
	public ArrayList<Parkeerplek> getAllePlekken() {
		return this.getPlekken("SELECT * FROM parkeerplek");
	}
	
	/**
	 * Get alle vrije plekken op parkeerplaats
	 * @param datum Datum om te zoeken
	 * @return list met vrije plekken
	 */
	public ArrayList<Parkeerplek> getAlleVrijePlekken(Calendar datum) {
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM parkeerplek WHERE "
					+ "parkeerplek.rij NOT IN("
					+ "SELECT rij FROM reservering WHERE van >= ? AND tot <= ?)"
					+ "AND"
					+ "parkeerplek.plek NOT IN("
					+ "SELECT plek FROM reservering WHERE van >= ? AND tot <= ?);");
			return this.getPlekken(ps);
		}catch(Exception e) {
			return new ArrayList<Parkeerplek>();
		}
	}
	
	public ArrayList<Parkeerplek> getParkeerplekkenOpDatum(Calendar datum){
		ArrayList<Parkeerplek> plekken = new ArrayList<Parkeerplek>();
		
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM parkeerplek WHERE "
					+ "parkeeplek.rij IN(SELECT rij FROM reservering WHERE MONTH(van) = ? AND YEAR(van) = ?) "
					+ "AND "
					+ "parkeerplek.plek IN(SELECT plek FROM reservering WHERE MONTH(van) = ? AND YEAR(van) = ?);");
			
			ps.setInt(1, datum.get(Calendar.MONTH + 1	));
			ps.setInt(2, datum.get(Calendar.YEAR));
			ps.setInt(3, datum.get(Calendar.MONTH + 1));
			ps.setInt(4, datum.get(Calendar.YEAR));
			
			plekken = this.getPlekken(ps);
			
			ps.getConnection().close();
			ps.close();
			
		}catch(Exception e){
			
		}
		
		return plekken;
	}
	
	
	public ArrayList<Parkeerplek> getParkeerplekkenTussenVanTot(Calendar van, Calendar tot){
		ArrayList<Parkeerplek> plekken = new ArrayList<Parkeerplek>();
		
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM parkeerplek WHERE "
					+ "parkeerplekid NOT IN("
					+ "SELECT parkeerplek FROM reservering WHERE "
					+ "(van BETWEEN ? AND ?) "
					+ "OR "
					+ "(tot BETWEEN ? AND ?));");
			
			ps.setTimestamp(1, new Timestamp(van.getTimeInMillis()));
			ps.setTimestamp(2, new Timestamp(tot.getTimeInMillis()));
			ps.setTimestamp(3, new Timestamp(van.getTimeInMillis()));
			ps.setTimestamp(4, new Timestamp(tot.getTimeInMillis()));
			plekken = this.getPlekken(ps);
		}catch(Exception e){
			e.printStackTrace();
		}
		return plekken;
	}
	
	/**
	 * Get plekken met rij en plek
	 * @param rij
	 * @param plek
	 * @return plekken
	 */
	
	public ArrayList<Parkeerplek> getParkeerplekkenOpPlekEnRij(char rij, int plek) {
		ArrayList<Parkeerplek> plekken = new ArrayList<Parkeerplek>();
		
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM parkeerplek WHERE rij LIKE ? AND plek = ?");
			ps.setString(1, Character.toString(rij));
			ps.setInt(2, plek);
			
			plekken = this.getPlekken(ps);
			
			ps.getConnection().close();
			ps.close();
		}catch(Exception e) {}
		
		return plekken;
	}
	
	/**
	 * Get plek id van plek zelf
	 * @param plek
	 * @return int of 0
	 */
	public int getParkeerplekIdOpPlek(Parkeerplek plek) {
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM parkeerplek WHERE rij LIKE ? AND plek = ?");
			ps.setString(1, Character.toString(plek.getRij()));
			ps.setInt(2, plek.getPlek());
			
			ResultSet set = ps.executeQuery();
			
			int nummer = 0;
			while(set.next()) {
				nummer = set.getInt("parkeerplekid");
			}
			
			ps.getConnection().close();
			ps.close();
			
			return nummer;
		}catch(Exception e) {}
		
		return 0;
	}
	
	
	/**
	 * Add parkeerplek
	 * @param plek
	 * @return gelukt?
	 */
	public boolean addParkeerplek(Parkeerplek plek) {
		try{
			PreparedStatement ps = this.getPreparedStatement("INSERT INTO parkeerplek (rij, plek) VALUES(?, ?);");
			ps.setString(1, Character.toString(plek.getRij()));
			ps.setInt(2, plek.getPlek());
			
			boolean gelukt = ps.execute();
			
			ps.getConnection().close();
			ps.close();
			
			return gelukt;
		}catch(Exception e){
			return false;
		}
	}
	
	
}
