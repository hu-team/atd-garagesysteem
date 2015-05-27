package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.KlantDAO;
import nl.atd.model.Klant;

public class KlantService {

	private KlantDAO klantDAO = new KlantDAO();
	
	public ArrayList<Klant> getAlleKlantenAuto(){
		return this.klantDAO.getAlleKlanten(true);
	}
	
	public ArrayList<Klant> getAlleKlanten(){
		return this.klantDAO.getAlleKlanten(false);
	}
	
	public Klant getKlantByGebruikersnaam(String gebruikersnaam){
		return this.klantDAO.getKlant(gebruikersnaam);
	}
	
	public boolean addKlant(Klant klant){
		if(this.klantDAO.getKlant(klant.getGebruikersnaam()) == null){
			return this.klantDAO.addKlant(klant);
		}
		return false;
	}
	
}
