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
	 * Get auto op kenteken
	 * @param kenteken
	 * @return auto of null
	 */
	public Auto getAutoOpKenteken(String kenteken) {
		return this.autoDAO.getAutoOpKenteken(kenteken);
	}
	
	/**
	 * Get auto op database ID
	 * @param id
	 * @return auto of null
	 */
	public Auto getAuto(int id) {
		return this.autoDAO.getAutoOpId(id);
	}

	/**
	 * Toevoegen van auto
	 * @param klant klant gebruikersnaam
	 * @param auto de auto
	 * @return is het gelukt?
	 */
	public boolean addAuto(String klant, Auto auto) {
		if(this.autoDAO.getAutoOpKenteken(auto.getKenteken()) == null) {
			return this.autoDAO.addAuto(klant, auto);
		}
		return false;
	}
	
	/**
	 * Get auto id van kenteken
	 * @param kenteken
	 * @return autoid
	 */
	public int getAutoIdOpKenteken(String kenteken) {
		return this.autoDAO.getAutoIdOpKenteken(kenteken);
	}
	
	/**
	 * Verwijder alle autos uit database
	 * LET OP: gebruik alleen binnen tests
	 */
	public void deleteAlleAutos() {
		autoDAO.deleteAlles();
	}

	/**
	 * Set laatste beurt van auto
	 * @param auto met nieuwe laatstebeurt
	 */
	public void setAutoLaatsteBeurt(Auto auto) {
		this.autoDAO.setAutoLaatsteBeurt(auto);
	}
}
