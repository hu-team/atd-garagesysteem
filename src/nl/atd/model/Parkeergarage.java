package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Parkeergarage zorgt voor een dataopslag voor de plekken die in de garage zitten.
 * @author ATD Developers
 *
 */
public class Parkeergarage {

	private static Parkeergarage instance;
	private ArrayList<Parkeerplek> plekken;
	
	public Parkeergarage(){
		this.plekken = new ArrayList<Parkeerplek>();
	}
	
	/**
	 * Get instantie van parkeergarage.
	 * @return
	 */
	public static Parkeergarage getInstance(){
		if(instance == null){
			instance = new Parkeergarage();
		}
		return instance;
	}
	
	/**
	 * Get alle plekken
	 * @return plekken
	 */
	public ArrayList<Parkeerplek> getAllePlekken(){
		return this.plekken;
	}
	
	/**
	 * Zoek vrije plek in garage, return de vrije plek, returnt null als er geen plek is.
	 * 
	 * @param van vanaf welke datum en tijd?
	 * @param tot tot welke datum en tijd?
	 * @return de plek of null als er geen vrije plek is
	 */
	public Parkeerplek zoekVrijePlek(Calendar van, Calendar tot){
		for(int i = 0; i < this.plekken.size(); i++) {
			if(this.plekken.get(i).isVrijTussen(van, tot)) {
				return this.plekken.get(i);
			}
		}
		
		return null;
	}
	
}
