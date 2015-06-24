	package nl.atd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.model.Klant;
import nl.atd.model.Parkeerplek;
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
				
				reservering.setParkeerplek(ServiceProvider.getParkeerplekService().getParkeerplekOpId(set.getInt("parkeerplek")));
				
				reserveringen.add(reservering);
			}
			
			ps.getConnection().close();
			ps.close();
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
			
			st.getConnection().close();
			st.close();
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
	
	/**
	 * Get reserveringen van klant
	 * @param klant
	 * @return lijst met reserveringen van klant
	 */
	public ArrayList<Reservering> getReserveringenVanKlant(Klant klant) {
		ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM reservering WHERE klant LIKE ?");
			ps.setString(1, klant.getGebruikersnaam());
			
			reserveringen = this.getReserveringen(ps);
		}catch(Exception e) {e.printStackTrace();}
		return reserveringen;
	}
	
	/**
	 * Get reserveringen op maand en jaar
	 * @param datum
	 * @return lijst met reserveringen
	 */
	public ArrayList<Reservering> getReserveringenOpMaand(Calendar datum){
		ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM reservering WHERE MONTH(van)=? AND YEAR(van)=?;");
			
			ps.setInt(1, datum.get(Calendar.MONTH) + 1);
			ps.setInt(2, datum.get(Calendar.YEAR));
			
			reserveringen = this.getReserveringen(ps);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return reserveringen;
		
	}
	
	/**
	 * Get reserveringen
	 * @param id
	 * @return reservering of null
	 */
	public Reservering getReserveringOpId(int id) {
		ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
		try{
			PreparedStatement ps = this.getPreparedStatement("SELECT * FROM reservering WHERE reserveringid = ?");
			ps.setInt(1, id);
			
			reserveringen = this.getReserveringen(ps);
			
			ps.getConnection().close();
			ps.close();
		}catch(Exception e){}
		
		return reserveringen.size() > 0 ? reserveringen.get(0) : null;
	}
	
	/**
	 * Get reserveringid, zoeken van reserveringid op unieke parameters.
	 * @param van
	 * @param parkeerplek
	 * @return ReserveringID of 0
	 */
	public int getReserveringId(Calendar van, Parkeerplek plek){
		return this.getReserveringIdOpQuery("SELECT reserveringid FROM reservering WHERE "
				+ "van=FROM_UNIXTIME(" + (van.getTimeInMillis() / 1000) + ")"
				+ "AND "
				+ "parkeerplek=" + ServiceProvider.getParkeerplekService().getParkeerplekIdOpPlek(plek) + ";");
	}
	
	/**
	 * ReserveringID opzoeken met query
	 * @param query
	 * @return id of 0
	 */
	
	private int getReserveringIdOpQuery(String query) {
		int nr = 0;

		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();

			ResultSet set = statement.executeQuery(query);

			while (set.next()) {
				nr = set.getInt("reserveringid");
			}

			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nr;
	}
	
	public boolean addReservering(Reservering reservering){
		try{
			PreparedStatement ps = this.getPreparedStatement("INSERT INTO reservering (van, tot, auto, klant, parkeerplek) VALUES(?, ?, ?, ?, ?)");
			ps.setTimestamp(1, new Timestamp(reservering.getVan().getTimeInMillis()));
			ps.setTimestamp(2, new Timestamp(reservering.getTot().getTimeInMillis()));
			ps.setInt(3, ServiceProvider.getAutoService().getAutoIdOpKenteken(reservering.getAuto().getKenteken()));
			ps.setString(4, reservering.getKlant().getGebruikersnaam());
			ps.setInt(5, ServiceProvider.getParkeerplekService().getParkeerplekIdOpPlek(reservering.getParkeerplek()));
			
			ps.execute();
			
			ps.getConnection().close();
			ps.close();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
