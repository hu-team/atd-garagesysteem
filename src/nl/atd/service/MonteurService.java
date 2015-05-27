package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.MonteurDAO;
import nl.atd.model.Monteur;

public class MonteurService {
	private MonteurDAO monteurDAO = new MonteurDAO();
	
	public ArrayList<Monteur> getAlleMonteuren() {
		return this.monteurDAO.getAlleMonteuren();
	}
	
	public Monteur getMonteurByGebruikersnaam(String gebruikersnaam) {
		return this.monteurDAO.getMonteur(gebruikersnaam);
	}
	
	public boolean addMonteur(Monteur monteur) {
		if(this.monteurDAO.getMonteur(monteur.getGebruikersnaam()) == null) {
			return this.monteurDAO.addMonteur(monteur);
		}
		return false;
	}
}
