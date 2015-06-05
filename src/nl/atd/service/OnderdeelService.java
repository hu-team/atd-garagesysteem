package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.OnderdeelDAO;
import nl.atd.model.Klus;
import nl.atd.model.Onderdeel;

public class OnderdeelService {
	private OnderdeelDAO onderdeelDAO = new OnderdeelDAO();
	
	/**
	 * Voeg onderdeel toe
	 * @param onderdeel
	 * @param klus
	 * @return gelukt?
	 */
	public boolean addOnderdeel(Onderdeel onderdeel, Klus klus){
		return this.onderdeelDAO.addOnderdeel(onderdeel, klus);
	}
	
	/**
	 * Alle onderdelen, van alle klussen
	 * @return onderdelen
	 */
	public ArrayList<Onderdeel> getAlleOnderdelen() {
		return this.onderdeelDAO.getAlleOnderdelen();
	}
	
	/**
	 * Alle onderdelen van bepaalde klus
	 * @param klus
	 * @return onderdelen
	 */
	public ArrayList<Onderdeel> getAlleOnderdelenVanKlus(Klus klus) {
		return this.onderdeelDAO.getAlleOnderdelenVanKlus(ServiceProvider.getKlusService().getKlusIdOpKlus(klus));
	}
	
	/**
	 * Verwijder alle onderdelen uit database
	 * LET OP: gebruik alleen binnen tests
	 */
	public void deleteAlleOnderdelen() {
		onderdeelDAO.deleteAlles();
	}
}
