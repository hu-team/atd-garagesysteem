package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Parkeergarage {

	private static Parkeergarage instance;
	private ArrayList<Parkeerplek> plekken;
	
	public Parkeergarage(){
		this.plekken = new ArrayList<Parkeerplek>();
	}
	
	public static Parkeergarage getInstance(){
		if(instance == null){
			instance = new Parkeergarage();
		}
		return instance;
	}
	
	public ArrayList<Parkeerplek> getAllePlekken(){
		return this.plekken;
	}
	
	public Parkeerplek zoekVrijePlek(Calendar van, Calendar tot){
		
		return null;
	}
	
}
