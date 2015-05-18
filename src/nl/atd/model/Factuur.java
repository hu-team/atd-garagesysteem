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
	
	private ArrayList<Klus> klussen;
	private ArrayList<Reservering> reserveringen;
	
	public Factuur() {
		this.betaald = false;
		this.calendar = Calendar.getInstance();
		
		this.klussen = new ArrayList<Klus>();
		this.reserveringen = new ArrayList<Reservering>();
	}
	
	/**
	 * Voeg een klus toe.
	 * 
	 * @param klus
	 */
	public void addKlus(Klus klus) {
		this.klussen.add(klus);
	}
	
	/**
	 * Voeg een reservering toe.
	 * 
	 * @param reservering
	 */
	public void addReservering(Reservering reservering) {
		this.reserveringen.add(reservering);
	}
	
	/**
	 * Get totaalprijs van alle onderdelen inclusief btw
	 * @return totaalprijs inclusief btw
	 */
	public double getTotaalPrijs() {
		double totaal = 0;
		
		for(int i = 0; i < this.reserveringen.size(); i++) {
			totaal += this.reserveringen.get(i).getTotaalPrijs();
		}
		
		for(int i = 0; i < this.klussen.size(); i++) {
			totaal += this.klussen.get(i).getTotaalPrijs();
		}
		
		return totaal;
	}
	
	/**
	 * Get reserveringen
	 * @return reserveringen
	 */
	public ArrayList<Reservering> getReserveringen() {
		return this.reserveringen;
	}
	
	/**
	 * Get klussen
	 * @return klussen
	 */
	public ArrayList<Klus> getKlussen() {
		return this.klussen;
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
