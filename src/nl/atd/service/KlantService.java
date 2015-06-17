package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.KlantDAO;
import nl.atd.model.Klant;

public class KlantService {

	private KlantDAO klantDAO = new KlantDAO();
	
	/**
	 * Get Alle klanten, inclusief auto's.
	 * @return klanten met autos
	 */
	public ArrayList<Klant> getAlleKlantenAuto(){
		return this.klantDAO.getAlleKlanten(true);
	}
	
	/**
	 * Get alle klanten, zonder gekoppelde autos.
	 * Door 2 methodes te maken scheelt het tijd in verwerking
	 * @return klanten
	 */
	public ArrayList<Klant> getAlleKlanten(){
		return this.klantDAO.getAlleKlanten(false);
	}
	
	/**
	 * Klant zoeken op gebruikersnaam
	 * @param gebruikersnaam gebruikersnaam om op te zoeken
	 * @return klant of null
	 */
	public Klant getKlantByGebruikersnaam(String gebruikersnaam){
		return this.klantDAO.getKlant(gebruikersnaam);
	}
	
	/**
	 * Klant toevoegen
	 * @param klant toe te voegen klant
	 * @return gelukt?
	 */
	public boolean addKlant(Klant klant){
		if(this.klantDAO.getKlant(klant.getGebruikersnaam()) == null){
			return this.klantDAO.addKlant(klant);
		}
		return false;
	}
	
	/**
	 * Aanpassen van klant, verander gebruikersnaam niet!
	 * @param klant klant om aan te passen
	 */
	public void editKlant(Klant klant) {
		this.klantDAO.editKlant(klant);
	}
	
	/**
	 * Verwijder alle klanten uit database
	 * LET OP: gebruik alleen binnen tests
	 */
	public void deleteAlleKlanten() {
		klantDAO.deleteAlles();
	}
}
