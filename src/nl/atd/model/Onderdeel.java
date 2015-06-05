package nl.atd.model;

/**
 * 
 * @author ATD Developers
 *
 */
public class Onderdeel {

	private int aantal;
	private Artikel artikel;

	public Onderdeel(Artikel ar, int aan) {
		this.artikel = ar;
		this.aantal = aan;
	}

	/**
	 * Get artikel
	 * 
	 * @return Artikel artikel
	 */
	public Artikel getArtikel() {
		return this.artikel;
	}

	/**
	 * Get aantal
	 * 
	 * @return int aantal
	 */
	public int getAantal() {
		return this.aantal;
	}

	/**
	 * Override van equals
	 * 
	 * @param ander
	 */
	public boolean equals(Object ander) {
		boolean b;

		if (ander instanceof Onderdeel) {
			b = true;
		} else {
			b = false;
		}

		b = b && (this.aantal == ((Onderdeel) ander).getAantal());
		b = b && (this.artikel.equals(((Onderdeel) ander).getArtikel()));
		return b;
	}
}
