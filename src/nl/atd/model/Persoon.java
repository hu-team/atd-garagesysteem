package nl.atd.model;

/**
 * Generiek persoon klasse
 * @author ATD Developers
 *
 */
public abstract class Persoon {
	private String naam;
	
	public Persoon(String nm) {
		this.naam = nm;
	}
	
	/**
	 * Get Naam
	 * @return naam van persoon
	 */
	public String getNaam() {
		return this.naam;
	}
	
	/**
	 * Set Naam
	 * @param nm naam van persoon
	 */
	public void setNaam(String nm) {
		this.naam = nm;
	}
}
