package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.AutoDAO;
import nl.atd.model.Auto;

public class AutoService {
	private AutoDAO autoDAO = new AutoDAO();
	
	/**
	 * Get alle autos
	 * @return array met autos
	 */
	public ArrayList<Auto> getAlleAutos() {
		return this.autoDAO.getAlleAutos();
	}
	
	/**
	 * Get alle autos van een klant
	 * @param gebruikersnaam
	 * @return array met autos
	 */
	public ArrayList<Auto> getAutosVanKlant(String gebruikersnaam) {
		return this.autoDAO.getAutosVanKlant(gebruikersnaam);
	}
	
	/**
	 * Get auto op database ID
	 * @param id
	 * @return auto of null
	 */
	public Auto getAuto(int id) {
		return this.autoDAO.getAutoOpId(id);
	}
}
