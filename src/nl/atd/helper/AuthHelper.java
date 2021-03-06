package nl.atd.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import nl.atd.dao.KlantDAO;
import nl.atd.model.Klant;
import nl.atd.model.Monteur;
import nl.atd.model.Persoon;
import nl.atd.service.ServiceProvider;

/**
 * Helper class die inlogpogingen, en sessie beheerd voor authenticatie en autorisatie
 * 
 * @author ATD Developers
 *
 */
public class AuthHelper {
	
	/**
	 * Execute login aanvraag, verwerkt login in sessie indien het gelukt is.
	 * @param session
	 * @param gebruikersnaam
	 * @param wachtwoord
	 * @param type soort van inlogpoging
	 * @return is de gebruiker nu ingelogd?
	 */
	public static boolean executeLogin(HttpSession session, String gebruikersnaam, String wachtwoord, AuthType type) {
		boolean success = false;
		
		if(type == AuthType.ADMIN && gebruikersnaam.equals("henk") && wachtwoord.equals("henkje101")) {
			// Admin is ingelogd
			session.setAttribute("user", gebruikersnaam);
			session.setAttribute("logintime", Calendar.getInstance());
			success = true;
		}else{
			Persoon user = null;
			String wachtwoordEncrypted = "";
			
			// Persoon uit juiste tabel opvragen
			switch(type) {
			case KLANT: 
				user = new KlantDAO().getKlant(gebruikersnaam);
				break;
			case MONTEUR:
				user = ServiceProvider.getMonteurService().getMonteurByGebruikersnaam(gebruikersnaam);
				break;
			default:
				user = null;
				break;
			}
			
			// Encrypt wachtwoord
			wachtwoordEncrypted = AuthHelper.encryptWachtwoord(wachtwoord);
			
			// Compare
			if(user != null && !user.getWachtwoord().isEmpty() &&
					user.getGebruikersnaam().equals(gebruikersnaam) &&
					user.getWachtwoord().equals(wachtwoordEncrypted)) {
				// Gelukt, hij is het.
				session.setAttribute("user", user);
				session.setAttribute("logintime", Calendar.getInstance());
				
				success = true;
			}
			
		}
		
		return success;
	}
	
	/**
	 * Encrypt het plaintext wachtwoord
	 * @param wachtwoord
	 * @return encrypted wachtwoord
	 */
	public static String encryptWachtwoord(String wachtwoord) {
		String wachtwoordEncrypted = null;
		
		try{
			MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
			byte[] data = sha512.digest(wachtwoord.getBytes());
			
			// Naar HEX string
			StringBuffer hexData = new StringBuffer();
	        for (int i = 0; i < data.length; i++) {
	        	hexData.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        wachtwoordEncrypted = hexData.toString();
		}catch(NoSuchAlgorithmException nsae) {
			Logger.getLogger("to4").warning("SHA-512 not found!");
			System.exit(1);
		}
		return wachtwoordEncrypted;
	}
	
	
	
	/**
	 * Is er iemand ingelogd
	 * @param session
	 * @return ja of nee
	 */
	public static boolean isLoggedIn(HttpSession session) {
		return session.getAttribute("user") != null;
	}
	
	/**
	 * Is er een monteur ingelogd
	 * @param session
	 * @return ja of nee
	 */
	public static boolean isMonteur(HttpSession session) {
		return isLoggedIn(session) && session.getAttribute("user") instanceof Monteur;
	}
	
	/**
	 * Is er een klant ingelogd
	 * @param session
	 * @return ja of nee
	 */
	public static boolean isKlant(HttpSession session) {
		return isLoggedIn(session) && session.getAttribute("user") instanceof Klant;
	}
	
	/**
	 * Is er een beheerder ingelogd
	 * @param session
	 * @return ja of nee
	 */
	public static boolean isAdmin(HttpSession session) {
		return isLoggedIn(session) && (!isMonteur(session) && !isKlant(session));
	}
	
	/**
	 * Get naam van huidige gebruiker
	 * @param session
	 * @return naam
	 */
	public static String getNaam(HttpSession session) {
		if(isAdmin(session)) return "Henk";
		if(isLoggedIn(session)) {
			return ((Persoon)session.getAttribute("user")).getNaam();
		}
		return "";
	}
	
	/**
	 * Get gebruikersnaam van klant of monteur. Of null bij geen.
	 * @param session
	 * @return gebruikersnaam
	 */
	public static String getGebruikersnaam(HttpSession session) {
		if(isKlant(session) || isMonteur(session) && session.getAttribute("user") instanceof Persoon) {
			return ((Persoon)session.getAttribute("user")).getGebruikersnaam();
		}
		return null;
	}

	/**
	 * Uitloggen
	 * @param session
	 */
	public static void logOut(HttpSession session) {
		session.setAttribute("user", null);
		session.setAttribute("logintime", null);
	}
}
