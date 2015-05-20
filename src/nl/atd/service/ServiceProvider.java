package nl.atd.service;

public class ServiceProvider {
	private static MonteurService monteurService = new MonteurService();
	private static AutoService autoService = new AutoService();
	private static KlantService klantService = new KlantService();
	private static KlusService klusService = new KlusService();
	private static ArtikelService artikelService = new ArtikelService();
	
	private static OnderdeelService onderdeelService = new OnderdeelService();
	
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
	
<<<<<<< HEAD
	public static ArtikelService getArtikelService(){
		return artikelService;
=======
	public static OnderdeelService getOnderdeelService() {
		return onderdeelService;
>>>>>>> c966f48bfee6ea873d5f4597bf53540b885c9c3b
	}
}
