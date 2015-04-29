package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * @author ATD Developer
 *
 */

public class Parkeerplek {
	
	private char rij;
	private int plek;
	
	private ArrayList<Reservering> reserveringen;
	
	public Parkeerplek(char rij, int plek){
		this.rij = rij;
		this.plek = plek;
	}
	
	/**
	 * Get rij
	 * @return char rij
	 */
	public char getRij(){
		return this.rij;
	}
	
	/**
	 * Get plek
	 * @return int plek
	 */
	public int getPlek(){
		return this.plek;
	}
	
	/**
	 * Is bezet vandaag?
	 * @return boolean 
	 */
	public boolean isBezet(){
		return !this.isVrijTussen(Calendar.getInstance(), Calendar.getInstance());
	}
	
	/**
	 * Voegt een reservering bij
	 * @param Reservering res
	 */
	public void addReservering(Reservering res){
		reserveringen.add(res);
	}
	
	/**
	 * Get alle reserveringen
	 * @return ArrayList<Reservering> reservering
	 */
	public ArrayList<Reservering> getAlleReserveringen(){
		return this.reserveringen;
	}
	
	/**
	 * Is vrij tussen bepaalde datum?
	 * @param begin datum van reservering
	 * @param eind datum van reservering
	 * @return boolean
	 */
	public boolean isVrijTussen(Calendar begin, Calendar eind){
		for(Reservering res : reserveringen){
			if(begin.after(res.getVan()) && begin.before(res.getTot())  
					|| eind.after(res.getVan()) && eind.before(res.getTot())){
				return false;
			}
		}		
		return true;
	}
	
	/**
	 * Get huidige reservering
	 * @return Reservering res
	 */
	public Reservering getHuidigeReservering(){
		for(Reservering res : reserveringen){
			if(Calendar.getInstance().after(res.getVan()) 
					&& Calendar.getInstance().before(res.getTot())){
				return res;
			}
		}
		return null;
	}
}
