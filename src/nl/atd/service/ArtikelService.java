package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.ArtikelDAO;
import nl.atd.model.Artikel;

public class ArtikelService {
	private ArtikelDAO artikelDAO = new ArtikelDAO();
	
	/**
	 * Alle artikelen uit database ontvangen als objecten.
	 * @return array met Artikel
	 */
	public ArrayList<Artikel> getAlleArtikelen(){
		return this.artikelDAO.getAlleArtikelen();
	}
	
	/**
	 * Artikel opzoeken met code
	 * @param code artikelcode
	 * @return Artikel of null
	 */
	public Artikel getArtikelByCode(String code){
		return this.artikelDAO.getArtikelByCode(code);
	}
	
	/**
	 * Artikelen opzoeken op naam.
	 * @param naam naam om op te zoeken
	 * @return array met artikelen
	 */
	public ArrayList<Artikel> getArtikelByNaam(String naam){
		return this.artikelDAO.getArtikelByName(naam);
	}
	
	/**
	 * Artikel toevoegen aan database
	 * @param artikel artikel om toe te voegen
	 * @return gelukt?
	 */
	public boolean addArtikel(Artikel artikel){
		if(this.artikelDAO.getArtikelByCode(artikel.getCode()) == null){
			return this.artikelDAO.addArtikel(artikel);
		}
		return false;
	}
	
	/**
	 * Artikel aanpassen, zorg dat code niet gewijzigd wordt!
	 * @param artikel artikel die originele code en gewijzigde data bevat.
	 * @return gelukt?
	 */
	public boolean editArtikel(Artikel artikel){
		if(this.artikelDAO.getArtikelByCode(artikel.getCode()) != null){
			return this.artikelDAO.editArtikel(artikel);
		}
		return false;
	}
}
