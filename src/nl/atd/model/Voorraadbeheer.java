package nl.atd.model;

import java.util.ArrayList;

public class Voorraadbeheer {
	
	private static Voorraadbeheer instance;
	
	private ArrayList<Artikel> artikelen;
	
	public Voorraadbeheer(){
		this.artikelen = new ArrayList<Artikel>();
		
	}
	
	private static Voorraadbeheer getInstance(){
		if(instance == null){
			instance = new Voorraadbeheer();
		}
		return instance;
	}
	
	
	
}
