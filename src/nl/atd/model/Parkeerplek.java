package nl.atd.model;

/**
 * 
 * @author ATD Developer
 *
 */

public class Parkeerplek {
	
	private char rij;
	private int plek;
	
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
}
