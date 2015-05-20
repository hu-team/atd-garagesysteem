package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.KlusDAO;
import nl.atd.model.Klus;

public class KlusService {
	private KlusDAO klusDAO = new KlusDAO();
	
	/**
	 * Get alle klussen
	 * @return array met klussen
	 */
	public ArrayList<Klus> getKlussen() {
		return this.klusDAO.getAlleKlussen();
	}
	
}
