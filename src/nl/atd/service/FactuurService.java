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
}
