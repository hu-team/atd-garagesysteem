package nl.atd.service;

public class ServiceProvider {
	private static MonteurService monteurService = new MonteurService();
	private static AutoService autoService = new AutoService();
	private static KlantService klantService = new KlantService();
	private static KlusService klusService = new KlusService();
	private static ArtikelService artikelService = new ArtikelService();
	
	public static MonteurService getMonteurService() {
		return monteurService;
	}
	
	public static AutoService getAutoService() {
		return autoService;
	}
	
	public static KlantService getKlantService() {
		return klantService;
	}
	
	public static KlusService getKlusService() {
		return klusService;
	}
	
	public static ArtikelService getArtikelService(){
		return artikelService;
	}
}
