package nl.atd.service;

public class ServiceProvider {
	private static MonteurService monteurService = new MonteurService();
	private static AutoService autoService = new AutoService();
	
	public static MonteurService getMonteurService() {
		return monteurService;
	}
	
	public static AutoService getAutoService() {
		return autoService;
	}
}
