package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.OnderdeelDAO;
import nl.atd.model.Onderdeel;

public class OnderdeelService {
	private OnderdeelDAO onderdeelDAO = new OnderdeelDAO();
	
	public ArrayList<Onderdeel> getAlleOnderdelen() {
		return this.onderdeelDAO.getAlleOnderdelen();
	}
	
	public ArrayList<Onderdeel> getAlleOnderdelenVanKlus(int klusid) {
		return this.onderdeelDAO.getAlleOnderdelenVanKlus(klusid);
	}
}
