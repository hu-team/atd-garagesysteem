package nl.atd.service;

public class ServiceProvider {
	private static MonteurService monteurService = new MonteurService();
	
	public static MonteurService getMonteurService() {
		return monteurService;
	}
	
	
}
