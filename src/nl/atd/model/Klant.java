package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Klant object
 * 
 * @author ATD Developers
 *
 */
public class Klant extends Persoon {
	private String email;
	private Calendar laatsteBezoek;
	
	/**
	 * @since 16-06-2015, sprint 3
	 */
	private String adres;
	private String postcode;
	private String woonplaats;
	private String telefoonnummer;

	private ArrayList<Auto> autos;

	public Klant(String nm) {
		super(nm);

		this.email = "";
		this.laatsteBezoek = Calendar.getInstance();
		this.autos = new ArrayList<Auto>();
	}
	
	public Klant(String voornaam, String achternaam, String email, String adres, String postcode, String woonplaats, String telefoonnummer) {
		super(voornaam + " " + achternaam);
		this.email = email;
		this.adres = adres;
		this.postcode = postcode;
		this.woonplaats = woonplaats;
		this.telefoonnummer = telefoonnummer;
	}

	/**
	 * Get alle auto's in arraylist
	 * 
	 * @return ArrayList met auto's
	 */
	public ArrayList<Auto> getAutos() {
		return this.autos;
	}

	/**
	 * Voeg auto toe aan auto's
	 * 
	 * @param a
	 *            Auto om toe te voegen
	 */
	public void voegAutoToe(Auto a) {
		this.autos.add(a);
	}

	/**
	 * Get e-mail adres van klant
	 * 
	 * @return E-mail adres
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Get laatste bezoek
	 * 
	 * @return laatste bezoek klant
	 */
	public Calendar getLaatsteBezoek() {
		return this.laatsteBezoek;
	}

	/**
	 * Set laatste bezoek van klant
	 * 
	 * @param lb
	 *            Laatste bezoek
	 */
	public void setLaatsteBezoek(Calendar lb) {
		this.laatsteBezoek = lb;
	}

	/**
	 * Set email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get Adres van klant
	 * @return the adres
	 */
	public String getAdres() {
		return adres;
	}

	/**
	 * Set Adres van klant
	 * @param adres the adres to set
	 */
	public void setAdres(String adres) {
		this.adres = adres;
	}

	/**
	 * Get postcode van klant, 0000AA
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * Set Postcode.
	 * Let Op: Zorg dat het zonder spaties wordt ingevoerd, dus geen 0000 AA maar 0000AA!
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Get Woonplaats
	 * @return the woonplaats
	 */
	public String getWoonplaats() {
		return woonplaats;
	}

	/**
	 * Set woonplaats
	 * @param woonplaats the woonplaats to set
	 */
	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

	/**
	 * Get telefoonnummer
	 * @return the telefoonnummer
	 */
	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	/**
	 * Set telefoonnummer
	 * @param telefoonnummer the telefoonnummer to set
	 */
	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	/**
	 * Override van equals 
	 * 
	 * @param ander 
	 */
	public boolean equals(Object ander) {
		boolean b;

		if (ander instanceof Klant) {
			b = true;
		} else {
			b = false;
		}

		b = b && super.equals(ander) && (this.email.equals(((Klant)ander).getEmail()));
		b = b && (this.adres.equals(((Klant)ander).getAdres()));
		b = b && (this.postcode.equals(((Klant)ander).getPostcode()));
		b = b && (this.woonplaats.equals(((Klant)ander).getWoonplaats()));
		b = b && (this.telefoonnummer.equals(((Klant)ander).getTelefoonnummer()));
		
		// Calendar vergelijken, DAY.OF.YEAR - YEAR - HOURS : MINUTES
		b = b && ((this.laatsteBezoek == null && ((Klant)ander).getLaatsteBezoek() == null) || 
				((this.laatsteBezoek).get(Calendar.YEAR) == ((Klant)ander).getLaatsteBezoek().get(Calendar.YEAR) &&
				(this.laatsteBezoek).get(Calendar.HOUR_OF_DAY) == ((Klant)ander).getLaatsteBezoek().get(Calendar.HOUR_OF_DAY) &&
				(this.laatsteBezoek).get(Calendar.MINUTE) == ((Klant)ander).getLaatsteBezoek().get(Calendar.MINUTE)));
		
		return b;
	}

}
