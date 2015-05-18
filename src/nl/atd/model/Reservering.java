package nl.atd.model;

import java.util.Calendar;

/**
 * Reservering van parkeerplek
 * @author ATD Developer
 *
 */

public class Reservering {
	private Calendar van;
	private Calendar tot;
	
	private Klant klant;
	private Auto auto;
	
	public Reservering(Klant kl, Auto au){
		this.klant = kl;
		this.auto = au;
		
		van = Calendar.getInstance();
		tot = Calendar.getInstance();
	}
	
	/**
	 * Set begin datum van reservering
	 * @param vn van
	 */
	public void setVan(Calendar vn){
		van = vn;
	}
	
	/**
	 * Set eind datum van reservering
	 * @param tt tot
	 */
	public void setTot(Calendar tt){
		tot = tt;
	}
	
	/**
	 * Get begin datum
	 * @return Calendar van
	 */
	public Calendar getVan(){
		return van;
	}
	
	/**
	 * Get eind datum
	 * @return Calendar tot
	 */
	public Calendar getTot(){
		return tot;
	}
	
	/**
	 * Get klant
	 * @return Klant klant
	 */
	public Klant getKlant(){
		return klant;
	}
	
	/**
	 * Get auto
	 * @return Auto auto
	 */
	public Auto getAuto(){
		return auto;
	}

	/**
	 * Get totaal prijs(15 * dagen)
	 * @return double totaal prijs
	 */
	public double getTotaalPrijs() {
		
		long milisecond1 = van.getTimeInMillis();
		long milisecond2 = tot.getTimeInMillis();
		
		long diff = milisecond2 - milisecond1;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		return 15 * (int)diffDays;
	}

	/**
	 * Get factuur omschrijving
	 * @return String factooromschrijving
	 */
	public String getFactuurOmschrijving() {
		
		return "Parkeerplek reservering: " + getKlant() + " " + getAuto() + "\t\t €" + getTotaalPrijs();
	}
	
	
	
}
