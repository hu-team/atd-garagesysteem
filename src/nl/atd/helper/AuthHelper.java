package nl.atd.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import nl.atd.dao.KlantDAO;
import nl.atd.dao.MonteurDAO;
import nl.atd.model.Klant;
import nl.atd.model.Monteur;
import nl.atd.model.Persoon;

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
				user = new MonteurDAO().getMonteur(gebruikersnaam);
				break;
			default:
				user = null;
				break;
			}
			
			// Encrypt wachtwoord
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
}
