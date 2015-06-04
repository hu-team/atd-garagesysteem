package nl.atd.service;

import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.dao.KlusDAO;
import nl.atd.model.Klus;

public class KlusService {
	private KlusDAO klusDAO = new KlusDAO();
	
	/**
	 * Get alle klussen
	 * @return array met klussen
	 */
	public ArrayList<Klus> getKlussen() {
		return this.klusDAO.getAlleKlussen();
	}
	
	/**
	 * Klus toevoegen
	 * @param klus klus om toe te voegen
	 * @return gelukt?
	 */
	public boolean addKlus(Klus klus){
		return this.klusDAO.addKlus(klus);
	}

	/**
	 * Klus bewerkingen doorvoegen in database
	 * @param klus klus. Zorg dat datum, klant en auto niet aangepast wordt
	 * @return gelukt?
	 */
	public boolean editKlus(Klus klus){
		return this.klusDAO.editKlus(klus);
	}
	
	/**
	 * Get klussen tussen bepaalde tijd/datum
	 * @param start start tijd
	 * @param end eind tijd
	 * @return gefilterde klussen
	 */
	public ArrayList<Klus> getKlussenTussen(Calendar start, Calendar end) {
		return this.klusDAO.getAlleKlussenTussen(start, end);
	}
	
	/**
	 * Get klus op klusid
	 * @param id
	 * @return klus of null
	 */
	public Klus getKlusOpId(int id){
		return this.klusDAO.getKlusOpId(id);
	}
	
	/**
	 * Get klusid op klus
	 * @param klus klus om id te krijgen
	 * @return id of 0
	 */
	public int getKlusIdOpKlus(Klus klus){
		return this.klusDAO.getKlusId(klus.getCalendar(), klus.getKlant(), klus.getAuto());
	}

	/**
	 * Verwijder klus 
	 * LET OP, er kunnen uiteraard koppelingen zitten, verwijder het niet zomaar, zet eerder klus op nonactief.
	 * @param id id van klus om te verwijderen
	 * @throws Exception
	 */
	public void deleteKlus(int id) throws Exception {
		Klus k = klusDAO.getKlusOpId(id);
		
		if (k != null) {
			klusDAO.delete(k);
		} else throw new Exception("Klus met dit id, bestaat niet");
	}
	
	/**
	 * Verwijder alle klussen uit database
	 * LET OP: gebruik alleen binnen tests
	 */
	public void deleteAlleKlussen() {
		klusDAO.deleteAlles();
	}

}
