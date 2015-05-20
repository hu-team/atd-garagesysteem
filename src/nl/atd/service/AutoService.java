package nl.atd.service;

import java.util.ArrayList;

import nl.atd.dao.AutoDAO;
import nl.atd.model.Auto;

public class AutoService {
	private AutoDAO autoDAO = new AutoDAO();
	
	public ArrayList<Auto> getAlleAutos() {
		return this.autoDAO.getAlleAutos();
	}
	
	public ArrayList<Auto> getAutosVanKlant(String gebruikersnaam) {
		return this.autoDAO.getAutosVanKlant(gebruikersnaam);
	}
}
