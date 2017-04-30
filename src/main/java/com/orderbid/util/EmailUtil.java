package com.orderbid.util;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class EmailUtil {
	private static String emailusername=null;
	private static String emailpasswrd = null;
	public static ClientResponse SendSimpleMessage(String toEmailId, String htmlBody, String subject) {
	    Client client = Client.create();
	    client.addFilter(new HTTPBasicAuthFilter(
	        "api","key-3ax6xnjp29jd6fds4gc373sgvjxteol0"));
	    WebResource webResource = client.resource(
	        "https://api.mailgun.net/v3/samples.mailgun.org/messages");
	    MultivaluedMapImpl formData = new MultivaluedMapImpl();
	    formData.add("from", "Excited User <excited@samples.mailgun.org>");
	    formData.add("to", toEmailId);
	    formData.add("subject", subject);
	    formData.add("text", htmlBody);
	    return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
	        post(ClientResponse.class, formData);
	}
	public static void sendMailWithAuth(String host, String user, String password, String port, List<String> toList,
		     String htmlBody, String subject) throws Exception {
			emailusername = user;
			emailpasswrd = password;
		    Properties props = System.getProperties();

		    props.put("mail.smtp.user",user); 
		    props.put("mail.smtp.password", password);
		    props.put("mail.smtp.host", host); 
		    props.put("mail.smtp.port", port); 
		    //props.put("mail.debug", "true"); 
		    props.put("mail.smtp.auth", "true"); 
		    props.put("mail.smtp.starttls.enable","true"); 
		    props.put("mail.smtp.EnableSSL.enable","true");
		   // props.put("mail.smtp.socketFactory.port", port);
		   // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    //props.put("mail.smtp.socketFactory.fallback", "false");

		    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(emailusername, emailpasswrd);
		        }
		    });
		    session.setDebug(true);

		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(user));

		    // To get the array of addresses
		    for (String to: toList) {
		        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		    }

		    message.setSubject(subject);
		    message.setContent(htmlBody, "text/html");

		    Transport transport = session.getTransport("smtps");
		    try {
		    	Transport.send(message);
		        //transport.connect(host, user, password);
		        //transport.sendMessage(message, message.getAllRecipients());
		    } catch(Exception e){
		    	e.printStackTrace();
		    } finally {
		        transport.close();
		    }
	 }
	
	public static void sendMailWithAttachment(String host, String user, String password, String port, List<String> toList,
		     String htmlBody, String subject, String filePath) throws Exception {

		    Properties props = System.getProperties();

		    props.put("mail.smtp.user",user); 
		    props.put("mail.smtp.password", password);
		    props.put("mail.smtp.host", host); 
		    props.put("mail.smtp.port", port); 
		    //props.put("mail.debug", "true"); 
		    props.put("mail.smtp.auth", "true"); 
		    props.put("mail.smtp.starttls.enable","true"); 
		    props.put("mail.smtp.EnableSSL.enable","true");

		    Session session = Session.getInstance(props, null);
		    //session.setDebug(true);

		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(user));

		    // To get the array of addresses
		    for (String to: toList) {
		        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		    }

		    message.setSubject(subject);
		    // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
		    // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         DataSource source = new FileDataSource(filePath);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filePath);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

		     Transport.send(message);
	 }
}
