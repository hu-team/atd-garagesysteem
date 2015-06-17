package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.ParkeerplekDAO;
import nl.atd.model.Parkeerplek;

public class ParkeerplekService {
	private ParkeerplekDAO parkeerplekDAO = new ParkeerplekDAO();
	
	/**
	 * Get alle parkeerplekken
	 * @return list parkeerplekken
	 */
	public ArrayList<Parkeerplek> getAllePlekken() {
		return this.parkeerplekDAO.getAllePlekken();
	}
}
