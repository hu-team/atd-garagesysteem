package nl.atd.service;

/**
 * ServiceProvider zorgt voor de koppeling tussen business logic
 * en de achterliggende persistance laag. 
 * De persistance laag werkt achter de business logic.
 * 
 * @author ATD Developers
 *
 */
public class ServiceProvider {
	private static MonteurService monteurService = new MonteurService();
	private static AutoService autoService = new AutoService();
	private static KlantService klantService = new KlantService();
	private static KlusService klusService = new KlusService();
	private static ArtikelService artikelService = new ArtikelService();
	private static OnderdeelService onderdeelService = new OnderdeelService();
	private static ParkeerService parkeerService = new ParkeerService();
	
	/**
	 * Krijg Monteur Service Provider
	 * @return Monteur Service
	 */
	public static MonteurService getMonteurService() {
		return monteurService;
	}
	
	/**
	 * Krijg Auto Service Provider
	 * @return Auto Service
	 */
	public static AutoService getAutoService() {
		return autoService;
	}
	
	/**
	 * Krijg Klant Service Provider
	 * @return Klant Service
	 */
	public static KlantService getKlantService() {
		return klantService;
	}
	
	/**
	 * Krijg Klus Service Provider
	 * @return Klus Service
	 */
	public static KlusService getKlusService() {
		return klusService;
	}
	
	/**
	 * Krijg Artikel Service Provider
	 * @return Artikel Service
	 */
	public static ArtikelService getArtikelService() {
		return artikelService;
	}

	/**
	 * Krijg Onderdeel Service Provider
	 * @return Onderdeel Service
	 */
	public static OnderdeelService getOnderdeelService() {
		return onderdeelService;
	}
	
	/**
	 * Krijg parkeer service provider
	 * @return parkeer service
	 */
	public static ParkeerService getParkeerService() {
		return parkeerService;
	}
}
