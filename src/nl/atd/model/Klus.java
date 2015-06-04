package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Klus data object
 * 
 * @author ATD Developers
 *
 */
public class Klus {
	private String type;
	private boolean klaar;
	private int uren;
	private Calendar calendar;
	private String omschrijving;

	private Monteur monteur;
	private ArrayList<Onderdeel> onderdelen;
	private Klant klant;
	private Auto auto;

	public Klus(Klant kl, Auto aut) {
		this.klant = kl;
		this.auto = aut;

		this.type = "";
		this.klaar = false;
		this.uren = 0;
		this.calendar = Calendar.getInstance();
		this.omschrijving = "";
		this.onderdelen = new ArrayList<Onderdeel>();
	}

	/**
	 * Set de klaarstatus
	 * 
	 * @param kl
	 *            klaarstatus
	 */
	public void setKlaar(boolean kl) {
		this.klaar = kl;
	}

	/**
	 * Get klaarstatus
	 * 
	 * @return
	 */
	public boolean isKlaar() {
		return this.klaar;
	}

	/**
	 * Get Onderdelen van klus
	 * 
	 * @return array met onderdelen
	 */
	public ArrayList<Onderdeel> getOnderdelen() {
		return this.onderdelen;
	}

	/**
	 * Voeg onderdeel toe aan onderdelen
	 * 
	 * @param ond
	 *            toe te voegen onderdeel
	 */
	public void addOnderdeel(Onderdeel ond) {
		this.onderdelen.add(ond);
	}

	/**
	 * Verwijder onderdeel uit lijst
	 * 
	 * @param ond
	 *            onderdeel te verwijderen
	 */
	public void verwijderOnderdeel(Onderdeel ond) {
		this.onderdelen.remove(ond);
	}

	/**
	 * Vervang de onderdeellijst door een nieuwe lijst
	 * 
	 * @param ond
	 *            array met onderdelen
	 */
	public void setOnderdelen(ArrayList<Onderdeel> ond) {
		this.onderdelen = ond;
	}

	/**
	 * Set het aantal uren gewerkt aan klus
	 * 
	 * @param ur
	 *            uren
	 */
	public void setUren(int ur) {
		this.uren = ur;
	}

	/**
	 * Get aantal gewerkte uren
	 * 
	 * @return uren
	 */
	public int getUren() {
		return this.uren;
	}

	/**
	 * Voeg aantal uren toe aan huidig aantal uren
	 * 
	 * @param ur
	 *            uren toe te voegen
	 */
	public void addUren(int ur) {
		this.uren += ur;
	}

	/**
	 * Get datum en tijd van klus
	 * 
	 * @return datum en tijd
	 */
	public Calendar getCalendar() {
		return this.calendar;
	}

	/**
	 * Set datum en tijd van klus
	 * 
	 * @param cl
	 *            datum en tijd
	 */
	public void setCalendar(Calendar cl) {
		this.calendar = cl;
	}

	/**
	 * Get auto in klus
	 * 
	 * @return auto
	 */
	public Auto getAuto() {
		return this.auto;
	}

	/**
	 * Get klant in klus
	 * 
	 * @return klant
	 */
	public Klant getKlant() {
		return this.klant;
	}

	/**
	 * Get type klus
	 * 
	 * @return type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Set type klus
	 * 
	 * @param typ
	 *            type
	 */
	public void setType(String typ) {
		this.type = typ;
	}

	/**
	 * Get omschrijving van klus
	 * 
	 * @return omschrijving
	 */
	public String getOmschrijving() {
		return this.omschrijving;
	}

	/**
	 * Set omschrijving van klus
	 * 
	 * @param om
	 *            omschrijving
	 */
	public void setOmschrijving(String om) {
		this.omschrijving = om;
	}

	/**
	 * Get omschrijving zoals deze op de factuur weergegeven wordt
	 * 
	 * @return factuuromschrijving
	 */
	public String getFactuurOmschrijving() {
		return "Klus voor auto '" + this.auto.getMerk() + " "
				+ this.auto.getModel() + "' met kenteken "
				+ this.auto.getKenteken() + "\n" + this.type + ": "
				+ this.omschrijving;
	}

	/**
	 * Get totaalprijs onderdelen met uren, inclusief btw
	 * 
	 * @return totaalprijs
	 */
	public double getTotaalPrijs() {
		double totaal = 0;

		for (int i = 0; i < this.onderdelen.size(); i++) {
			totaal += this.onderdelen.get(i).getAantal()
					* this.onderdelen.get(i).getArtikel().getPrijs();
		}

		totaal += this.uren * 75;

		return totaal;
	}

	/**
	 * Get monteur die toegewezen is aan klus
	 * 
	 * @return monteur
	 */
	public Monteur getMonteur() {
		return this.monteur;
	}

	/**
	 * Wijs een monteur toe aan klus
	 * 
	 * @param mn
	 *            monteur
	 */
	public void setMonteur(Monteur mn) {
		this.monteur = mn;
	}

	public boolean equals(Object ander) {
		boolean b;

		if (ander instanceof Klus) {
			b = true;
		} else {
			b = false;
		}

		b = b && (this.type.equals(((Klus) ander).getType()));
		b = b && (this.klaar == ((Klus) ander).isKlaar());
		b = b && (this.uren == ((Klus) ander).getUren());
		b = b && (this.monteur.equals(((Klus) ander).getMonteur()));
		b = b && (this.klant.equals(((Klus) ander).getKlant()));
		b = b && (this.auto.equals(((Klus) ander).getAuto()));

		// Omdat in de tests de database moet worden geleegd bij tearDown()
		// b = b && (this.onderdelen.equals(((Klant)ander).getOnderdelen()));

		// Calendar vergelijken, DAY.OF.YEAR - YEAR - HOURS : MINUTES
	
		b = b && ((this.calendar).get(Calendar.DAY_OF_YEAR) == ((Klus)ander).getCalendar().get(Calendar.DAY_OF_YEAR));
		b = b && ((this.calendar).get(Calendar.YEAR) == ((Klus)ander).getCalendar().get(Calendar.YEAR));
		b = b && ((this.calendar).get(Calendar.HOUR_OF_DAY) == ((Klus)ander).getCalendar().get(Calendar.HOUR_OF_DAY));
		b = b && ((this.calendar).get(Calendar.MINUTE) == ((Klus)ander).getCalendar().get(Calendar.MINUTE));

	
		return b;
	}

}
