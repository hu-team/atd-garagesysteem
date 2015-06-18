package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Factuur met aantal onderdelen.
 * @author ATD Developers
 *
 */
public class Factuur {
	private int factuurnummer;
	private boolean betaald;
	private Calendar calendar;
	
	/**
	 * Lazy getter
	 */
	private ArrayList<Factuuronderdeel> onderdelen;
	
	public Factuur() {
		this.betaald = false;
		this.calendar = Calendar.getInstance();
		
		this.onderdelen = null;
	}
	
	
	/**
	 * @return the factuurnummer
	 */
	public int getFactuurnummer() {
		return factuurnummer;
	}




	/**
	 * @param factuurnummer the factuurnummer to set
	 */
	public void setFactuurnummer(int factuurnummer) {
		this.factuurnummer = factuurnummer;
	}




	/**
	 * Get totaalprijs van alle onderdelen inclusief btw
	 * @return totaalprijs inclusief btw
	 */
	public double getTotaalPrijs() {
		double totaal = 0;
		
		for(int i = 0; i < this.getOnderdelen().size(); i++) {
			totaal += this.getOnderdelen().get(i).getTotaalprijs();
		}
		
		return totaal;
	}
	
	
	/**
	 * Get Onderdelen
	 * @return onderdelen
	 */
	public ArrayList<Factuuronderdeel> getOnderdelen() {
		if(this.onderdelen == null) {
			// Lazy getter
			// TODO Lazy getter aanroepen
		}
		return this.onderdelen;
	}
	
	/**
	 * Get het btw bedrag van de factuur. 
	 * @return btw bedrag totaal
	 */
	public double getTotaalBTW() {
		double totaal = this.getTotaalPrijs();
		return (totaal / 121) * 100;
	}
	
	/**
	 * Is de factuur al betaald?
	 * @return betaald?
	 */
	public boolean isBetaald() {
		return this.betaald;
	}
	
	/**
	 * Zet de betaalstatus op de factuur
	 * @param bt betaalstatus
	 */
	public void setBetaald(boolean bt) {
		this.betaald = bt;
	}
	
	/**
	 * Get datum van factuur
	 * @return
	 */
	public Calendar getDatum() {
		return this.calendar;
	}
	
	/**
	 * Set datum van factuur
	 * @param dt datum
	 */
	public void setDatum(Calendar dt) {
		this.calendar = dt;
	}
}
