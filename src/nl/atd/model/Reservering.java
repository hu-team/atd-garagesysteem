package nl.atd.model;

import java.util.Calendar;

/**
 * 
 * @author ATD Developer
 *
 */

public class Reservering extends FactuurOnderdeel{
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
	@Override
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
	 * Get totaal prijs
	 * @return double totaal prijs
	 */
	@Override
	public double getTotaalPrijs() {
		
		return 0;
	}

	/**
	 * Get factuur omschrijving
	 * @return String factooromschrijving
	 */
	@Override
	public String getFactuurOmschrijving() {
		
		return null;
	}
	
	
	
}
