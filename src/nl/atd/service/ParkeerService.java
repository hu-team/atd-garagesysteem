package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.ParkeerDAO;
import nl.atd.model.Parkeerplek;

public class ParkeerService {
	private ParkeerDAO parkeerDAO = new ParkeerDAO();
	
	/**
	 * Get alle parkeerplekken
	 * @return list parkeerplekken
	 */
	public ArrayList<Parkeerplek> getAllePlekken() {
		return this.parkeerDAO.getAllePlekken();
	}
}
