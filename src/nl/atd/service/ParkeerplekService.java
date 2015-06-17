package nl.atd.service;

import java.util.ArrayList;
import java.util.Calendar;

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
	
	/**
	 * Get alle vrije plekken op datum
	 * @param datum datum om te zoeken
	 * @return list met vrije plekken
	 */
	public ArrayList<Parkeerplek> getAlleVrijePlekken(Calendar datum) {
		return this.parkeerplekDAO.getAlleVrijePlekken(datum);
	}
}
