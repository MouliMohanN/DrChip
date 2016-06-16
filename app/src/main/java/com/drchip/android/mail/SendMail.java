package com.drchip.android.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	public static void main(String[] args) {
		try {

			String[] strArr = { "moulimohann@gmail.com" };

			SendMail.send(strArr, "API Test Results", "Hi, Find the attachment for test results ");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public static String send(String[] to, String subject, String message) {

		return SendMail.sendMail("smartcuesapitest@gmail.com", "smartcues", "smtp.gmail.com", "465", "true", "true", true,
				"javax.net.ssl.SSLSocketFactory", "false", to, subject, message);
	}

	public synchronized static String sendMail(String userName, String passWord, String host, String port,
			String starttls, String auth, boolean debug, String socketFactoryClass, String fallback, String[] to,
			String subject, String text) {

		String errorMessage = "";

		Properties props = new Properties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		if (!"".equals(port))
			props.put("mail.smtp.port", port);
		if (!"".equals(starttls))
			props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.auth", auth);
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port))
			props.put("mail.smtp.socketFactory.port", port);
		if (!"".equals(socketFactoryClass))
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		if (!"".equals(fallback))
			props.put("mail.smtp.socketFactory.fallback", fallback);
		try {
			/*try{
				props.put("mail.smtp.connectiontimeout", 10000000 * 60);
				props.put("mail.smtp.timeout", 10000000 * 60);
			} catch (Exception e) {
				errorMessage += "\n\n\n timeout exception " + e.getMessage() + " \n\n\n";
			}*/

			Session session = Session.getDefaultInstance(props);

			// session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			msg.setText(text);
			msg.setSubject(subject);
			//String filename = "D:\\testResult.zip";
			//DataSource source = new FileDataSource(filename);
			//msg.setDataHandler(new DataHandler(source));
			//msg.setFileName(filename);
			
			/*msg.setReplyTo(new javax.mail.Address[]
					{
					    new javax.mail.internet.InternetAddress("mnop@gmail.com")
					});*/
			
			msg.setFrom(new InternetAddress("docketadmin@omspatentservices.com"));
			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			msg.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(host,Integer.parseInt(port), userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			errorMessage += "\n\nActual Email success\n\n";
			return errorMessage;
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "\n\n Actual Email failed: " + e.getMessage() + " \n\n";
			return errorMessage;
		}

	}

}
