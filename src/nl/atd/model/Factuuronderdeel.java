package nl.atd.model;

public class Factuuronderdeel {
	private Klus klus;
	private Reservering reservering;
	private double totaalprijs;
	private String omschrijving;
	
	public Factuuronderdeel() {
		this.klus = null;
		this.reservering = null;
	}

	/**
	 * @return the klus
	 */
	public Klus getKlus() {
		return klus;
	}

	/**
	 * @param klus the klus to set
	 */
	public void setKlus(Klus klus) {
		this.klus = klus;
	}

	/**
	 * @return the reservering
	 */
	public Reservering getReservering() {
		return reservering;
	}

	/**
	 * @param reservering the reservering to set
	 */
	public void setReservering(Reservering reservering) {
		this.reservering = reservering;
	}

	/**
	 * @return the totaalprijs
	 */
	public double getTotaalprijs() {
		return totaalprijs;
	}

	/**
	 * @param totaalprijs the totaalprijs to set
	 */
	public void setTotaalprijs(double totaalprijs) {
		this.totaalprijs = totaalprijs;
	}

	/**
	 * @return the omschrijving
	 */
	public String getOmschrijving() {
		return omschrijving;
	}

	/**
	 * @param omschrijving the omschrijving to set
	 */
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	
	
}
