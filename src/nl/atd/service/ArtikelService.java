package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.ArtikelDAO;
import nl.atd.model.Artikel;

public class ArtikelService {
	private ArtikelDAO artikelDAO = new ArtikelDAO();
	
	public ArrayList<Artikel> getAlleArtikelen(){
		return this.artikelDAO.getAlleArtikelen();
	}
	
	public Artikel getArtikelByCode(String code){
		return this.artikelDAO.getArtikelByCode(code);
	}
	
	public ArrayList<Artikel> getArtikelByNaam(String naam){
		return this.artikelDAO.getArtikelByName(naam);
	}
	
	public boolean addArtikel(Artikel artikel){
		if(this.artikelDAO.getArtikelByCode(artikel.getCode()) == null){
			return this.artikelDAO.addArtikel(artikel);
		}
		return false;
	}
	
	public boolean editArtikel(Artikel artikel){
		if(this.artikelDAO.getArtikelByCode(artikel.getCode()) != null){
			return this.artikelDAO.editArtikel(artikel);
		}
		return false;
	}
}
