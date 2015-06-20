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
	public boolean isLaatsteBeurtRedelijk() {
		if(this.laatsteBeurt == null) return false;
		
		Calendar streef = Calendar.getInstance();

		Calendar lb = (Calendar)laatsteBeurt.clone();
		if(lb == null) return false;
		
		streef.add(Calendar.MONTH, -6);

		return !lb.before(streef);
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
	 * Set laatste beurt datum
	 * 
	 * @param cal nieuwe datum
	 */
	public void setLaatsteBeurt(Calendar cal) {
		this.laatsteBeurt = cal;
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
		b = b && (this.kenteken.equals(((Auto) ander).getKenteken()));
				
		// Calendar vergelijken, DAY.OF.YEAR - YEAR - HOURS : MINUTES
		b = b && ((this.laatsteBeurt == null && ((Auto)ander).getLaatsteBeurt() == null) || 
				((this.laatsteBeurt).get(Calendar.YEAR) == ((Auto)ander).getLaatsteBeurt().get(Calendar.YEAR) &&
				(this.laatsteBeurt).get(Calendar.HOUR_OF_DAY) == ((Auto)ander).getLaatsteBeurt().get(Calendar.HOUR_OF_DAY) &&
				(this.laatsteBeurt).get(Calendar.MINUTE) == ((Auto)ander).getLaatsteBeurt().get(Calendar.MINUTE)));
		

		return b;
	}
}