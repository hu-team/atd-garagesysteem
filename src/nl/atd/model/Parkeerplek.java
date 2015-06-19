package nl.atd.model;

/**
 * 
 * @author ATD Developer
 *
 */

public class Parkeerplek {

	private char rij;
	private int plek;

	public Parkeerplek(char rij, int plek) {
		this.rij = rij;
		this.plek = plek;
	}

	/**
	 * Get rij
	 * 
	 * @return char rij
	 */
	public char getRij() {
		return this.rij;
	}

	/**
	 * Get plek
	 * 
	 * @return int plek
	 */
	public int getPlek() {
		return this.plek;
	}

	/**
	 * Override Equals
	 * 
	 * @return boolean
	 */
	public boolean equals(Object ander) {
		boolean b;

		if (ander instanceof Parkeerplek) {
			b = true;
		} else {
			b = false;
		}

		b = b && (this.rij == ((Parkeerplek) ander).getRij());
		b = b && (this.plek == ((Parkeerplek) ander).getPlek());

		return b;
	}
}
