package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.MonteurDAO;
import nl.atd.model.Monteur;

public class MonteurService {
	private MonteurDAO monteurDAO = new MonteurDAO();
	
	/**
	 * Get alle monteuren
	 * @return monteuren
	 */
	public ArrayList<Monteur> getAlleMonteuren() {
		return this.monteurDAO.getAlleMonteuren();
	}
	
	/**
	 * Get monteur op gebruikersnaam
	 * @param gebruikersnaam
	 * @return monteur of null
	 */
	public Monteur getMonteurByGebruikersnaam(String gebruikersnaam) {
		return this.monteurDAO.getMonteur(gebruikersnaam);
	}
	
	/**
	 * Voeg monteur toe
	 * @param monteur
	 * @return gelukt?
	 */
	public boolean addMonteur(Monteur monteur) {
		if(this.monteurDAO.getMonteur(monteur.getGebruikersnaam()) == null) {
			return this.monteurDAO.addMonteur(monteur);
		}
		return false;
	}
}
