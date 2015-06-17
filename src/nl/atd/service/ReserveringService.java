package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.ReserveringDAO;import nl.atd.model.Parkeerplek;
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
	 * Get reserveringen op plek
	 * @param plek
	 * @return list reserveringen op plek
	 */
	public ArrayList<Reservering> getReserveringenOpPlek(Parkeerplek plek) {
		return this.reserveringDAO.getReserveringenOpPlek(ServiceProvider.getParkeerplekService().getParkeerplekIdOpPlek(plek));
	}
}
