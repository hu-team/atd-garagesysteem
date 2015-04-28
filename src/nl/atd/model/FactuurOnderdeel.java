package nl.atd.model;

/**
 * Met de abstracte klasse FactuurOnderdeel geeft je het object de juiste methodes die er nodig zijn
 * om een factuur te kunnen maken van een bepaald object
 * 
 * @author ATD Developers
 *
 */
public abstract class FactuurOnderdeel {
	
	/**
	 * Get totaalprijs van het onderdeel
	 * @return (Sub)totaal onderdeel
	 */
	public abstract double getTotaalPrijs();
	
	/**
	 * Get Klant van het onderdeel
	 * @return Klant
	 */
	public abstract Klant getKlant();
	
	/**
	 * Get omschrijving die op factuur komt bij het bewuste onderdeel
	 * @return Omschrijving van onderdeel
	 */
	public abstract String getFactuurOmschrijving();
}
