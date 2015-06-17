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
	
	/**
	 * Add parkeerplek
	 * @param plek
	 * @return gelukt?
	 */
	public boolean addParkeerplek(Parkeerplek plek) {
		if(this.parkeerplekDAO.getParkeerplekkenOpPlekEnRij(plek.getRij(), plek.getPlek()).size() == 0) {
			return this.parkeerplekDAO.addParkeerplek(plek);
		}
		return false;
	}
	
	/**
	 * Get parkeerplek ID
	 * @param plek
	 * @return ID of 0
	 */
	public int getParkeerplekIdOpPlek(Parkeerplek plek) {
		return this.parkeerplekDAO.getParkeerplekIdOpPlek(plek);
	}
}
