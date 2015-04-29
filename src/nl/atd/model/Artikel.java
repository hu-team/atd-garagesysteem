package nl.atd.model;

/**
 * 
 * @author ATD Developers
 *
 */

public class Artikel {
    private String naam;
    private String code;
    private int aantal;
    private double prijs;
    
    /**
     * Artikel
     * @param nm Naam
     */
    public Artikel(String nm) {
    	this(nm, 0);
    }
    
    /**
     * Artikel
     * @param nm Naam
     * @param aan Aantal
     */
    public Artikel(String nm, int aan) {
    	this.naam = nm;
    	this.aantal = aan;
    }
    
    /**
     * Get naam
     * @return String
     */
    public String getNaam() {
    	return this.naam;
    }
    
    /**
     * Set naam
     * @param String nm Naam
     */
    public void setNaam(String nm) {
    	this.naam = nm;
    }
    
    /**
     * Set artikel code
     * @param code Code
     */
    public void setCode(String code){
    	this.code = code;
    }
    
    /**
     * Get artikel code
     * @return code
     */
    public String getCode() {
    	return this.code;
    }
    
    /**
     * Voeg aantal bij
     * @param aantal Aantal
     */
    public void voegAantalToe(int aantal) {
    	this.aantal += aantal;
    }
    
    /**
     * Kan er een aantal gebruikt worden?
     * @param aantal
     * @return boolean Kan het gebruikt worden?
     */
    public boolean kanAantalGebruiken(int aantal) {
    	return this.aantal >= aantal;
    }
    
    /**
     * Gebruik aantal
     * @param aantal
     * @return boolean gelukt?
     */
    public boolean gebruikAantal(int aantal) {
    	if(this.kanAantalGebruiken(aantal)) {
    		this.aantal -= aantal;
    		return true;
    	}
    	return false;
    }
    
    /**
     * Get aantal op voorraad
     * @return int Aantal
     */
    public int getAantal() {
		return aantal;
	}
    
    /**
     * Get prijs van artikel
     * @return prijs
     */
    public double getPrijs() {
    	return this.prijs;
    }
    
    /**
     * Set prijs van artikel
     * @param ps prijs
     */
    public void setPrijs(double ps) {
    	this.prijs = ps;
    }
    
    @Override
    public boolean equals(Object other) {
    	try{
    		return (((Artikel)other).getNaam().equals(this.naam) && ((Artikel)other).getCode().equals(this.code));
    	}catch(Exception e){return false;}
    }
    
    public String toString() {
    	return this.naam;
    }
}
