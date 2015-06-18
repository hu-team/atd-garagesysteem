package nl.atd.helper;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHelper {
	public static Session getMailSession() {
		
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", 25);
		//props.put("mail.smtp.")
		
		Session session = Session.getInstance(props);
		
		return session;
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
