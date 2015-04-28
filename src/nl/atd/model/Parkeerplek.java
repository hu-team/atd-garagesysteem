package nl.atd.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Parkeerplek {
	
	private char rij;
	private int plek;
	
	private ArrayList<Reservering> reserveringen;
	
	public Parkeerplek(char rij, int plek){
		this.rij = rij;
		this.plek = plek;
	}
	
	public char getRij(){
		return this.rij;
	}
	
	public int getPlek(){
		return this.plek;
	}
	
	public boolean isBezet(){
		
		return false;
	}
	
	public void addReservering(Reservering res){
		
	}
	
	public ArrayList<Reservering> getAlleReserveringen(){
		return this.reserveringen;
	}
	
	public boolean isVrijTussen(Calendar begin, Calendar eind){
		return false;
	}
}
