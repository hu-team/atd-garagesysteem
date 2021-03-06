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
	 * Get parkeerplekken vrij op datum
	 * @param datum
	 * @return plekken
	 */
	public ArrayList<Parkeerplek> getParkeerplekkenOpDatum(Calendar datum){
		return this.parkeerplekDAO.getParkeerplekkenOpDatum(datum);
	}
	
	/**
	 * Get vrijeplekken tussen
	 * @param van
	 * @param tot
	 * @return plekken
	 */
	public ArrayList<Parkeerplek> getAlleVrijePlekkenTussenVanTot(Calendar van, Calendar tot){
		return this.parkeerplekDAO.getParkeerplekkenTussenVanTot(van, tot);
	}
	
	/**
	 * Get plek op plek en rij
	 * @param rij
	 * @param plek
	 * @return plek
	 */
	public Parkeerplek getParkeerplekOpPlekEnRij(char rij, int plek){
		return this.parkeerplekDAO.getParkeerplekkenOpPlekEnRij(rij, plek).get(0);
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
	
	/**
	 * Get parkeerplek op id
	 * @param id
	 * @return parkeerplek of null
	 */
	public Parkeerplek getParkeerplekOpId(int id) {
		return this.parkeerplekDAO.getParkeerplekOpId(id);
	}
}
