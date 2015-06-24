package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.FactuurDAO;
import nl.atd.model.Factuur;
import nl.atd.model.Klant;

public class FactuurService {

	private FactuurDAO factuurDAO = new FactuurDAO();
	
	/**
	 * Get alle facturen
	 * @return facturen
	 */
	public ArrayList<Factuur> getAlleFacturen() {
		return this.factuurDAO.getAlleFacturen();
	}
	
	/**
	 * Get klanten facturen
	 * @param klant
	 * @return lijst met klant facturen
	 */
	public ArrayList<Factuur> getFacturenVanKlant(Klant klant) {
		return this.factuurDAO.getFacturenVanKlant(klant.getGebruikersnaam());
	}
	
	/**
	 * Get factuur op nummer
	 * @param nummer
	 * @return factuur of null
	 */
	public Factuur getFactuurOpNummer(int nummer) {
		return this.factuurDAO.getFactuurOpNummer(nummer);
	}
	
	/**
	 * Set factuur betaal status
	 * @param factuur
	 * @return gelukt
	 */
	public boolean setFactuurBetaald(Factuur factuur) {
		return this.factuurDAO.setFactuurBetaald(factuur);
	}
	
	/**
	 * Add factuur
	 * @param factuur
	 * @return factuurnummer
	 */
	public int addFactuur(Factuur factuur) {
		this.factuurDAO.addFactuur(factuur);
		return factuur.getFactuurnummer();
	}
}
