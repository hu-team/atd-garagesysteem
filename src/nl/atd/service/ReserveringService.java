package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.ReserveringDAO;import nl.atd.model.Klant;
import nl.atd.model.Parkeerplek;
import nl.atd.model.Reservering;

public class ReserveringService {
	private ReserveringDAO reserveringDAO = new ReserveringDAO();
	
	/**
	 * Get alle reserveringen
	 * @return list reserveringen
	 */
	public ArrayList<Reservering> getAlleReserveringen() {
		return this.reserveringDAO.getAlleReserveringen();
	}
	
	/**
	 * Get alle reserveringen van klant
	 * @param klant
	 * @return list reserveringen
	 */
	public ArrayList<Reservering> getReserveringenVanKlant(Klant klant) {
		return this.reserveringDAO.getReserveringenVanKlant(klant);
	}
	
	/**
	 * Get reserveringen op plek
	 * @param plek
	 * @return list reserveringen op plek
	 */
	public ArrayList<Reservering> getReserveringenOpPlek(Parkeerplek plek) {
		return this.reserveringDAO.getReserveringenOpPlek(ServiceProvider.getParkeerplekService().getParkeerplekIdOpPlek(plek));
	}
	
	/**
	 * Add reservering
	 * @param reservering
	 * @return gelukt?
	 */
	public boolean addReservering(Reservering reservering){
		return this.reserveringDAO.addReservering(reservering);
	}

	/**
	 * Get reservering op id
	 * @param reserveringid
	 * @return reservering of null
	 */
	public Reservering getReserveringOpId(int reserveringid) {
		return this.reserveringDAO.getReserveringOpId(reserveringid);
	}
}
