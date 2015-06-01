package nl.atd.model;

import java.util.Calendar;

/**
 * Auto data object
 * 
 * @author ATD Developers
 *
 */
public class Auto {

	private String merk;
	private String model;
	private int bouwjaar;
	private Calendar laatsteBeurt;
	private String kenteken;

	public Auto(String mk, String ml, int bj, Calendar lb) {
		this.merk = mk;
		this.model = ml;
		this.bouwjaar = bj;
		this.kenteken = "";

		this.laatsteBeurt = lb;
	}

	/**
	 * Is laatste beurt na 6 maanden geweest?
	 * 
	 * @return na 6 maanden?
	 */
	public boolean isLaatsteBeurt() {
		Calendar overtijd = Calendar.getInstance();
		Calendar vandaag = Calendar.getInstance();

		overtijd = laatsteBeurt;
		overtijd.add(Calendar.MONTH, +6);

		return vandaag.after(overtijd);
	}

	/**
	 * Get merk
	 * 
	 * @return merk
	 */
	public String getMerk() {
		return merk;
	}

	/**
	 * Get model
	 * 
	 * @return model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Get bouwjaar
	 * 
	 * @return bouwjaar
	 */
	public int getBouwjaar() {
		return bouwjaar;
	}

	/**
	 * Get laatste beurt datum
	 * 
	 * @return Datum van laatste beurt
	 */
	public Calendar getLaatsteBeurt() {
		return laatsteBeurt;
	}

	/**
	 * Get Kenteken van auto
	 * 
	 * @return Kenteken
	 */
	public String getKenteken() {
		return this.kenteken;
	}

	/**
	 * Set Kenteken van auto
	 * 
	 * @param Nieuw
	 *            kenteken
	 */
	public void setKenteken(String nieuwKent) {
		this.kenteken = nieuwKent;
	}

	@Override
	public String toString() {
		return merk + " " + model;
	}

	@Override
	public boolean equals(Object ander) {
		boolean b;

		if (ander instanceof Auto) {
			b = true;
		} else {
			b = false;
		}

		b = b && (this.merk.equals(((Auto) ander).getMerk()));
		b = b && (this.model.equals(((Auto) ander).getModel()));
		b = b && (this.bouwjaar == ((Auto) ander).getBouwjaar());
		// omdat nog gefixt moet worden met datum in de sql query
		//b = b && (this.laatsteBeurt.equals(((Auto) ander).getLaatsteBeurt()));
		b = b && (this.kenteken.equals(((Auto) ander).getKenteken()));

		return b;
	}
}