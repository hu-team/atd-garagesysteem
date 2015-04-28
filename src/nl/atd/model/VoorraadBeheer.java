package nl.atd.model;

import java.util.ArrayList;

public class VoorraadBeheer {
	private static VoorraadBeheer instance;
	
	private ArrayList<Artikel> artikelen;
	
	public VoorraadBeheer() {
		this.artikelen = new ArrayList<Artikel>();
	}
	
	/**
	 * Get instance of voorraadbeheer
	 * @return instance
	 */
	public static VoorraadBeheer getInstance() {
		if(instance == null) {instance = new VoorraadBeheer();}
		return instance;
	}
	
	/**
	 * Voeg artikel toe aan voorraad
	 * @param art artikel
	 */
	public void voegArtikelToe(Artikel art) {
		this.artikelen.add(art);
	}
	
	/**
	 * Get alle artikelen
	 * @return artikelen
	 */
	public ArrayList<Artikel> getArtikelen() {
		return this.artikelen;
	}
}