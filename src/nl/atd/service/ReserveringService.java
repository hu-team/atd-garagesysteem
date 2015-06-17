package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.ReserveringDAO;import nl.atd.model.Reservering;

public class ReserveringService {
	private ReserveringDAO reserveringDAO = new ReserveringDAO();
	
	/**
	 * Get alle reserveringen
	 * @return list reserveringen
	 */
	public ArrayList<Reservering> getAlleReserveringen() {
		return this.reserveringDAO.getAlleReserveringen();
	}
}
