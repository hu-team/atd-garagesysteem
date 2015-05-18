package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Klant object
 * @author ATD Developers
 *
 */
public class Klant extends Persoon {
	private String email;
	private Calendar laatsteBezoek;
	
	private ArrayList<Auto> autos;
	
	public Klant(String nm) {
		super(nm);
		
		this.email = "";
		this.laatsteBezoek = Calendar.getInstance();
		this.autos = new ArrayList<Auto>();
	}
	
	/**
	 * Get alle auto's in arraylist
	 * @return ArrayList met auto's
	 */
	public ArrayList<Auto> getAutos() {
		return this.autos;
	}
	
	/**
	 * Voeg auto toe aan auto's
	 * @param a Auto om toe te voegen
	 */
	public void voegAutoToe(Auto a) {
		this.autos.add(a);
	}
	
	/**
	 * Get e-mail adres van klant
	 * @return E-mail adres
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Get laatste bezoek
	 * @return laatste bezoek klant
	 */
	public Calendar getLaatsteBezoek() {
		return this.laatsteBezoek;
	}
	
	/**
	 * Set laatste bezoek van klant
	 * @param lb Laatste bezoek
	 */
	public void setLaatsteBezoek(Calendar lb) {
		this.laatsteBezoek = lb;
	}

	/**
	 * Set email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
