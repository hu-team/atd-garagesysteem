package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.FactuuronderdeelDAO;
import nl.atd.model.Factuur;
import nl.atd.model.Factuuronderdeel;

public class FactuuronderdeelService {

	private FactuuronderdeelDAO factuuronderdeelDAO = new FactuuronderdeelDAO();
	
	/**
	 * Get alle factuuronderdelen van factuur
	 * @return facturen
	 */
	public ArrayList<Factuuronderdeel> getAlleOnderdelen(Factuur factuur) {
		return this.factuuronderdeelDAO.getFactuuronderdelenVanFactuur(factuur);
	}
}
