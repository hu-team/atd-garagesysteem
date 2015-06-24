package nl.atd.helper;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import nl.atd.model.Klant;

public class MailHelper {
	
	private static final String WELKOM_KLANT = "<strong>Welkom [NAAM],</strong><br /><br />Er is voor u een account aangemaakt, de gegevens worden hier vermeld:<br />"
			+ "Naam: [NAAM] <br />"
			+ "E-mail adres: [EMAIL] <br />"
			+ "Telefoonnummer: [TELEFOONNUMMER] <br />"
			+ "Gebruikersnaam: [GEBRUIKERSNAAM] <br />"
			+ "Wachtwoord: [WACHTWOORD] <br /><br />"
			+ "Voor de volledige details, of het wijzigen van uw gegevens dient u in te loggen in de applicatie. Dit kan op <a href='http://www.atd.nl/app'>http://www.atd.nl/app/</a><br /><br />"
			+ "Met vriendelijke groet,<br />"
			+ "AutoTotaalDienst";
	
	public static final String NIEUWEFACTUUR = "<strong>Geachte klant,</strong><br /><br />Er is een nieuwe factuur beschikbaar in uw omgeving.<br />"
			+ "Om de factuur in te zien dient u in te loggen in de applicatie. Dit kan op <a href='http://www.atd.nl/app'>http://www.atd.nl/app/</a><br /><br />"
			+ "Met vriendelijke groet,<br />"
			+ "AutoTotaalDienst";
	
	private static final String AFZENDER_NAAM = "ATD Applicatie";
	private static final String AFZENDER_EMAIL = "atd@lt-box.info";


	
	
	
	public static Session getMailSession() {
		
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", 25);
		//props.put("mail.smtp.")
		
		Session session = Session.getInstance(props);
		
		return session;
	}
	
	public static boolean sendWelkomMailNaarKlant(Klant klant, String wachtwoord) {
		if(klant.getEmail() == null || klant.getNaam() == null || klant.getEmail().isEmpty() || klant.getNaam().isEmpty()) {
			return false;
		}
		
		Session session = getMailSession();
		
		MimeMessage message = new MimeMessage(session);
		
		try{
			message.setFrom(new InternetAddress(AFZENDER_EMAIL, AFZENDER_NAAM));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(klant.getEmail(), klant.getNaam()));
			message.setSubject("Welkom bij AutoTotaalDienst Online!");
			message.setSentDate(Calendar.getInstance().getTime());
			
			String content = "" + WELKOM_KLANT;
			content = content.replace("[NAAM]", klant.getNaam());
			content = content.replace("[EMAIL]", klant.getEmail());
			content = content.replace("[TELEFOONNUMMER]", klant.getTelefoonnummer());
			content = content.replace("[GEBRUIKERSNAAM]", klant.getGebruikersnaam());
			content = content.replace("[WACHTWOORD]", wachtwoord);
			
			message.setContent(content, "text/html; charset=utf-8");
			
			// TODO: Mail account met settings, dan pas activeren
			Transport.send(message);
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean sendEmailNaarKlant(Klant klant, String onderwerp, String inhoud) {
		if(klant.getEmail() == null || klant.getNaam() == null || klant.getEmail().isEmpty() || klant.getNaam().isEmpty()) {
			return false;
		}
		
		Session session = getMailSession();
		
		MimeMessage message = new MimeMessage(session);
		
		try{
			message.setFrom(new InternetAddress(AFZENDER_EMAIL, AFZENDER_NAAM));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(klant.getEmail(), klant.getNaam()));
			message.setSubject(onderwerp);
			message.setSentDate(Calendar.getInstance().getTime());
			
			message.setContent(inhoud, "text/html; charset=utf-8");
			
			// TODO: Mail account met settings, dan pas activeren
			Transport.send(message);
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/*
	public static void testMail() {
		Session session = getMailSession();
		
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress("atd@lt-box.info", "ATD Mailer"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress("jaman@test.pw", "Testing"));
			message.setSubject("ATD Test Mail");
			message.setSentDate(Calendar.getInstance().getTime());
			message.setText("Test mail");
			Transport.send(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}			
		
	}*/
}
