package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.FactuurDAO;
import nl.atd.model.Factuur;

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
	 * Add factuur
	 * @param factuur
	 * @return factuurnummer
	 */
	public int addFactuur(Factuur factuur) {
		this.factuurDAO.addFactuur(factuur);
		return factuur.getFactuurnummer();
	}
}
