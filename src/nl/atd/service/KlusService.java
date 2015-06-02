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

	public ArrayList<Klus> getKlussenTussen(Calendar start, Calendar end) {
		return this.klusDAO.getAlleKlussenTussen(start, end);
	}
	
}
