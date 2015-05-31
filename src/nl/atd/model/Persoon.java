package nl.atd.model;

/**
 * Generiek persoon klasse
 * 
 * @author ATD Developers
 *
 */
public abstract class Persoon {
	private String naam;

	private String gebruikersnaam;
	private String wachtwoord;

	public Persoon(String nm) {
		this.naam = nm;
	}

	/**
	 * Get Naam
	 * 
	 * @return naam van persoon
	 */
	public String getNaam() {
		return this.naam;
	}

	/**
	 * Set Naam
	 * 
	 * @param nm
	 *            naam van persoon
	 */
	public void setNaam(String nm) {
		this.naam = nm;
	}

	/**
	 * Get gebruikersnaam
	 * 
	 * @return string met gebruikersnaam
	 */
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	/**
	 * Set gebruikersnaam
	 * 
	 * @param gebruikersnaam
	 */
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	/**
	 * Get wachtwoord
	 * 
	 * @return wachtwoord
	 */
	public String getWachtwoord() {
		return wachtwoord;
	}

	/**
	 * Set wachtwoord
	 * 
	 * @param wachtwoord
	 */
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	/**
	 * Override equals methode
	 * 
	 * @param ander
	 */
	public boolean equals(Object ander) {
		boolean b;

		if (ander instanceof Persoon) {
			b = true;
		} else {
			b = false;
		}

		b = b && (this.naam.equals(((Persoon) ander).getNaam()));
		b = b && (this.gebruikersnaam.equals(((Persoon) ander).getGebruikersnaam()));
		b = b && (this.wachtwoord.equals(((Persoon) ander).getWachtwoord()));

		return b;
	}

}
