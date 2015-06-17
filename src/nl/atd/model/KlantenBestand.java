package nl.atd.model;

import java.util.ArrayList;

/**
 * KlantenBestand, zorgt voor centrale lijst met klanten
 * @author ATD Developers
 * 
 * @deprecated NIET MEER GEBRUIKEN, GEBRUIK SERVICE VOOR PARKEERPLEKKEN
 */
public class KlantenBestand {
	private static KlantenBestand instance;
	
	private ArrayList<Klant> klanten;
	
	/**
	 * Maak leeg klantenbestand aan. Gebruik getInstance om een centrale opslag te gebruiken
	 * @see {KlantenBestand.getInstance()}
	 */
	public KlantenBestand() {
		this.klanten = new ArrayList<Klant>();
	}
	
	/**
	 * Get huidige klantenbestand, of maak nieuwe als er nog nooit een aangemaakt is
	 * @return KlantenBestand
	 */
	public static KlantenBestand getInstance() {
		if(instance == null) instance = new KlantenBestand(); 
		return instance;
	}
	
	/**
	 * Krijg een lijst met alle klanten
	 * @return Klanten array
	 */
	public ArrayList<Klant> getKlanten() {
		return this.klanten;
	}
	
	/**
	 * Voeg klant toe
	 * @param kl Klant
	 */
	public void addKlant(Klant kl) {
		this.klanten.add(kl);
	}
	
	/**
	 * Verwijder uit klanten lijst
	 * @param kl Klant
	 */
	public void verwijderKlant(Klant kl) {
		this.klanten.remove(kl);
	}
}
