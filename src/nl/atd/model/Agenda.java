package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Agenda, zorgt voor data opslag voor alle klussen
 * @author ATD Developer
 * 
 * @deprecated NIET MEER GEBRUIKEN, GEBRUIK SERVICE VOOR PARKEERPLEKKEN
 *
 */
public class Agenda {

	private static Agenda instance;
	private ArrayList<Klus> klussen;
	
	public Agenda(){
		klussen = new ArrayList<Klus>();
	}
	
	/**
	 * Get instance van Agenda
	 * @return Agenda instance
	 */
	public static Agenda getInstance(){
		if(instance == null){
			instance = new Agenda();
		}
		return instance;
	}
	
	/**
	 * Voeg klus toe
	 * @param Klus kls
	 */
	public void addKlus(Klus kls){
		klussen.add(kls);
	}
	
	/**
	 * Get alle klussen
	 * @return ArrayList klussen
	 */
	public ArrayList<Klus> getKlussen(){
		return klussen;
	}
	
	/**
	 * Get klussen op bepaalde dag
	 * @param dt Datum van een bepaalde dag
	 * @return ArrayList met alle klussen van die dag
	 */
	public ArrayList<Klus> getKlussenOpDag(Calendar dt){
		ArrayList<Klus> tempList = new ArrayList<Klus>();
		
		for(Klus kl : klussen){
			if(kl.getCalendar().get(Calendar.YEAR) == dt.get(Calendar.YEAR)
					&& kl.getCalendar().get(Calendar.MONTH) == dt.get(Calendar.MONTH)
					&& kl.getCalendar().get(Calendar.DAY_OF_MONTH) == dt.get(Calendar.DAY_OF_MONTH)){
				tempList.add(kl);
			}
		}
		
		return tempList;
	}
	
	/**
	 * Get klussen in maand datum
	 * @param dt Datum van de maand
	 * @return ArrayList met klussen van die maand
	 */
	public ArrayList<Klus> getKlussenInMaand(Calendar dt){
		ArrayList<Klus> tempList = new ArrayList<Klus>();
		
		for(Klus kl : klussen){
			if(kl.getCalendar().get(Calendar.YEAR) == dt.get(Calendar.YEAR)
					&& kl.getCalendar().get(Calendar.MONTH) == dt.get(Calendar.MONTH)){
				tempList.add(kl);
			}
		}
		
		return tempList;
	}
}
