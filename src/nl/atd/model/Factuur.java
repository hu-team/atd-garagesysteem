package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Factuur met aantal onderdelen.
 * @author ATD Developers
 *
 */
public class Factuur {
	private boolean betaald;
	private Calendar calendar;
	
	private ArrayList<FactuurOnderdeel> onderdelen;
	
	public Factuur() {
		this.betaald = false;
		this.calendar = Calendar.getInstance();
		
		this.onderdelen = new ArrayList<>();
	}
	
	/**
	 * Voeg een onderdeel toe die FactuurOnderdeel als super class heeft.
	 * 
	 * @param onderdeel onderdeel toe te voegen
	 */
	public void addOnderdeel(FactuurOnderdeel onderdeel) {
		this.onderdelen.add(onderdeel);
	}
	
	/**
	 * Get totaalprijs van alle onderdelen inclusief btw
	 * @return totaalprijs inclusief btw
	 */
	public double getTotaalPrijs() {
		double totaal = 0;
		
		for(int i = 0; i < this.onderdelen.size(); i++) {
			totaal += this.onderdelen.get(i).getTotaalPrijs();
		}
		
		return totaal;
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
