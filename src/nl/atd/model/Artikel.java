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
	
	public Artikel(String nm){
		this.naam = nm;
	}
	
	public Artikel(String nm, int aan){
		this.naam = nm;
		this.aantal = aan;
	}
	
	public String getNaam(){
		return naam;
	}
	
	public void setNaam(String nm){
		this.naam = nm;
	}
	
	public String getCode(){
		return code;
	}
	
}
