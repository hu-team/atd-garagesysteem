package nl.atd.service;

import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.dao.KlusDAO;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.model.Monteur;

public class KlusService {
	private KlusDAO klusDAO = new KlusDAO();
	
	/**
	 * Get alle klussen
	 * @return array met klussen
	 */
	public ArrayList<Klus> getKlussen() {
		return this.klusDAO.getAlleKlussen();
	}
	
	public boolean addKlus(Klus klus, Auto auto, Monteur monteur, Klant klant){
		return this.klusDAO.addKlus(klus, auto, monteur, klant);
	}

	public boolean editKlus(Klus klus){
		return this.klusDAO.editKlus(klus);
	}
	
	public ArrayList<Klus> getKlussenTussen(Calendar start, Calendar end) {
		return this.klusDAO.getAlleKlussenTussen(start, end);
	}
	
	public Klus getKlusOpId(int id){
		return this.klusDAO.getKlusOpId(id);
	}
	
	public int getKlusIdOpKlus(Klus klus){
		return this.klusDAO.getKlusId(klus.getCalendar(), klus.getKlant(), klus.getAuto());
	}

	public void deleteKlus(int id) throws Exception {
		Klus k = klusDAO.getKlusOpId(id);
		
		if (k != null) {
			klusDAO.delete(k);
		} else throw new Exception("Klus met dit id, bestaat niet");
	}
	
	public void deleteAlleKlussen() {
		klusDAO.deleteAlles();
	}

}
